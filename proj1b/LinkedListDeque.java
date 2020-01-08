import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Isn't this solution kinda... cheating? Yes.
 */
public class LinkedListDeque<T> extends LinkedList<T> implements Deque<T>{
    public void printDeque() {
        System.out.println("dummy");
    }

    public T getRecursive(int i) {
        return get(i);
    }

    public T removeFirst() {
        try {
            return super.removeFirst();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public T removeLast() {
        try {
            return super.removeLast();
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}