import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Assert;
import org.junit.Test;

public class TestSortAlgs {

    @Test
    public void testQuickSort()
    {
        Queue<Integer> q1 = new Queue<>();
        q1.enqueue(7);
        q1.enqueue(2);
        q1.enqueue(4);
        q1.enqueue(3);
        q1.enqueue(11);
        q1.enqueue(7);
        q1.enqueue(17);
        q1.enqueue(27);
        q1.enqueue(1);

        Queue<Integer> sortedQ1 = QuickSort.quickSort(q1);
        Assert.assertTrue(isSorted(sortedQ1));
    }

    @Test
    public void timingTestQuickSort()
    {
        Stopwatch stopwatch = new Stopwatch();
        Queue<Integer> q1 = new Queue<>();
        int maxSize = 10000;
        while((maxSize--) >= 0)
        {
            q1.enqueue(StdRandom.uniform(10000));
        }
        double start = stopwatch.elapsedTime();
        Queue<Integer> sortedQueue = QuickSort.quickSort(q1);
        Assert.assertTrue(isSorted(sortedQueue));
        double end  = stopwatch.elapsedTime();
        System.out.println("QuickSort consuming time for 10000 is: " + (end - start));

        Queue<Integer> q2 = new Queue<>();
        maxSize = 100000;
        while((maxSize--) >= 0)
        {
            q2.enqueue(StdRandom.uniform(100000));
        }
        start = stopwatch.elapsedTime();
        Queue<Integer> sortedQueue2 = QuickSort.quickSort(q2);
        Assert.assertTrue(isSorted(sortedQueue2));
        end  = stopwatch.elapsedTime();
        System.out.println("QuickSort consuming time for 100000 is: " + (end - start));
    }

    @Test
    public void testMergeSort() {
        MergeSort mergeSort = new MergeSort();

        Queue<Integer> q1 = new Queue<>();
        Queue<Integer> q2 = new Queue<>();
        q1.enqueue(7);
        q1.enqueue(3);
        q1.enqueue(5);
        q1.enqueue(2);
        q1.enqueue(4);
        int q1Size = q1.size();

        q2.enqueue(3);
        q2.enqueue(7);
        q2.enqueue(1);
        q2.enqueue(2);
        q2.enqueue(8);
        int q2Size = q2.size();

        Queue<Integer> q1Sorted = MergeSort.mergeSort(q1);
        Queue<Integer> q2Sorted = MergeSort.mergeSort(q1);

        Assert.assertEquals(q1Size, q1.size());
        Assert.assertEquals(q2Size, q2.size());
        Assert.assertTrue(isSorted(q1Sorted));
        Assert.assertTrue(isSorted(q2Sorted));

    }

    @Test
    public void timingTestMergeSort()
    {
        Stopwatch stopwatch = new Stopwatch();
        Queue<Integer> q1 = new Queue<>();
        int maxSize = 10000;
        while((maxSize--) >= 0)
        {
            q1.enqueue(StdRandom.uniform(10000));
        }
        double start = stopwatch.elapsedTime();
        Queue<Integer> sortedQueue = MergeSort.mergeSort(q1);
        Assert.assertTrue(isSorted(sortedQueue));
        double end  = stopwatch.elapsedTime();
        System.out.println("MergeSort consuming time for 10000 is: " + (end - start));

        Queue<Integer> q2 = new Queue<>();
        maxSize = 100000;
        while((maxSize--) >= 0)
        {
            q2.enqueue(StdRandom.uniform(100000));
        }
        start = stopwatch.elapsedTime();
        Queue<Integer> sortedQueue2 = MergeSort.mergeSort(q2);
        Assert.assertTrue(isSorted(sortedQueue2));
        end  = stopwatch.elapsedTime();
        System.out.println("MergeSort consuming time for 100000 is: " + (end - start));

    }


    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items  A Queue of items
     * @return       true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}
