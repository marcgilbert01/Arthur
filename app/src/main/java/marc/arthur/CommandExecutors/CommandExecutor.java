package marc.arthur.CommandExecutors;

import marc.arthur.Commands.Command;
import marc.arthur.Responses.Response;

/**
 * Created by gilbertm on 11/05/2016.
 */
public interface CommandExecutor {


    public void executeCommand(Command command);

    public void setResponseListener(ResponseListener responseListener);

    interface ResponseListener{
        public void onResponseReceived(Response response);
    }


}
