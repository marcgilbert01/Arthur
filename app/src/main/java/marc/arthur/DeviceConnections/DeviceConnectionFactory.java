package marc.arthur.DeviceConnections;

import java.util.HashMap;

/**
 * Created by gilbertm on 11/05/2016.
 */
public class DeviceConnectionFactory {


    public enum DeviceConnectionType{

        BLUETOOTH ( new BluetoothConnection() ),
        IP ( new IpConnection() ),
        DUMMY ( new DummyDeviceConnection() ) ;

        DeviceConnection deviceConnection;

        DeviceConnectionType(DeviceConnection deviceConnection) {
            this.deviceConnection = deviceConnection;
        }
    }

    public DeviceConnection createDeviceConnection(DeviceConnectionType deviceConnectionType){

        DeviceConnection deviceConnection = null;
        try {
            deviceConnection = deviceConnectionType.deviceConnection.getClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return deviceConnection;
    }



}
