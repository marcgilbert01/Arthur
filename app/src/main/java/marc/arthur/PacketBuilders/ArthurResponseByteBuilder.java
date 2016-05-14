package marc.arthur.PacketBuilders;

import java.nio.ByteBuffer;

/**
 * Created by gilbertm on 13/05/2016.
 *
 *

 Byte Index Command                      Length
 1    Class Type                         1
 2    Instruction Type                   1
 3    Success (non-zero for successful)  1
 4    Payload Length (optional)          4
 8    Payload (optional)

 *
 *
 *
 *
 */
public class ArthurResponseByteBuilder implements PacketBuilder{

    byte classType;
    byte instructionType;
    byte success;
    byte[] payLoad;


    @Override
    public byte[] buildPacket() {

        byte[] packet;
        if( payLoad!=null ) {
            packet = new byte[7 + payLoad.length];
        }
        else{
            packet = new byte[7];
        }

        packet[0] = classType;
        packet[1] = instructionType;
        packet[2] = success;

        if( payLoad!=null ) {
            // PAYLOAD LENGTH
            byte[] payloadLengthBytes = ByteBuffer.allocate(4).putInt(payLoad.length).array();
            System.arraycopy(payloadLengthBytes, 0, packet, 3, 4);
            // PAYLOAD
            System.arraycopy(payLoad, 0, packet, 7, payLoad.length);
        }

        return packet;
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
