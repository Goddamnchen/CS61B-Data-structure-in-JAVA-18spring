package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.List;
import java.util.Random;

public class Grid {
    protected static final int WIDTH = 80;
    protected static final int HEIGHT = 30;
    protected TETile[][] roomTile;
    protected Random RoomSeed;
    protected int maxLength;
    protected int minLength;
    private Position LeftBottomPosition = new Position(0, 0);
    private Position RightTopPosition = new Position(0, 0);

    protected Grid(TETile[][] worldTile, int seed){
        this.roomTile = worldTile;
        this.RoomSeed = new Random(seed);
    }
    protected Position LeftBottomPosition(){
        return this.LeftBottomPosition;
    }

    protected Position RightTopPosition(){
        return this.RightTopPosition;
    }

    protected int width(){
        return RightTopPosition.x - LeftBottomPosition.x + 1;
    }

    protected int height(){
        return RightTopPosition.y - LeftBottomPosition.y + 1;
    }

    /**
     *
     * @param grid
     * @return 0 == no connection
     * @return 1 == overlap
     * @return -1 == connected
     */
    public boolean isOverlap(Grid grid){
        if (this.LeftBottomPosition().y > grid.RightTopPosition().y);
        else if (this.RightTopPosition().y < grid.LeftBottomPosition().y);
        else if (this.RightTopPosition().x < grid.LeftBottomPosition().x);
        else if (this.LeftBottomPosition().x > grid.RightTopPosition().x);
        else {
            return true;
        }
        return false;
    }
    public void setTile(){
        for (int x = LeftBottomPosition().x; x <= RightTopPosition().x; x++) {
            for (int y = LeftBottomPosition().y; y <= RightTopPosition().y; y++) {
                if (isSetWallTile(x,y)) roomTile[x][y] = Tileset.WALL;
                else roomTile[x][y] = Tileset.FLOOR;
            }
        }

    }

    private boolean isSetWallTile(int x, int y){
        if (x == LeftBottomPosition().x || x == RightTopPosition().x) {
            return true;
        }
        else if (y == LeftBottomPosition().y || y == RightTopPosition().y) {
            return true;
        }
        else return false;
    }

}
