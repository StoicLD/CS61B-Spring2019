package bearmaps;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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

    @Test
    public void nearestTest1()
    {
        List<Point> list = List.of(new Point(2,3),
                new Point(4,2),
                new Point(4, 5), new Point(3, 3),
                new Point(4, 4), new Point(1, 5));
        KDTree kdTree = new KDTree(list);
        assertEquals(new Point(1, 5), kdTree.nearest(0, 7));
        System.out.println(KDTree.getTimes());
    }

    @Test
    public void nearestTest2()
    {
        Random rng = new Random(); // Ideally just create one instance globally
        final double bound = 1000;
        //随机测试
        LinkedList<Point> list = new LinkedList<>();
        int totalNum = 10000;
        for(int i = 0; i < totalNum; i++)
        {
            list.add(new Point(rng.nextDouble() * bound, rng.nextDouble() * bound));
        }

        NaivePointSet nps = new NaivePointSet(list);
        KDTree kdt = new KDTree(list);

        System.out.println("NaviePointSet calling time is : " + totalNum);
        for(int i = 0; i < 10; i++)
        {
            Point targetPoint = new Point(rng.nextDouble() * bound, rng.nextDouble() * bound);
            assertEquals(nps.nearest(targetPoint.getX(), targetPoint.getY()), kdt.nearest(targetPoint.getX(), targetPoint.getY()));
            System.out.println("KdTree calling time is : " + KDTree.getTimes());
        }
    }
}
