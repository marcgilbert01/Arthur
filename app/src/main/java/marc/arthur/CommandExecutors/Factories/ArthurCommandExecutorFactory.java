package marc.arthur.CommandExecutors.Factories;

import java.util.EnumMap;

import marc.arthur.CommandExecutors.ArthurCommandExecutors.ArthurCommandExecutor;
import marc.arthur.CommandExecutors.ArthurCommandExecutors.ArthurPhotoExecutor;
import marc.arthur.CommandExecutors.ArthurCommandExecutors.ArthurRotateExecutor;
import marc.arthur.CommandExecutors.ArthurCommandExecutors.ArthurSpeechExecutor;
import marc.arthur.CommandExecutors.CommandExecutor;
import marc.arthur.CommandExecutors.ArthurCommandExecutors.ArthurMoveExecutor;

/**
 * Created by gilbertm on 11/05/2016.
 */
public class ArthurCommandExecutorFactory extends CommandExecutorFactory{


    static EnumMap< CommandExecutorType , ArthurCommandExecutor > commandExecutorEnumMap = new EnumMap<>(CommandExecutorType.class);
    static {

       commandExecutorEnumMap.put( CommandExecutorType.MOVE   , new ArthurMoveExecutor()   );
       commandExecutorEnumMap.put( CommandExecutorType.ROTATE , new ArthurRotateExecutor() );
       commandExecutorEnumMap.put( CommandExecutorType.PHOTO  , new ArthurPhotoExecutor()  );
       commandExecutorEnumMap.put(CommandExecutorType.SPEAK   , new ArthurSpeechExecutor() );


    }


    @Override
    public CommandExecutor createCommandExecutor(CommandExecutorType commandExecutorType) {

        ArthurCommandExecutor commandExecutor = null;

        ArthurCommandExecutor commandExecutorProto = commandExecutorEnumMap.get(commandExecutorType);

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
