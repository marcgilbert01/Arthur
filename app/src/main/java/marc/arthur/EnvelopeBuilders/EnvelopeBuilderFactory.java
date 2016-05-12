package marc.arthur.EnvelopeBuilders;

import java.util.HashMap;

/**
 * Created by gilbertm on 11/05/2016.
 */
public class EnvelopeBuilderFactory {

    public enum EnvelopBuilderType{

        ARTHUR( new ArthurEnvelopeBuilder() );

        EnvelopeBuilder envelopeBuilder;

        EnvelopBuilderType(EnvelopeBuilder envelopeBuilder) {
            this.envelopeBuilder = envelopeBuilder;
        }
    }



    public EnvelopeBuilder createEnvelopeBuilder(EnvelopBuilderType envelopBuilderType){

        EnvelopeBuilder envelopeBuilder = null;
        try {
            envelopeBuilder = envelopBuilderType.envelopeBuilder.getClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return envelopeBuilder;
    }




}
