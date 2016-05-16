package marc.arthur.CommandExecutors.ArthurCommandExecutors;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.nio.ByteBuffer;

import marc.arthur.Commands.Command;
import marc.arthur.Commands.CommandMove;
import marc.arthur.Commands.CommandPhoto;
import marc.arthur.Responses.PhotoResponse;
import marc.arthur.Responses.Response;

/**
 * Created by marc on 15/05/16.
 */
public class ArthurPhotoExecutor extends ArthurCommandExecutor{


    @Override
    byte getClassType() {
        return 0x02;
    }

    @Override
    byte getInstructionType() {
        return 0x04;
    }

    @Override
    byte[] createBytesCommand(Command command) {

        CommandPhoto commandPhoto = (CommandPhoto) command;

        commandByteBuilder.setClassType(getClassType())
                .setInstructionType(getInstructionType());

        return commandByteBuilder.buildPacket();
    }

    @Override
    Response parseByteResponse(byte[] bytes) {

        PhotoResponse response = new PhotoResponse();

        responseByteBuilder.extractPayload(bytes);

        if( responseByteBuilder.getClassType()==getClassType() &&
                responseByteBuilder.getInstructionType()==getInstructionType() &&
                responseByteBuilder.getSuccess()>0
                ){

            response.setSuccess(true);
            byte[] photo = responseByteBuilder.getPayLoad();
            Bitmap bitmap = BitmapFactory.decodeByteArray( photo , 0, photo.length);
            response.setBitmap(bitmap);

        }

        return response;
    }

}
