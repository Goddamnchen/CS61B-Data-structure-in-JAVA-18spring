import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = Palindrome.choseOffByOne();

    // Your tests go here.
    @Test
    public void testEqualChars() {
        char input_x = 'a';
        char input_y = 'b';
        boolean actual = offByOne.equalChars(input_x, input_y);
        assertTrue(actual);

        char input_x1 = 'B';
        char input_y1 = 'A';
        boolean actual2 = offByOne.equalChars(input_x1, input_y1);
        assertTrue(actual2);
    }


}
