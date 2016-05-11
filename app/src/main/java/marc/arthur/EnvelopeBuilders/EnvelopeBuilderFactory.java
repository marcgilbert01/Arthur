package marc.arthur.EnvelopeBuilders;

import java.util.HashMap;

/**
 * Created by gilbertm on 11/05/2016.
 */
public class EnvelopeBuilderFactory {

    static HashMap<String,EnvelopeBuilder> envelopeBuilders = new HashMap<>();
    static {

        envelopeBuilders.put( "Arthur" , new ArthurEnvelopeBuilder() );
    }


    public EnvelopeBuilder createEnvelopeBuilder(String envelopeName){

        EnvelopeBuilder envelopeBuilder = null;

        EnvelopeBuilder envelopeBuilderProto = envelopeBuilders.get(envelopeName);

        if( envelopeBuilderProto!=null ){

            try {
                envelopeBuilder = envelopeBuilderProto.getClass().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }

        return envelopeBuilder;
    }




}
