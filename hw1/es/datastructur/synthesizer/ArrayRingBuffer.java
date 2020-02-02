package es.datastructur.synthesizer;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T>{
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        rb = (T[])new Object[capacity];
        first = 0;
        last = 0;
    }

    @Override
    public int capacity()
    {
        return rb.length;
    }

    @Override
    public int fillCount()
    {
//        if(first < last) {
//            return last - first + 1;
//        }
//        else {
//            return last + capacity() - first + 1;
//        }
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last.
        if(isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        else {
            fillCount++;
            if(fillCount == 1) {
                rb[last] = x;
                return;
            }
            last++;
            if (last >= capacity()) {
                last = 0;
            }
            rb[last] = x;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        if(isEmpty()){
            throw new RuntimeException("Ring buffer underflow");
        }
        else {
            fillCount--;
            int originIndex = first;
            first++;
            if(first >= capacity()) {
                first = 0;
            }
            return rb[originIndex];
        }
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change.
        if(!isEmpty()) {
            return rb[first];
        }
        else {
            throw new RuntimeException("Ring buffer underflow");
        }
    }

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
    private class RingBufferIterator implements Iterator<T>
    {
        int curr;
        int count;
        public RingBufferIterator()
        {
            count = 0;
            curr = first;
        }

        @Override
        public boolean hasNext()
        {
            return count != fillCount;
        }

        @Override
        public T next()
        {
            T item = rb[curr];
            count++;
            curr++;
            if(curr >= capacity())
            {
                curr = 0;
            }
            return item;
        }
    }

    @Override
    public Iterator<T> iterator()
    {
        return new RingBufferIterator();
    }

    @Override
    public boolean equals(Object o)
    {
        if(o == this)
        {
            return true;
        }
        if(o == null)
        {
            return false;
        }
        if(this.getClass() != o.getClass())
        {
            return false;
        }
        ArrayRingBuffer<T> otherArrayRingBuffer = (ArrayRingBuffer<T>)o;
        if(this.fillCount != otherArrayRingBuffer.fillCount)
        {
            return false;
        }
        Iterator<T> it1 = this.iterator();
        Iterator<T> it2 = otherArrayRingBuffer.iterator();

        while(it1.hasNext() && it2.hasNext())
        {
            if(it1.next() != it2.next())
            {
                return false;
            }
        }

        return true;
    }

}
    // TODO: Remove all comments that say TODO when you're done.
