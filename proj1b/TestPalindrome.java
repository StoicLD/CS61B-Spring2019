import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("abba"));
        assertTrue(palindrome.isPalindrome("raccar"));
        assertTrue(palindrome.isPalindrome("bBbBb"));
    }

    @Test
    public void testNotPalindrome() {
        assertFalse(palindrome.isPalindrome("Dd"));
        assertFalse(palindrome.isPalindrome("aAaa"));
        assertFalse(palindrome.isPalindrome("aaaaaddddccaaaa"));
    }

    @Test
    public void testIsPalindromeRecursive() {
        assertTrue(palindrome.isPalindromeRecursive(""));
        assertTrue(palindrome.isPalindromeRecursive("a"));
        assertTrue(palindrome.isPalindromeRecursive("abba"));
        assertTrue(palindrome.isPalindromeRecursive("raccar"));
        assertTrue(palindrome.isPalindromeRecursive("bBbBb"));
    }

    @Test
    public void testNotPalindromeRecursive() {
        assertFalse(palindrome.isPalindromeRecursive("Dd"));
        assertFalse(palindrome.isPalindromeRecursive("aAaa"));
        assertFalse(palindrome.isPalindromeRecursive("aaaaaddddccaaaa"));
    }

    @Test
    public void testOffByOnePaIsPalindrome()
    {
        OffByOne offByOne = new OffByOne();

        assertTrue(palindrome.isPalindrome("adcb", offByOne));
        assertTrue(palindrome.isPalindrome("adscb", offByOne));
        assertTrue(palindrome.isPalindrome("a", offByOne));
        assertTrue(palindrome.isPalindrome("", offByOne));

        assertFalse(palindrome.isPalindrome("adcbe", offByOne));
        assertFalse(palindrome.isPalindrome("em13nf", offByOne));
    }

}
//Uncomment this class once you've created your Palindrome class.