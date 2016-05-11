package marc.arthur.DeviceConnections;

import java.util.HashMap;

/**
 * Created by gilbertm on 11/05/2016.
 */
public class DeviceConnectionFactory {

    static HashMap<String,DeviceConnection> deviceConnections = new HashMap<>();
    static {

        deviceConnections.put( "BLUETOOTH" , new BluetoothConnection() );
        deviceConnections.put( "IP" , new IpConnection() );
    }


    public DeviceConnection createDeviceConnection(String connectionName){

        DeviceConnection deviceConnection = null;

        DeviceConnection deviceConnectionProto = deviceConnections.get(connectionName);

        if( deviceConnectionProto!=null ){

            try {
                deviceConnection = deviceConnectionProto.getClass().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return deviceConnection;
    }



}
