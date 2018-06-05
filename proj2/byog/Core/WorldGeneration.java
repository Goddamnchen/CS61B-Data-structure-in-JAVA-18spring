package byog.Core;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.*;

/**
 * Draws a world consisting of rooms and walls.
 */
public class WorldGeneration {

    private static final int WIDTH = 80;
    private static final int HEIGHT = 30;
    private TETile[][] h;
    private TERenderer background;
    private int hex_size;       //invariant
    private static final Random RANDOM = new Random(5);

    /**
     * 初始化hexworld中每个hex的size以及创建hewworld背景
     */
    public WorldGeneration() {
        background = this.renderBG();
    }

    /**
     * 1.求出每个hex的位置
     * 2.传入addHexagon每个hex的位置。（以及颜色）

    public void tesselate() {
        Position init_p = new Position(WIDTH / 2, HEIGHT - 2 * hex_size);
        Set<Position> pos_set = getPosSet(init_p);
        for (Position p : pos_set) {
            addRandomRoom(p);
        }
    }
    /** 返回装载所有hex坐标的Set
    private HashSet<Position> getPosSet(Position p){
        HashSet<Position> set = new HashSet<>();
        getFirstPos(set,p);
        return set;
    }

    /**
     * divide the hexagonal grid into 3 parts according to each hexagon position's offset to the original position @param p
     * offset <0 , offset = 0, offset >0
     * the hexagons whose position equals == 0 will be taken into account twice,
     * in which case Using  data structure @param S could collect all the positions correctly

    private void getFirstPos(Set<Position> S, Position p){
        int x_offset = 2*hex_size - 1;
        int y_offset = hex_size;
        for(int x = p.x, y = p.y, index = 5; x <= p.x + 2 * x_offset; x = x + x_offset, y = y - y_offset, index -= 1){
            Position each_Rfirst = new Position(x,y);
            getRestPos(S,each_Rfirst,index);
        }
        for(int x = p.x, y = p.y, index = 5; x >= p.x - 2 * x_offset; x = x - x_offset, y = y - y_offset, index -= 1){
            Position each_Lfirst = new Position(x,y);
            getRestPos(S,each_Lfirst,index);
        }
    }

    /** calculate and add the position of each Hexagon from different columns
     * @param S Hexagon position HashSet
     * @param p position of original Hexagon of each column
     * @param index control the Hexagon amount of each column

    private void getRestPos(Set<Position> S, Position p, int index){
        for(int i = index; i > 0; i --){
            Position each = new Position(p.x,p.y);
            S.add(each);
            p.y = p.y - 2*hex_size;
        }

    }
    */

    /**
     * 在随机位置画出随机个Room
     * RANDOM.nextInt()作为Room中的Random seed，链接pseudorandomly
     */
    public void addRandomRoom() {
        List<Grid> gridList= new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Grid room = new Room(h, RANDOM.nextInt());
            if (!isExistedGrid(room, gridList)) {
                room.setTile();
                gridList.add(room);
            }
        }

    }

    private boolean isExistedGrid(Grid g, List<Grid> gridList) {
        for (Grid gridInList : gridList) {
            if (g.isOverlap(gridInList)) return true;
        }
        return false;
    }



    /*求得每个hex row的长度
    private int calRowLength(int rowth) {
        if (rowth >= hex_size) {
            return (3 * hex_size - 2) - (rowth - hex_size) * 2;
        } else {
            return hex_size + 2 * rowth;
        }
    }

    /*求得每个hex row相对hex initial position的offset
    private int calRowOffset(int rowth) {
        if (rowth >= hex_size) {
            return -1 * (hex_size - 1) + (rowth - hex_size);
        } else {
            return -1 * rowth;
        }
    }
    */
    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(3);
        switch (tileNum) {
            case 0: return Tileset.FLOOR;
            case 1: return Tileset.WALL;
            case 2: return Tileset.NOTHING;
            default: return Tileset.NOTHING;
        }
    }

    private TERenderer renderBG() {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        h = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                h[x][y] = Tileset.NOTHING;
            }
        }
        return ter;
    }

    public void renderPattern() {
        background.renderFrame(h);
    }
}
