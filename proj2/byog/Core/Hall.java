package byog.Core;

import byog.TileEngine.TETile;

public class Hall extends AbstractHall {
    public Hall(TETile[][] worldTile, int seed){
        super(worldTile, seed);
        setLBPosition();
        setRTPosition();
    }

    @Override
    protected void setLBPosition(){
        LeftBottomPosition().x = RandomUtils.uniform(RoomSeed, 0, WIDTH - maxLength);
        LeftBottomPosition().y = RandomUtils.uniform(RoomSeed, 0, HEIGHT - maxLength);
    }

    @Override
    /**
     * maxLength == min Length : Square Room
     * maxLength !== min : Rectangular Room
     */
    protected void setRTPosition(){
        if (this.minLength  != this.maxLength || this.minLength != 2) {
            throw new IllegalArgumentException("both min and max length of a hall unit are euqal to 2 ");
        }
        int widthOffset = this.minLength;
        int heightOffset = this.maxLength;
        RightTopPosition().x = LeftBottomPosition().x + widthOffset;
        RightTopPosition().y = LeftBottomPosition().y + heightOffset;
    }


}
