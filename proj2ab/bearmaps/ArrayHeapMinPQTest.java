package bearmaps;

import org.junit.Test;

import javax.swing.text.html.HTMLDocument;
import java.util.*;

import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {
    @Test
    public void addAndGetSamllestTest1()
    {
        ArrayHeapMinPQ<String> ahm = new ArrayHeapMinPQ<>();
        ahm.add("c", 3);
        assertEquals(1, ahm.size());
        assertEquals("c", ahm.getSmallest());

        ahm.add("b", 2);
        ahm.add("a", 1);
        assertEquals(3, ahm.size());

        assertEquals("a", ahm.getSmallest());
    }

    @Test
    public void addAndGetSamllestTest2()
    {
        ArrayHeapMinPQ<Integer> ahm1 = new ArrayHeapMinPQ<>();
        assertEquals(0, ahm1.size());
        ahm1.add(5, 5);
        ahm1.add(10, 10);
        ahm1.add(25, 25);
        ahm1.add(30, 30);
        ahm1.add(40, 40);
        ahm1.add(27, 27);
        ahm1.add(29, 29);
        assertEquals((Integer) 5, ahm1.getSmallest());


        ArrayHeapMinPQ<Integer> ahm2 = new ArrayHeapMinPQ<>();
        ahm2.add(10, 10);
        ahm2.add(25, 25);
        ahm2.add(5, 5);
        assertEquals((Integer)5, ahm2.getSmallest());

        ahm2.add(3, 3);
        assertEquals((Integer)3, ahm2.getSmallest());

        ahm2.add(15, 15);
        ahm2.add(1, 1);
        assertEquals((Integer)1, ahm2.getSmallest());

    }

    @Test
    public void containsTest()
    {
        ArrayHeapMinPQ<Integer> ahm = new ArrayHeapMinPQ<>();
        ahm.add(11, 11);
        ahm.add(2, 2);
        ahm.add(4, 4);
        assertTrue(ahm.contains(2));
        assertFalse(ahm.contains(1));
    }

    @Test
    public void removeTest1()
    {
        ArrayHeapMinPQ<Integer> ahm1 = new ArrayHeapMinPQ<>();
        assertEquals(0, ahm1.size());
        ahm1.add(4, 4);
        ahm1.add(11, 11);
        ahm1.add(25, 25);
        ahm1.add(31, 31);
        ahm1.add(40, 40);
        ahm1.add(27, 27);
        ahm1.add(29, 29);
        assertEquals((Integer)4, ahm1.getSmallest());

        ahm1.removeSmallest();
        assertEquals((Integer)11, ahm1.getSmallest());

        ahm1.removeSmallest();
        assertEquals((Integer)25, ahm1.getSmallest());
    }

    @Test
    public void removeTest2()
    {
        ArrayHeapMinPQ<Integer> ahm2 = new ArrayHeapMinPQ<>();
        ahm2.add(11, 11);
        ahm2.add(25, 25);
        ahm2.add(6, 6);
        assertEquals((Integer)6, ahm2.getSmallest());

        ahm2.add(3, 3);
        assertEquals((Integer)3, ahm2.getSmallest());

        ahm2.add(15, 15);
        ahm2.add(2, 2);
        assertEquals((Integer)2, ahm2.getSmallest());

        assertEquals((Integer)2, ahm2.removeSmallest());
        assertEquals((Integer)3, ahm2.removeSmallest());
        assertEquals((Integer)6, ahm2.removeSmallest());
        assertEquals((Integer)11, ahm2.removeSmallest());
        assertEquals((Integer)15, ahm2.removeSmallest());
    }

    @Test
    public void removeTest3()
    {
        ArrayHeapMinPQ<Integer> ahm = new ArrayHeapMinPQ<>();
        ahm.add(1, 1);
        assertEquals((Integer)1, ahm.removeSmallest());
    }

    @Test
    public void removeTest4()
    {
        ArrayHeapMinPQ<Integer> ahm = new ArrayHeapMinPQ<>();
        ahm.add(2, 2);
        ahm.add(3, 3);
        ahm.add(7, 7);
        ahm.add(10, 10);
        ahm.add(21, 21);
        ahm.add(9, 9);
        ahm.add(18, 18);
        ahm.add(25, 25);
        PrintHeapDemo.printFancyHeapDrawing(ahm.heapArray());

        assertEquals((Integer) 2, ahm.removeSmallest());
        assertEquals((Integer) 3, ahm.removeSmallest());
        assertEquals((Integer) 7, ahm.removeSmallest());
        assertEquals((Integer) 9, ahm.removeSmallest());
        assertEquals((Integer) 10, ahm.removeSmallest());
        assertEquals((Integer) 18, ahm.removeSmallest());
        assertEquals((Integer) 21, ahm.removeSmallest());
        assertEquals((Integer) 25, ahm.removeSmallest());

    }

    @Test
    public void mapDataTest1()
    {
        ArrayHeapMinPQ<Integer> ahm = new ArrayHeapMinPQ<>();
        ahm.add(5,5);
        ahm.add(1,1);
        ahm.add(11,11);
        ahm.add(3,3);
        ahm.checkMapIndex();

        ahm.removeSmallest();
        ahm.checkMapIndex();

        ahm.removeSmallest();
        ahm.checkMapIndex();
    }

    @Test
    public void changePriorityTest1()
    {
        ArrayHeapMinPQ<Integer> ahm = new ArrayHeapMinPQ<>();
        ahm.add(2,2);
        ahm.add(3,3);
        ahm.add(7,7);
        ahm.add(11,11);
        ahm.add(21,21);
        ahm.add(9,18);
        ahm.add(25,25);
        ahm.changePriority(21, 1);
        assertEquals((Integer)21, ahm.getSmallest());
        PrintHeapDemo.printFancyHeapDrawing(ahm.heapArray());
    }

    @Test
    public void changePriorityTest2()
    {
        ArrayHeapMinPQ<Integer> ahm = new ArrayHeapMinPQ<>();
        ahm.add(2,2);
        ahm.add(4,4);
        ahm.add(7,7);
        ahm.add(11,11);
        ahm.add(22,22);
        ahm.add(9,9);
        ahm.add(35,35);
        ahm.add(45,45);

        PrintHeapDemo.printFancyHeapDrawing(ahm.heapArray());


        ahm.changePriority(2, 3);
        assertEquals((Integer)2, ahm.getSmallest());
        PrintHeapDemo.printFancyHeapDrawing(ahm.heapArray());

        ahm.changePriority(4, 30);
        PrintHeapDemo.printFancyHeapDrawing(ahm.heapArray());

        ahm.changePriority(7, 10);
        PrintHeapDemo.printFancyHeapDrawing(ahm.heapArray());

        ahm.changePriority(2, 36);
        PrintHeapDemo.printFancyHeapDrawing(ahm.heapArray());
    }

    @Test
    public void addTimingTest1()
    {
        addTimingfHelper(1000);
        addTimingfHelper(10000);
    }

    @Test
    public void addTimingTest2()
    {
        long start = System.currentTimeMillis();
        ArrayHeapMinPQ<Integer> minHeap = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 200000; i += 1) {
            minHeap.add(i, 100000 - i);
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end - start) / 1000.0 +  " seconds.");

        long start2 = System.currentTimeMillis();
        for (int j = 0; j < 200000; j += 1) {
            minHeap.changePriority(j, j + 1);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end2 - start2) / 1000.0 +  " seconds.");
    }

    @Test
    public void removeTimingTest1()
    {
        removeTimingHelper(1000);
        removeTimingHelper(10000);
        removeTimingHelper(100000);
    }

    @Test
    public void changePriorityTimingTest1()
    {
        changeTimingHelper(1000);
        changeTimingHelper(10000);
        changeTimingHelper(100000);
    }

