package marc.arthur.CommandExecutors.ArthurCommandExecutors;

import java.nio.ByteBuffer;

import marc.arthur.CommandExecutors.ArthurCommandExecutors.ArthurCommandExecutor;
import marc.arthur.Commands.Command;
import marc.arthur.Commands.CommandMove;
import marc.arthur.PacketBuilders.ArthurCommandByteBuilder;
import marc.arthur.PacketBuilders.ArthurResponseByteBuilder;
import marc.arthur.Responses.Response;

/**
 * Created by gilbertm on 11/05/2016.
 *
 1 Class Type 1
 2 Instruction Type 1
 3 Success (non-zero for successful) 1
 4 Payload Length (optional) 4
 8 Payload (optional)
 *
 *
 */
public class ArthurMoveExecutor extends ArthurCommandExecutor {

    @Override
    byte[] createBytesCommand(Command command) {

        CommandMove commandMove = (CommandMove) command;

        byte[] distanceInBytes = ByteBuffer.allocate(4).putInt( commandMove.getDistance() ).array();

        commandByteBuilder.setClassType(getClassType())
                .setInstructionType(getInstructionType())
                .setPayLoad(distanceInBytes);

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

/*

*/
        return response;
    }




    @Override
    byte getClassType() {
        return 0x01;
    }

    @Override
    byte getInstructionType() {
        return 0x01;
    }

}
