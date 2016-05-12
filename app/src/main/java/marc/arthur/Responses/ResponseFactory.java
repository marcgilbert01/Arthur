package marc.arthur.Responses;

/**
 * Created by gilbertm on 12/05/2016.
 */
public class ResponseFactory {

    enum ResponseProto{

        EMPTY_RESPONSE( new Response()),
        PHOTO_RESPONSE( new PhotoResponse())
        ;

        Response response;

        ResponseProto(Response response) {
            this.response = response;
        }
    }


    public Response createResponse(ResponseProto responseProto){

        Response response = null;
        try {
            response = responseProto.response.getClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return response;
    }



}
