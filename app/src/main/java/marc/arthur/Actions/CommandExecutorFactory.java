package marc.arthur.Actions;

import java.util.HashMap;

/**
 * Created by gilbertm on 11/05/2016.
 */
public class CommandExecutorFactory {


    static HashMap< String , ArthurCommandExecutor> arthurCommands = new HashMap<>();
    static {
        arthurCommands.put( "MOVE" , new Mover() );
    }


    public ArthurCommandExecutor createArthurCommandExecutor(String commandName){

        ArthurCommandExecutor commandExecutor = null;

        ArthurCommandExecutor commandExecutorProto = arthurCommands.get(commandName);
        if( commandExecutorProto!=null ){

            try {
                commandExecutor = commandExecutorProto.getClass().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return commandExecutor;
    }








}
