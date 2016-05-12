package marc.arthur.EnvelopeBuilders;

/**
 * Created by gilbertm on 11/05/2016.
 */
public interface EnvelopeBuilder {

    public byte[] buildEnvelope(int sequenceNumber , byte[] content);

    public byte[] extractContent(byte[] envelope );

}


