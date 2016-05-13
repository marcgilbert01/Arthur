package marc.arthur.Commands;

/**
 * Created by gilbertm on 13/05/2016.
 */
public class CommandRotate extends Command{

    int degrees;

    public int getDegrees() {
        return degrees;
    }

    public CommandRotate setDegrees(int degrees) {
        this.degrees = degrees;
        return this;
    }
}
