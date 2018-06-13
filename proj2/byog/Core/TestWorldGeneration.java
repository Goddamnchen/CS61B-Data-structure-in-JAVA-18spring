package byog.Core;
import byog.TileEngine.TERenderer;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
public class TestWorldGeneration {
    public void TestAddRoom(){
        WorldGeneration w = new WorldGeneration();
        w.addRandomRoom();
        w.renderPattern();
    }
    public static void main(String args[]){
        TestWorldGeneration test = new TestWorldGeneration();
        test.TestAddRoom();
    }
}
