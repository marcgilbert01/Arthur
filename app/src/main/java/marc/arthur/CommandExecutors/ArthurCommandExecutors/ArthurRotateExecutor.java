package marc.arthur.CommandExecutors.ArthurCommandExecutors;

import java.nio.ByteBuffer;

import marc.arthur.Commands.Command;
import marc.arthur.Commands.CommandRotate;
import marc.arthur.Responses.Response;

/**
 * Created by gilbertm on 13/05/2016.
 */
public class ArthurRotateExecutor extends ArthurCommandExecutor{


    @Override
    byte getClassType() {
        return 0x01;
    }

    @Override
    byte getInstructionType() {
        return 0x02;
    }

    @Override
    byte[] createBytesCommand(Command command) {

        CommandRotate commandRotate = (CommandRotate) command;

        byte[] degreesInBytes = ByteBuffer.allocate(4).putInt( commandRotate.getDegrees() ).array();

        commandByteBuilder.setClassType(getClassType())
                .setInstructionType(getInstructionType())
                .setPayLoad(degreesInBytes);

        return commandByteBuilder.buildPacket();
    }

    @Override
    Response parseByteResponse(byte[] bytes) {

        Response response = new Response();

        responseByteBuilder.extractPayload(bytes);

        if( responseByteBuilder.getClassType()==getClassType() &&
            responseByteBuilder.getInstructionType()==getInstructionType() &&
            responseByteBuilder.getSuccess()>0
           ){

            response.setSuccess(true);
        }

        return null;
    }
}
