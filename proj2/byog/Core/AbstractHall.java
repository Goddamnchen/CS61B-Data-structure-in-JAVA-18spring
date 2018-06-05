package byog.Core;
import byog.TileEngine.TETile;

import java.util.List;

/**
 * Hall is constructed with hall unit
 * Hall unit is length of 3
 * Hall unit can only connect to 1.other hall unit or 2. Room
 * Connecting need merge units
 */
public abstract class AbstractHall extends Grid{
    public AbstractHall(TETile[][] worldTile, int seed) {
        super(worldTile, seed);
        this.minLength = 2;
        this.maxLength = 2;
    }

    protected abstract void  setLBPosition();
    protected abstract void  setRTPosition();



}
