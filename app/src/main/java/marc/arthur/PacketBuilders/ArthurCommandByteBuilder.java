package marc.arthur.PacketBuilders;

import java.nio.ByteBuffer;

/**
 * Created by gilbertm on 13/05/2016.
 */
public class ArthurCommandByteBuilder implements PacketBuilder{

    byte classType;
    byte instructionType;
    byte[] payLoad;

    @Override
    public byte[] buildPacket() {

        byte[] bytes = new byte[ 6 + payLoad.length ];

        bytes[0] = classType;
        bytes[1] = instructionType;
        // PAYLOAD LENGTH
        byte[] payLoadLength = ByteBuffer.allocate(4).putInt( payLoad.length ).array();
        System.arraycopy( payLoadLength , 0 , bytes , 2 , 4 );
        // PAYLOAD
        System.arraycopy( payLoad , 0 , bytes , 6 , payLoad.length );

        return bytes;
    }

    @Override
    public byte[] extractPayload(byte[] packet) {

        return new byte[0];

    }

    public byte getClassType() {
        return classType;
    }

    public ArthurCommandByteBuilder setClassType(byte classType) {
        this.classType = classType;
        return this;
    }

    public byte getInstructionType() {
        return instructionType;
    }

    public ArthurCommandByteBuilder setInstructionType(byte instructionType) {
        this.instructionType = instructionType;
        return this;
    }

    public byte[] getPayLoad() {
        return payLoad;
    }

    public ArthurCommandByteBuilder setPayLoad(byte[] payLoad) {
        this.payLoad = payLoad;
        return this;
    }
}
