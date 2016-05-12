package marc.arthur;

import java.util.EnumMap;

import marc.arthur.CommandExecutors.ArthurCommandExecutors.ArthurCommandExecutor;
import marc.arthur.CommandExecutors.CommandExecutor;
import marc.arthur.CommandExecutors.Factories.CommandExecutorFactory;
import marc.arthur.Commands.Command;
import marc.arthur.Commands.CommandFactory;
import marc.arthur.Commands.CommandMove;
import marc.arthur.DeviceConnections.DeviceConnection;
import marc.arthur.EnvelopeBuilders.EnvelopeBuilder;
import marc.arthur.Responses.Response;

/**
 * Created by gilbertm on 12/05/2016.
 */
public class ArthurController {

    protected EnumMap<CommandFactory.CommandType, Command> commands =
            new EnumMap<CommandFactory.CommandType, Command>(CommandFactory.CommandType.class);


    public void preformStraightLine(){

        CommandMove commandMove = (CommandMove) commands.get(CommandFactory.CommandType.MOVE);
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




    static class Builder{

        DeviceConnection deviceConnection;
        EnvelopeBuilder envelopeBuilder;

        public Builder setDeviceConnection(DeviceConnection deviceConnection) {
            this.deviceConnection = deviceConnection;
            return this;
        }

        public Builder setEnvelopeBuilder(EnvelopeBuilder envelopeBuilder) {
            this.envelopeBuilder = envelopeBuilder;
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
                arthurCommandExecutor.setEnvelopeBuilder( envelopeBuilder );
                Command command = commandFactory.createCommand(commandType);
                command.setCommandExecutor( commandExecutorFactory.createCommandExecutor(commandType.getCommandExecutorType()) );

                arthurController.commands.put( commandType , command );

            }

            return arthurController;
        }




    }





}
