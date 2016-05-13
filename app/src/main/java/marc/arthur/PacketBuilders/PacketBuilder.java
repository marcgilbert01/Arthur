package marc.arthur.PacketBuilders;

/**
 * Created by gilbertm on 13/05/2016.
 */
public interface PacketBuilder {

    public byte[] buildPacket();

    public byte[] extractPayload(byte[] packet);


}
