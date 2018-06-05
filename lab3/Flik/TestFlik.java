import static org.junit.Assert.*;
import org.junit.Test;
public class TestFlik {
    @Test
    public void testBug(){
        int a = 10;
        int b = 10;
        boolean actual = Flik.isSameNumber(a,b);
        assertTrue(actual);
    }
}
