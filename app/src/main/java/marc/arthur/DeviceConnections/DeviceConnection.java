package marc.arthur.DeviceConnections;

/**
 * Created by gilbertm on 11/05/2016.
 */
public interface DeviceConnection {

    public void connect();
    public void sendData(Byte[] data , DataReceiver dataReceiver);

    interface DataReceiver{

        public void onDataReceived(Byte[] bytes);
    }


}
