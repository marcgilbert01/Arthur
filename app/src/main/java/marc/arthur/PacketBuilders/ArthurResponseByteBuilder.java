package marc.arthur.PacketBuilders;

import java.nio.ByteBuffer;

/**
 * Created by gilbertm on 13/05/2016.
 */
public class ArthurResponseByteBuilder implements PacketBuilder{

    byte classType;
    byte instructionType;
    byte success;
    byte[] payLoad;


    @Override
    public byte[] buildPacket() {

        return new byte[0];
    }

    @Override
    public byte[] extractPayload(byte[] packet) {

        classType = packet[0];
        instructionType = packet[1];
        success = packet[2];

        // IF PAYLOAD PRESENT COPY
        if( packet.length>3 ) {
            // PAYLOAD LENGHT
            byte[] payLoadLengthBytes = new byte[4];
            System.arraycopy(packet, 3, payLoadLengthBytes, 0, 4);
            ByteBuffer byteBuffer = ByteBuffer.wrap(payLoadLengthBytes);
            int payLoadLength = byteBuffer.getInt();
            // PAYLOAD
            payLoad = new byte[payLoadLength];
            System.arraycopy(packet, 7, payLoad, 0, payLoadLength);
        }

        return payLoad;
    }

    public byte getClassType() {
        return classType;
    }

    public byte getInstructionType() {
        return instructionType;
    }

    public byte getSuccess() {
        return success;
    }

    public byte[] getPayLoad() {
        return payLoad;
    }
}
