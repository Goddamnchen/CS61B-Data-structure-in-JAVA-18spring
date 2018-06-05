import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    /**
     * Test of splitting String to Duque function;
     */
    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }
    /**
     * Test of identifying the Palindrome style
     */
    @Test
    public void testIsPalindrome(){
        String input1 = "abcba";
        String input2 = "a";
        String input3 = "001";

        Boolean actual1 = palindrome.isPalindrome(input1);
        assertTrue(actual1);

        Boolean actual2 = palindrome.isPalindrome(input2);
        assertTrue(actual2);

        Boolean actual3 = palindrome.isPalindrome(input3);
        assertFalse(actual3);
    }

    /**
     * Test of identifying whether the string is off-by-1 Palindrome style
     */
    @Test
    public void testIsOffByOnePalindrome(){
        String input1 = "flake";
        CharacterComparator obo_input1 = Palindrome.choseOffByOne();
        boolean actual = palindrome.isPalindrome(input1, obo_input1);
        assertTrue(actual);

        String input2 = "Abcb";
        boolean actual2 = palindrome.isPalindrome(input2, obo_input1);
        assertFalse(actual2);

    }
}
