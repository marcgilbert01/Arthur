package marc.arthur.Commands;

import marc.arthur.CommandExecutors.Factories.CommandExecutorFactory;

/**
 * Created by gilbertm on 12/05/2016.
 */
public class CommandFactory {

    public enum CommandType {

        MOVE( new CommandMove() , CommandExecutorFactory.CommandExecutorType.MOVE ),
        ROTATE( new CommandRotate() , CommandExecutorFactory.CommandExecutorType.ROTATE );


        Command command;
        CommandExecutorFactory.CommandExecutorType commandExecutorType;

        CommandType(Command command , CommandExecutorFactory.CommandExecutorType commandExecutorType) {

            this.command = command;
            this.commandExecutorType = commandExecutorType;
        }

        public CommandExecutorFactory.CommandExecutorType getCommandExecutorType() {
            return commandExecutorType;
        }
    }


    public Command createCommand(CommandType commandType){

        Command command = null;

        try {
            command = commandType.command.getClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return command;
    }




}
