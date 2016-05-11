package marc.arthur.Actions;

import marc.arthur.ParamsIn.ParamsIn;
import marc.arthur.ResponseData;

/**
 * Created by gilbertm on 11/05/2016.
 */
public interface CommandSender {


    public void sendCommand( ParamsIn paramsIn );

    public void addResponseListener(ResponseListener responseListener);


    interface ResponseListener{
        public void onResponseReceived(ResponseData responseData);
    }


}
