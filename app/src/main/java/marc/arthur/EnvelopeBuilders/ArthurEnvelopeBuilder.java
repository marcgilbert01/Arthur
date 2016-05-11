package marc.arthur.EnvelopeBuilders;

/**
 * Created by gilbertm on 11/05/2016.
 */
public class ArthurEnvelopeBuilder implements EnvelopeBuilder{

    @Override
    public Byte[] buildEnvelope(Byte[] content) {
        return new Byte[0];
    }

    @Override
    public Byte[] extractContent(Byte[] envelope) {
        return new Byte[0];
    }
}
