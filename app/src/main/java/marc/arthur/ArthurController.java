package marc.arthur;

import android.graphics.Bitmap;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import marc.arthur.CommandExecutors.ArthurCommandExecutors.ArthurCommandExecutor;
import marc.arthur.CommandExecutors.CommandExecutor;
import marc.arthur.CommandExecutors.Factories.CommandExecutorFactory;
import marc.arthur.Commands.Command;
import marc.arthur.Commands.CommandFactory;
import marc.arthur.Commands.CommandMove;
import marc.arthur.Commands.CommandPhoto;
import marc.arthur.Commands.CommandRotate;
import marc.arthur.Commands.CommandSpeak;
import marc.arthur.DeviceConnections.DeviceConnection;
import marc.arthur.PacketBuilders.ArthurCommandByteBuilder;
import marc.arthur.PacketBuilders.ArthurEnvelopeBuilder;
import marc.arthur.PacketBuilders.ArthurResponseByteBuilder;
import marc.arthur.Responses.PhotoResponse;
import marc.arthur.Responses.Response;

/**
 * Created by gilbertm on 12/05/2016.
 */
public class ArthurController {

    protected EnumMap<CommandFactory.CommandType, Command> commands =
            new EnumMap<CommandFactory.CommandType, Command>(CommandFactory.CommandType.class);

    List<Bitmap> bitmapList = new ArrayList<>();

    CallBack callBack;



    public void preformStraightLine(){

        CommandMove commandMove = (CommandMove) commands.get(CommandFactory.CommandType.MOVE);
        commandMove.setSequenceNumber(1);
        commandMove.setDistance(15);
        commandMove.execute(new CommandExecutor.ResponseListener() {
            @Override
            public void onResponseReceived(Response response) {

                if( response.getSuccess() ){
                    System.out.println("15 m done");
                }
                else{
                    System.out.println("Error");
                }
            }
        });




    }


    public void getInPlaceForPentagone(final CallBack callBack){


        final CommandMove commandMove = (CommandMove) commands.get(CommandFactory.CommandType.MOVE);
        final CommandRotate commandRotate = (CommandRotate) commands.get(CommandFactory.CommandType.ROTATE);

        // ROTATE -18 degrees
        commandRotate.setDegrees(-18);
        commandRotate.execute(new CommandExecutor.ResponseListener() {
            @Override
            public void onResponseReceived(Response response) {

                commandMove.setDistance( 2000 );
                commandMove.execute(new CommandExecutor.ResponseListener() {

                    @Override
                    public void onResponseReceived(Response response) {

                        commandRotate.setDegrees( 126 );
                        commandRotate.execute(new CommandExecutor.ResponseListener() {
                            @Override
                            public void onResponseReceived(Response response) {
                                callBack.onProgramCompleted(true);
                            }
                        });
                    }
                });
            }
        });

    }


    public void performPentagon(){

        final int[] n = {0};

        callBack = new ArthurController.CallBack() {
            @Override
            public void onProgramCompleted(Boolean success) {

                if( n[0]<4 ) {
                    n[0]++;
                    performPentagonSide(callBack);
                }
            }
        };

        performPentagonSide(callBack);

    }


    public void performPentagonSide(final CallBack callBack){


        final CommandMove commandMove = (CommandMove) commands.get(CommandFactory.CommandType.MOVE);
        final CommandRotate commandRotate = (CommandRotate) commands.get(CommandFactory.CommandType.ROTATE);
        final CommandPhoto commandPhoto = (CommandPhoto) commands.get(CommandFactory.CommandType.PHOTO);

        commandMove.setDistance( 2351 );
        commandMove.execute(new CommandExecutor.ResponseListener() {
            @Override
            public void onResponseReceived(Response response) {

                commandRotate.setDegrees( 72 );
                commandRotate.execute(new CommandExecutor.ResponseListener() {
                    @Override
                    public void onResponseReceived(Response response) {


                        commandPhoto.execute(new CommandExecutor.ResponseListener() {
                            @Override
                            public void onResponseReceived(Response response) {

                                PhotoResponse photoResponse = (PhotoResponse) response;

                                bitmapList.add(photoResponse.getBitmap());

                                callBack.onProgramCompleted(true);

                            }
                        });

                    }
                });
            }
        });

    }




    public void speak(String textToSpeak , final CallBack callBack){

        CommandSpeak commandSpeak = (CommandSpeak) commands.get(CommandFactory.CommandType.SPEAK);

        commandSpeak.setSpeech(textToSpeak);

        commandSpeak.execute(new CommandExecutor.ResponseListener() {
            @Override
            public void onResponseReceived(Response response) {

                if( response.getSuccess() ){
                    System.out.println("Done speaking ");
                }
                else{
                    System.out.println("Error");
                }

                callBack.onProgramCompleted(response.getSuccess());

            }
        });



    }





    interface CallBack{

        public void onProgramCompleted(Boolean success);

    }






    static class Builder{

        DeviceConnection deviceConnection;
        ArthurEnvelopeBuilder arthurEnvelopeBuilder;
        ArthurCommandByteBuilder arthurCommandByteBuilder;
        ArthurResponseByteBuilder arthurResponseByteBuilder;

        public Builder setDeviceConnection(DeviceConnection deviceConnection) {
            this.deviceConnection = deviceConnection;
            return this;
        }

        public Builder setArthurEnvelopeBuilder(ArthurEnvelopeBuilder arthurEnvelopeBuilder) {
            this.arthurEnvelopeBuilder = arthurEnvelopeBuilder;
            return this;
        }

        public Builder setArthurCommandByteBuilder(ArthurCommandByteBuilder arthurCommandByteBuilder) {
            this.arthurCommandByteBuilder = arthurCommandByteBuilder;
            return this;
        }

        public Builder setArthurResponseByteBuilder(ArthurResponseByteBuilder arthurResponseByteBuilder) {
            this.arthurResponseByteBuilder = arthurResponseByteBuilder;
            return this;
        }

        public ArthurController build(){

            ArthurController arthurController = new ArthurController();

            CommandFactory commandFactory = new CommandFactory();
            CommandExecutorFactory commandExecutorFactory =
                     CommandExecutorFactory.CommandExecutorFactoryType.ARTHUR.getCommandExecutorFactory();

            for(CommandFactory.CommandType commandType : CommandFactory.CommandType.values()){

                ArthurCommandExecutor arthurCommandExecutor =
                        (ArthurCommandExecutor) commandExecutorFactory.createCommandExecutor( commandType.getCommandExecutorType() );
                arthurCommandExecutor.setDeviceConnection( deviceConnection );
                arthurCommandExecutor.setEnvelopeBuilder( arthurEnvelopeBuilder );
                arthurCommandExecutor.setCommandByteBuilder(arthurCommandByteBuilder);
                arthurCommandExecutor.setResponseByteBuilder(arthurResponseByteBuilder);

                Command command = commandFactory.createCommand(commandType);
                command.setCommandExecutor( arthurCommandExecutor );
                arthurController.commands.put( commandType , command );

            }

            return arthurController;
        }




    }





}
