package marc.arthur.DeviceConnections;

import android.os.SystemClock;

/**
 * Created by gilbertm on 12/05/2016.
 */
public class DummyDeviceConnection implements DeviceConnection{

    @Override
    public void connect() {
    }

    @Override
    public void sendData(byte[] data, DataReceiver dataReceiver) {

        System.out.println( bytesPacketToReadableString( data , "COMMAND" ) );
        SystemClock.sleep(2000);

        // SEND BACK SUCCESS
        // 00 00 00 09 00 00 00 03 01 02 00

        byte[] dummyOkResponse = new byte[11];
        dummyOkResponse[0] = data[0];  // ENVELOPE SEQUENCE
        dummyOkResponse[1] = data[1];  // same has received
        dummyOkResponse[2] = data[2];
        dummyOkResponse[3] = data[3];
        dummyOkResponse[4] = 0x00;     // ENVELOPE PAYLOAD SIZE
        dummyOkResponse[5] = 0x00;     // equals to 3 for empty response
        dummyOkResponse[6] = 0x00;
        dummyOkResponse[7] = 0x03;
        dummyOkResponse[8]  = data[8]; // EMPTY RESPONSE WITH SAME classType
        dummyOkResponse[9]  = data[9]; // EMPTY RESPONSE WITH Same instructionType
        dummyOkResponse[10] = 0x01;    // SUCCESS BYTE


        System.out.println( bytesPacketToReadableString( dummyOkResponse , "RESPONSE" )  );

        dataReceiver.onDataReceived( dummyOkResponse );

    }


    //////LOG PACKET OF DATA
    ////////////////////////////////////////////////////////////////////////
    static public String bytesPacketToReadableString(byte[] bytesPacket, String packetName){

        String toLog = "";

        StringBuilder sbHex = new StringBuilder();
        StringBuilder sbDec = new StringBuilder();
        for (byte b : bytesPacket)
        {
            sbHex.append(String.format("%x ", b));
            sbDec.append(String.format("%d ", b));
        }
        toLog += packetName+" size  ="+bytesPacket.length+"\n";
        toLog += packetName+" (Hex) ="+sbHex.toString()+"\n";
        toLog += packetName+" (Dec) ="+sbDec.toString()+"\n";

        return toLog;
    }


}
