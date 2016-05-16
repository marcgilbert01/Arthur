package marc.arthur.CommandExecutors.ArthurCommandExecutors;

import java.nio.ByteBuffer;

import marc.arthur.Commands.Command;
import marc.arthur.Commands.CommandPhoto;
import marc.arthur.Commands.CommandRotate;
import marc.arthur.Commands.CommandSpeak;
import marc.arthur.Responses.Response;

/**
 * Created by marc on 16/05/16.
 */
public class ArthurSpeechExecutor extends  ArthurCommandExecutor{


    @Override
    byte getClassType() {
        return 0x02;
    }

    @Override
    byte getInstructionType() {
        return 0x05;
    }

    @Override
    byte[] createBytesCommand(Command command) {

        CommandSpeak commandSpeak = (CommandSpeak) command;

        byte[] speechAsBytes = commandSpeak.getSpeech().getBytes();

        commandByteBuilder.setClassType(getClassType())
                .setInstructionType(getInstructionType())
                .setPayLoad( speechAsBytes );

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

        return response;

    }
}
