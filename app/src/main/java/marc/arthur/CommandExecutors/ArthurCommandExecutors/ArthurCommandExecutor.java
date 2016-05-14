package marc.arthur.CommandExecutors.ArthurCommandExecutors;

import java.nio.ByteBuffer;

import marc.arthur.CommandExecutors.CommandExecutor;
import marc.arthur.DeviceConnections.DeviceConnection;
import marc.arthur.Commands.Command;
import marc.arthur.PacketBuilders.ArthurCommandByteBuilder;
import marc.arthur.PacketBuilders.ArthurEnvelopeBuilder;
import marc.arthur.PacketBuilders.ArthurResponseByteBuilder;
import marc.arthur.PacketBuilders.PacketBuilder;
import marc.arthur.Responses.Response;

/**
 * Created by gilbertm on 11/05/2016.
 */
public abstract class ArthurCommandExecutor implements CommandExecutor {

    ArthurEnvelopeBuilder envelopeBuilder;
    ArthurCommandByteBuilder commandByteBuilder;
    ArthurResponseByteBuilder responseByteBuilder;

    DeviceConnection deviceConnection;

    abstract byte getClassType();
    abstract byte getInstructionType();

    abstract byte[] createBytesCommand(Command command);
    abstract Response parseByteResponse(byte[] bytes);


    ResponseListener responseListener;

    @Override
    public void executeCommand(Command command) {

        byte[] bytesCommand = createBytesCommand(command);

        envelopeBuilder.setPayload(bytesCommand);
        envelopeBuilder.setSequenceNumber(command.getSequenceNumber());
        byte[] envelope = envelopeBuilder.buildPacket();

        deviceConnection.connect();
        deviceConnection.sendData(envelope, new DeviceConnection.DataReceiver() {

            @Override
            public void onDataReceived(byte[] bytes) {

                byte[] bytesResponse = envelopeBuilder.extractPayload(bytes);
                Response response = parseByteResponse( bytesResponse );
                if( responseListener!=null ){

                    responseListener.onResponseReceived(response);
                }
            }

        });
    }

    @Override
    public void setResponseListener(ResponseListener responseListener) {
        this.responseListener = responseListener;
    }

    // GETTERS SETTERS
    public ArthurCommandExecutor setEnvelopeBuilder(ArthurEnvelopeBuilder envelopeBuilder) {
        this.envelopeBuilder = envelopeBuilder;
        return this;
    }

    public ArthurCommandExecutor setCommandByteBuilder(ArthurCommandByteBuilder commandByteBuilder) {
        this.commandByteBuilder = commandByteBuilder;
        return this;
    }

    public ArthurCommandExecutor setResponseByteBuilder(ArthurResponseByteBuilder responseByteBuilder) {
        this.responseByteBuilder = responseByteBuilder;
        return this;
    }

    public ArthurCommandExecutor setDeviceConnection(DeviceConnection deviceConnection) {
        this.deviceConnection = deviceConnection;
        return this;
    }
}













