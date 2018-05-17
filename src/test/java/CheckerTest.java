import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CheckerTest {
    @Test(expected = IllegalArgumentException.class)
    public void nullShouldThrowException(){
        Checker checker = new Checker();
        String word = null;
        boolean isPalindrome = checker.isPalindrome(word);
    }
    @Test
    public void emptyStringIsPalindrome(){
        Checker checker = new Checker();
        String word = "";
        boolean isPalindrome = checker.isPalindrome(word);
        assertTrue(isPalindrome);
    }
}
