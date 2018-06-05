package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.List;

public class Room extends AbstractRoom{
    /** 因为初始化的顺序:
     * 1.super.constructor  -->
     * 2.this.variables(e.g.LeftBottomPosition
     * 3.this.constructor
     * 所以Room object.isOverlap()用的虽然是父类的方法，其方法内的变量却已经为被子类constructor初始化从而改变了
     * */
    public Room(TETile[][] worldTile, int seed){
        super(worldTile, seed);
        setLBPosition();
        setRTPosition();
    }


    @Override
    protected  void setLBPosition(){
        LeftBottomPosition().x = RandomUtils.uniform(RoomSeed, 0, WIDTH - maxLength);
        LeftBottomPosition().y = RandomUtils.uniform(RoomSeed, 0, HEIGHT - maxLength);
    }

    @Override
    /**
     * maxLength == min Length : Square Room
     * maxLength !== min : Rectangular Room
     */
    protected void setRTPosition(){
        int widthOffset;
        int heightOffset;
        if (this.maxLength == this.minLength) {
            widthOffset = this.minLength;
            heightOffset = this.minLength;
        } else {
            widthOffset = RandomUtils.uniform(RoomSeed, this.minLength, this.maxLength);
            heightOffset = RandomUtils.uniform(RoomSeed, this.minLength, this.maxLength);
        }
        RightTopPosition().x = LeftBottomPosition().x + widthOffset;
        RightTopPosition().y = LeftBottomPosition().y + heightOffset;
    }


}
