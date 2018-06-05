package byog.lab5;
import javafx.geometry.Pos;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.*;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    private TETile[][] h;
    private TERenderer background;
    private int hex_size;       //invariant
    private static final Random RANDOM = new Random(100);

    /**
     * 初始化hexworld中每个hex的size以及创建hewworld背景
     */
    public HexWorld(int size) {
        if (size < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }
        this.hex_size = size;
        background = this.renderBG();
    }

    /**
     * 1.求出每个hex的位置
     * 2.传入addHexagon每个hex的位置。（以及颜色）
     */
    public void tesselate() {
        Position init_p = new Position(WIDTH / 2, HEIGHT - 2 * hex_size);
        Set<Position> pos_set = getPosSet(init_p);
        for (Position p : pos_set) {
            addHexagon(p);
        }
    }
    /** 返回装载所有hex坐标的Set */
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
     */
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
     */
    private void getRestPos(Set<Position> S, Position p, int index){
        for(int i = index; i > 0; i --){
            Position each = new Position(p.x,p.y);
            S.add(each);
            p.y = p.y - 2*hex_size;
        }

    }

    /**
     * 以每一row（y）为单位，计算出当前row六边形的content的length与offset
     * offset为横坐标（x)
     * @param p 传入六边形起始坐标点
     */
    public void addHexagon(Position p) {
        TETile tile_color = randomTile();
        int row_length;
        int row_offset;
        int total_rows = 2 * hex_size;
        for (int y = p.y, rowth = 0; y < total_rows + p.y; y++, rowth++) {
            row_length = calRowLength(rowth);
            row_offset = calRowOffset(rowth);
            for (int x = p.x + row_offset; x < p.x + row_offset + row_length; x++) {
                h[x][y] = tile_color;
            }
        }
    }

    /*求得每个hex row的长度*/
    private int calRowLength(int rowth) {
        if (rowth >= hex_size) {
            return (3 * hex_size - 2) - (rowth - hex_size) * 2;
        } else {
            return hex_size + 2 * rowth;
        }
    }

    /*求得每个hex row相对hex initial position的offset*/
    private int calRowOffset(int rowth) {
        if (rowth >= hex_size) {
            return -1 * (hex_size - 1) + (rowth - hex_size);
        } else {
            return -1 * rowth;
        }
    }
    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(5);
        switch (tileNum) {
            case 0: return Tileset.TREE;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.GRASS;
            case 3: return Tileset.MOUNTAIN;
            case 4: return Tileset.SAND;
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