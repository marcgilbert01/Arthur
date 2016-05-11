package marc.arthur.EnvelopeBuilders;

/**
 * Created by gilbertm on 11/05/2016.
 */
public interface EnvelopeBuilder {

    public Byte[] buildEnvelope(Byte[] content);

    public Byte[] extractContent(Byte[] envelope );

}


