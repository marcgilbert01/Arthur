package marc.arthur.PacketBuilders;

/**
 * Created by gilbertm on 13/05/2016.
 */
public class PacketBuilderFactory {


    public enum PacketBuilderType{

        ARTHUR_ENVELOPE( new ArthurEnvelopeBuilder() ),
        ARTHUR_COMMAND_BYTE_BUILDER( new ArthurCommandByteBuilder() ),
        ARTHUR_RESPONSE_BYTE_BUILDER( new ArthurResponseByteBuilder() ),
        ;


        PacketBuilder packetBuilder;

        PacketBuilderType(PacketBuilder packetBuilder) {
            this.packetBuilder = packetBuilder;
        }
    }


    public PacketBuilder createPacketBuilder(PacketBuilderType packetBuilderType){

        PacketBuilder packetBuilder = null;

        try {
            packetBuilder = packetBuilderType.packetBuilder.getClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return packetBuilder;
    }





}
