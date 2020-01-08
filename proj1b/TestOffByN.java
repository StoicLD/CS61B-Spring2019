import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestOffByN
{
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offBy2 = new OffByN(2);
    static CharacterComparator offBy3 = new OffByN(3);
    static CharacterComparator offBy4 = new OffByN(4);

    // Your tests go here.
    @Test
    public void testEqualChars2()
    {
        assertTrue(offBy2.equalChars('a', 'c'));  // true
        assertTrue(offBy2.equalChars('c', 'a'));  // true
        assertTrue(offBy2.equalChars('e', 'g'));  // true

        assertFalse(offBy2.equalChars('C', 'c')); // false
        assertFalse(offBy2.equalChars('a', 'e'));  // false
        assertFalse(offBy2.equalChars('z', 'a'));  // false
        assertFalse(offBy2.equalChars('k', 's'));  // false
    }

    @Test
    public void testEqualChars3()
    {
        assertTrue(offBy3.equalChars('a', 'd'));  // true
        assertTrue(offBy3.equalChars('d', 'a'));  // true
        assertTrue(offBy3.equalChars('4', '1'));  // true
    }

    @Test
    public void testEqualChars4()
    {
        assertTrue(offBy4.equalChars('a', 'e'));  // true
        assertTrue(offBy4.equalChars('e', 'a'));  // true
        assertFalse(offBy4.equalChars('r', 'q'));  // true
    }
}

