package byog.lab5;
import byog.TileEngine.TERenderer;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestHexWorld {
    public static void main(String args[]){
        HexWorld a = new HexWorld(3);
        a.tesselate();
        a.renderPattern();
    }
}
