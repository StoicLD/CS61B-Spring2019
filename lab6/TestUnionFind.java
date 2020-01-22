import org.junit.Test;

import static org.junit.Assert.*;

public class TestUnionFind
{
    @Test
    public void testUnionFind()
    {
        UnionFind union = new UnionFind(7);
        //Test size
        for(int i = 0; i < 7; i ++)
        {
            assertEquals(1, union.sizeOf(i));
        }
        System.out.println("Passed Test1: initial size test");

        //Test Union
        union.union(1, 0);
        union.union(1, 2);
        int[] expected1 = new int[]{-3, 0, 0, -1, -1, -1, -1};
        assertTrue(union.connected(1, 2));
        assertArrayEquals(expected1, union.getParent());
        System.out.println("Passed Test2: Union Test1");

        union.union(4, 3);
        union.union(2, 4);
        int[] expected2 = new int[]{-5, 0, 0, 0, 3, -1, -1};
        assertTrue(union.connected(1, 3));
        assertTrue(union.connected(2, 4));
        assertArrayEquals(expected2, union.getParent());
        System.out.println("Passed Test2: Union Test2");


    }

    public static void main()
    {

    }
}
