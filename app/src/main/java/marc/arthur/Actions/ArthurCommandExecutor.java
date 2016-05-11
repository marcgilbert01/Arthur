package marc.arthur.Actions;

import marc.arthur.DeviceConnections.DeviceConnection;
import marc.arthur.EnvelopeBuilders.EnvelopeBuilder;
import marc.arthur.ParamsIn.ParamsIn;
import marc.arthur.ResponseData;

/**
 * Created by gilbertm on 11/05/2016.
 */
public abstract class ArthurCommandExecutor implements CommandSender{

    EnvelopeBuilder  envelopeBuilder;
    DeviceConnection deviceConnection;

    abstract Byte[] createBytesCommand(ParamsIn paramsIn);
    abstract ResponseData parseByteResponse(Byte[] bytes);

    ResponseListener responseListener;

    @Override
    public void sendCommand(ParamsIn paramsIn) {

        Byte[] bytesCommand = createBytesCommand(paramsIn);

        Byte[] envelope = envelopeBuilder.buildEnvelope(bytesCommand);

        deviceConnection.connect();

        deviceConnection.sendData(envelope, new DeviceConnection.DataReceiver() {
            @Override
            public void onDataReceived(Byte[] bytes) {

                Byte[] bytesResponse = envelopeBuilder.extractContent(bytes);
                ResponseData responseData = parseByteResponse( bytesResponse );
                if( responseListener!=null ){

                    responseListener.onResponseReceived(responseData);
                }
            }
        });

    }


    @Override
    public void addResponseListener(ResponseListener responseListener) {
        this.responseListener = responseListener;
    }




    public EnvelopeBuilder getEnvelopeBuilder() {
        return envelopeBuilder;
    }

    public void setEnvelopeBuilder(EnvelopeBuilder envelopeBuilder) {
        this.envelopeBuilder = envelopeBuilder;
    }

    public DeviceConnection getDeviceConnection() {
        return deviceConnection;
    }

    public void setDeviceConnection(DeviceConnection deviceConnection) {
        this.deviceConnection = deviceConnection;
    }



}
