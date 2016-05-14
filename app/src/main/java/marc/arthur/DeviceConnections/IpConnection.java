package marc.arthur.DeviceConnections;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by gilbertm on 11/05/2016.
 */
public class IpConnection implements DeviceConnection {

    String ip;
    Integer port;

    Socket socket;
    InputStream inputStream;
    OutputStream outputStream;

    @Override
    public void connect() {

        try {
            socket = new Socket( getIp() , getPort() );
            inputStream  = socket.getInputStream();
            outputStream = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void sendData(byte[] data, DataReceiver dataReceiver) {

        try {

            outputStream.write(data);
            byte[] buffer = new byte[512];
            inputStream.read(buffer);
            dataReceiver.onDataReceived( buffer );

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public String getIp() {
        return ip;
    }

    public IpConnection setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public Integer getPort() {
        return port;
    }

    public IpConnection setPort(Integer port) {
        this.port = port;
        return this;
    }
}



