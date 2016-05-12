package marc.arthur.Responses;

import android.graphics.Bitmap;

/**
 * Created by gilbertm on 12/05/2016.
 */
public class PhotoResponse extends Response{

    Bitmap bitmap;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
