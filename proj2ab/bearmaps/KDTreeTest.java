package bearmaps;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class KDTreeTest
{
    @Test
    public void sanityTest1()
    {
        List<Point> list = List.of(new Point(10,6),
                new Point(7,15), new Point(20,3),
                new Point(12, 2), new Point(30, 7),
                new Point(7, 15), new Point(6,10),
                new Point(2, 20));
        KDTree kdTree = new KDTree(list);
        BTreePrinter.printNode(kdTree.getRoot(), 9);
    }

    @Test
    public void sanityTest2()
    {
        List<Point> list = List.of(new Point(10,6),
                new Point(20,3),
                new Point(12, 2), new Point(30, 7),
                new Point(7, 15));
        KDTree kdTree = new KDTree(list);
        BTreePrinter.printNode(kdTree.getRoot(), 9);
    }
}
