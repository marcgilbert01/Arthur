package marc.arthur.Commands;

import marc.arthur.CommandExecutors.CommandExecutor;
import marc.arthur.Responses.Response;

/**
 * Created by gilbertm on 11/05/2016.
 */
public class Command{

    Response response;
    CommandExecutor commandExecutor;

    public void setCommandExecutor(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    public CommandExecutor getCommandExecutor() {
        return commandExecutor;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public void execute(CommandExecutor.ResponseListener responseListener){

        commandExecutor.executeCommand(this);
    }



}
