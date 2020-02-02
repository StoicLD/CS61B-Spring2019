package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        assertTrue(arb.isEmpty());
        assertEquals(10, arb.capacity());
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        assertEquals(3, arb.fillCount());
        assertEquals(1, arb.peek());
        arb.enqueue(4);
        arb.enqueue(5);
        arb.dequeue();
        arb.dequeue();
        assertEquals(3, arb.fillCount());
        assertEquals(3, arb.peek());
        arb.enqueue(6);
        arb.enqueue(7);
        arb.enqueue(8);
        arb.enqueue(9);
        arb.enqueue(10);
        arb.enqueue(11);
        arb.enqueue(12);

        assertTrue(arb.isFull());
        arb.dequeue();
        arb.dequeue();
        assertEquals(5, arb.peek());
        assertEquals(8, arb.fillCount());

        System.out.println("Yes, you have passed all the tests!!!");
    }

    @Test
    public void testEquals()
    {
        ArrayRingBuffer<Integer> a1 = new ArrayRingBuffer<>(10);
        ArrayRingBuffer<Integer> a2 = new ArrayRingBuffer<>(10);
        ArrayRingBuffer<String> a3 = new ArrayRingBuffer<>(10);

        assertEquals(a1,a1);
        assertEquals(a1,a2);

        a1.enqueue(1);
        a2.enqueue(1);
        a3.enqueue("nihao");
        assertNotEquals(a1,a3);

        a1.enqueue(2);
        a2.enqueue(2);
        a1.enqueue(3);
        a2.enqueue(3);
        assertEquals(a1,a2);

        a2.dequeue();
        assertNotEquals(a1,a2);
    }
}
