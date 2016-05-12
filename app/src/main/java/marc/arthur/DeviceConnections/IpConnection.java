package marc.arthur.DeviceConnections;

/**
 * Created by gilbertm on 11/05/2016.
 */
public class IpConnection implements DeviceConnection {

    String ip;
    Integer port;

    @Override
    public void connect() {
    }

    @Override
    public void sendData(byte[] data, DataReceiver dataReceiver) {
    }
}



