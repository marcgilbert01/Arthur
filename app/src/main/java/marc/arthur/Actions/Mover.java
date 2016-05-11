package marc.arthur.Actions;

import marc.arthur.ParamsIn.ParamsIn;
import marc.arthur.ResponseData;

/**
 * Created by gilbertm on 11/05/2016.
 */
public class Mover extends ArthurCommandExecutor {



    @Override
    Byte[] createBytesCommand(ParamsIn paramsIn) {
        return new Byte[0];
    }

    @Override
    ResponseData parseByteResponse(Byte[] bytes) {
        return null;
    }

}
