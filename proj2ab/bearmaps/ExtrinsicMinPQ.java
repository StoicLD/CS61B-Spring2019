package bearmaps;
/**
 * Priority queue where objects have a priority that is provided
 * extrinsically, i.e. are are supplied as an argument during insertion
 * and can be changed using the changePriority method.
 */
public interface ExtrinsicMinPQ<T> {
    //也就是加入一个新元素时候是手动给一个优先级的value的，而不是依靠这个element的特性来
    //比较的

    /* Adds an item with the given priority value. Throws an
     * IllegalArgumentExceptionb if item is already present.
     * You may assume that item is never null. */
    void add(T item, double priority);
    /* Returns true if the PQ contains the given item. */
    boolean contains(T item);
    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    T getSmallest();
    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    T removeSmallest();
    /* Returns the number of items in the PQ. */
    int size();
    /* Changes the priority of the given item. Throws NoSuchElementException if the item 
     * doesn't exist. */
    void changePriority(T item, double priority);
}
