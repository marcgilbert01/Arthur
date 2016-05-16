package marc.arthur.CommandExecutors.Factories;

import java.util.EnumMap;
import java.util.HashMap;

import marc.arthur.CommandExecutors.CommandExecutor;

/**
 * Created by gilbertm on 12/05/2016.
 */
public abstract class CommandExecutorFactory {

    public enum CommandExecutorFactoryType{

        ARTHUR( new ArthurCommandExecutorFactory() );

        CommandExecutorFactory commandExecutorFactory;
        CommandExecutorFactoryType(CommandExecutorFactory commandExecutorFactory) {
            this.commandExecutorFactory = commandExecutorFactory;
        }

        public CommandExecutorFactory getCommandExecutorFactory() {
            return commandExecutorFactory;
        }
    }

    public enum CommandExecutorType{

        MOVE,
        ROTATE,
        PHOTO,
        SPEAK
        ;

    }


    public abstract CommandExecutor createCommandExecutor(CommandExecutorType commandExecutorType);

}
