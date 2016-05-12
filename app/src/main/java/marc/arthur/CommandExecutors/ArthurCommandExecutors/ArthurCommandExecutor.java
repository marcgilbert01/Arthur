package marc.arthur.CommandExecutors.ArthurCommandExecutors;

import java.nio.ByteBuffer;

import marc.arthur.CommandExecutors.CommandExecutor;
import marc.arthur.DeviceConnections.DeviceConnection;
import marc.arthur.EnvelopeBuilders.EnvelopeBuilder;
import marc.arthur.Commands.Command;
import marc.arthur.Responses.Response;

/**
 * Created by gilbertm on 11/05/2016.
 */
public abstract class ArthurCommandExecutor implements CommandExecutor {

    EnvelopeBuilder  envelopeBuilder;
    DeviceConnection deviceConnection;

    abstract byte getClassType();
    abstract byte getInstructionType();

    abstract byte[] createBytesCommand(Command command);
    abstract Response parseByteResponse(byte[] bytes);


    ResponseListener responseListener;

    @Override
    public void executeCommand(Command command) {

        byte[] bytesCommand = createBytesCommand(command);
        byte[] envelope = envelopeBuilder.buildEnvelope(bytesCommand);
        deviceConnection.connect();
        deviceConnection.sendData(envelope, new DeviceConnection.DataReceiver() {
            @Override
            public void onDataReceived(byte[] bytes) {

                byte[] bytesResponse = envelopeBuilder.extractContent(bytes);
                Response response = parseByteResponse( bytesResponse );
                if( responseListener!=null ){

                    responseListener.onResponseReceived(response);
                }
            }
        });
    }


    @Override
    public void addResponseListener(ResponseListener responseListener) {
        this.responseListener = responseListener;
    }



    // GETTERS SETTERS
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






    class CommandBytesBuilder{

        byte classType;
        byte instructionType;
        byte[] payLoad;

        byte[] build(){
            byte[] bytes = new byte[ 6 + payLoad.length ];

            bytes[0] = classType;
            bytes[1] = instructionType;
            // PAYLOAD LENGTH
            byte[] payLoadLength = ByteBuffer.allocate(4).putInt( payLoad.length ).array();
            System.arraycopy( payLoadLength , 0 , bytes , 2 , 4 );
            // PAYLOAD
            System.arraycopy( payLoad , 0 , bytes , 6 , payLoad.length );

            return bytes;
        }

        public CommandBytesBuilder setClassType(byte classType) {
            this.classType = classType;
            return this;
        }

        public CommandBytesBuilder setInstructionType(byte instructionType) {
            this.instructionType = instructionType;
            return this;
        }

        public CommandBytesBuilder setPayLoad(byte[] payLoad) {
            this.payLoad = payLoad;
            return this;
        }
    }



    class ResponseBytesParser{

        byte classType;
        byte instructionType;
        byte success;
        byte[] payLoad;

        void parse(byte[] bytes){

           classType = bytes[0];
           instructionType = bytes[1];
           success = bytes[2];

           // IF PAYLOAD PRESENT COPY
           if( bytes.length>3 ) {
               // PAYLOAD LENGHT
               byte[] payLoadLengthBytes = new byte[4];
               System.arraycopy(bytes, 3, payLoadLengthBytes, 0, 4);
               ByteBuffer byteBuffer = ByteBuffer.wrap(payLoadLengthBytes);
               int payLoadLength = byteBuffer.getInt();
               // PAYLOAD
               payLoad = new byte[payLoadLength];
               System.arraycopy(bytes, 7, payLoad, 0, payLoadLength);
           }

        }

        public byte getClassType() {
            return classType;
        }

        public byte getInstructionType() {
            return instructionType;
        }

        public byte getSuccess() {
            return success;
        }

        public byte[] getPayLoad() {
            return payLoad;
        }
    }

}













