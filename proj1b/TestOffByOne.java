import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualChars()
    {
        assertTrue(offByOne.equalChars('a', 'b'));  // true
        assertTrue(offByOne.equalChars('b', 'a'));  // true
        assertTrue(offByOne.equalChars('r', 'q'));  // true

        assertFalse(offByOne.equalChars('C', 'c')); // false
        assertFalse(offByOne.equalChars('a', 'e'));  // false
        assertFalse(offByOne.equalChars('z', 'a'));  // false
        assertFalse(offByOne.equalChars('k', 's'));  // false
    }
}
//Uncomment this class once you've created your CharacterComparator interface and OffByOne class. *