//===================================================================================================

    public Set<Integer> randomSet(int totalNum)
    {
        if(totalNum < 1)
        {
            throw new IllegalArgumentException();
        }

        Random rng = new Random(); // Ideally just create one instance globally
        Set<Integer> generated = new LinkedHashSet<>();
        while (generated.size() < totalNum)
        {
            Integer next = rng.nextInt(totalNum) + 1;
            // As we're adding to a set, this will automatically do a containment check
            generated.add(next);
        }
        return generated;
    }

    public ArrayList<Integer> randomList(int totalNum)
    {
        ArrayList<Integer> generated = new ArrayList<>();
        if(totalNum < 1)
        {
            throw new IllegalArgumentException();
        }

        Random rng = new Random(); // Ideally just create one instance globally
        while (generated.size() < totalNum)
        {
            Integer next = rng.nextInt(totalNum) + 1;
            // As we're adding to a set, this will automatically do a containment check
            if(!generated.contains(next))
            {
                generated.add(next);
            }
        }
        return generated;
    }

    public void addTimingfHelper(int totalNum)
    {

        Set<Integer> generated = randomSet(totalNum);

        long start = System.currentTimeMillis();
        ArrayHeapMinPQ<Integer> ahm = new ArrayHeapMinPQ<>();
        for (Integer it : generated) {
            ahm.add(it, it);
        }
        long end = System.currentTimeMillis();
        System.out.println("ArrayPQ Total time elapsed: " + (end - start)/1000.0 +  " seconds.");

        start = System.currentTimeMillis();
        NaiveMinPQ<Integer> nmp = new NaiveMinPQ<>();
        for (Integer it : generated) {
            nmp.add(it, it);
        }
        end = System.currentTimeMillis();
        System.out.println("Naive Total time elapsed: " + (end - start)/1000.0 +  " seconds.");


    }

    public void removeTimingHelper(int totalNum)
    {
        Set<Integer> generated = randomSet(totalNum);
        ArrayHeapMinPQ<Integer> ahm = new ArrayHeapMinPQ<>();
        for (Integer it : generated) {
            ahm.add(it, it);
        }

        long start = System.currentTimeMillis();
        for(int i = 0; i < ahm.size(); i++)
        {
            ahm.removeSmallest();
        }
        long end = System.currentTimeMillis();
        System.out.println("ArrayPQ Total time elapsed: " + (end - start)/1000.0 +  " seconds.");


        NaiveMinPQ<Integer> nmp = new NaiveMinPQ<>();
        for (Integer it : generated) {
            nmp.add(it, it);
        }

        start = System.currentTimeMillis();
        for(int i = 0; i < nmp.size(); i++)
        {
            nmp.removeSmallest();
        }
        end = System.currentTimeMillis();
        System.out.println("Naive Total time elapsed: " + (end - start)/1000.0 +  " seconds.");
    }

    public void changeTimingHelper(int totalNum)
    {
        ArrayList<Integer> generated = randomList(totalNum);
        ArrayList<Integer> originList = new ArrayList<>(generated);

        ArrayHeapMinPQ<Integer> ahm = new ArrayHeapMinPQ<>();
        for (Integer it : generated) {
            ahm.add(it, it);
        }
        NaiveMinPQ<Integer> npm = new NaiveMinPQ<>();
        for (Integer it : generated) {
            npm.add(it, it);
        }

        Collections.shuffle(generated);
        long start = System.currentTimeMillis();
        Iterator originIt = originList.iterator();
        Iterator genIt = generated.iterator();
        for ( ; originIt.hasNext() && genIt.hasNext(); ) {
            ahm.changePriority((Integer) originIt.next(), (Integer) genIt.next());
        }
        long end = System.currentTimeMillis();
        System.out.println("ArrayPQ Total time elapsed: " + (end - start)/1000.0 +  " seconds.");

        start = System.currentTimeMillis();
        originIt = originList.iterator();
        genIt = generated.iterator();
        for ( ; originIt.hasNext() && genIt.hasNext(); ) {
            npm.changePriority((Integer) originIt.next(), (Integer) genIt.next());
        }
        end = System.currentTimeMillis();
        System.out.println("NaivePQ Total time elapsed: " + (end - start)/1000.0 +  " seconds.");


    }


    public static void main(String args[])
    {
        jh61b.junit.TestRunner.runTests(ArrayHeapMinPQTest.class);
    }
}
