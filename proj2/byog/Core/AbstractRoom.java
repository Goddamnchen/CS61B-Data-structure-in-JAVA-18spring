package byog.Core;

import byog.TileEngine.TETile;


public abstract class AbstractRoom extends Grid{

    protected AbstractRoom(TETile[][] worldTile, int seed) {
        super(worldTile, seed);
        this.minLength = 3;
        this.maxLength = RandomUtils.uniform(RoomSeed, this.minLength, 8);
    }

    protected abstract void setLBPosition();
    protected abstract void setRTPosition();

}
