package marc.arthur.Commands;

import marc.arthur.CommandExecutors.CommandExecutor;

/**
 * Created by gilbertm on 12/05/2016.
 */
public class CommandMove extends Command{

    Integer distance;

    public Integer getDistance() {
        return distance;
    }

    public CommandMove setDistance(Integer distance) {
        this.distance = distance;
        return this;
    }
}
