package marc.arthur.EnvelopeBuilders;

import java.nio.ByteBuffer;

/**
 * Created by gilbertm on 11/05/2016.
 */
public class ArthurEnvelopeBuilder implements EnvelopeBuilder{

    @Override
    public byte[] buildEnvelope(int sequenceNumber , byte[] content) {

/*
        1 Sequence Number (int) 4
        5 Length of command (int) 4
        9 Payload varies
*/
        byte[] envelopeBytes = new byte[ 8 + content.length ];
        // ENVELOPE SEQUENCE NUMBER
        byte[] sequenceNumberBytes = ByteBuffer.allocate(4).putInt( sequenceNumber ).array();
        System.arraycopy( sequenceNumberBytes , 0 , envelopeBytes , 0, 4 );
        //
        byte[]



        return new byte[0];
    }

    @Override
    public byte[] extractContent(byte[] envelope) {
        return new byte[0];
    }


}
