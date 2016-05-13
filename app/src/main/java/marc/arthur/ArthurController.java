package marc.arthur;

import java.util.EnumMap;

import marc.arthur.CommandExecutors.ArthurCommandExecutors.ArthurCommandExecutor;
import marc.arthur.CommandExecutors.CommandExecutor;
import marc.arthur.CommandExecutors.Factories.CommandExecutorFactory;
import marc.arthur.Commands.Command;
import marc.arthur.Commands.CommandFactory;
import marc.arthur.Commands.CommandMove;
import marc.arthur.Commands.CommandRotate;
import marc.arthur.DeviceConnections.DeviceConnection;
import marc.arthur.PacketBuilders.ArthurCommandByteBuilder;
import marc.arthur.PacketBuilders.ArthurEnvelopeBuilder;
import marc.arthur.PacketBuilders.ArthurResponseByteBuilder;
import marc.arthur.Responses.Response;

/**
 * Created by gilbertm on 12/05/2016.
 */
public class ArthurController {

    protected EnumMap<CommandFactory.CommandType, Command> commands =
            new EnumMap<CommandFactory.CommandType, Command>(CommandFactory.CommandType.class);


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




    public void performPentagon(){

        CommandMove commandMove = (CommandMove) commands.get(CommandFactory.CommandType.MOVE);

        CommandRotate commandRotate108degrees = (CommandRotate) commands.get(CommandFactory.CommandType.ROTATE);
        commandRotate108degrees.setDegrees(108);





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
