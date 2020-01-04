import java.util.Objects;

public class ArrayDeque<T>
{
    private T[] items;
    private int size;
    private int capacity;
    private int criticalValue;         //当前阈值，等于四分之一的capacity

    private final int FACTOR = 2;

    private int headIndex;      //第一个元素所在位置
    private int tailIndex;      //最后一个元素所在位置

    //注意，泛型数组的创建，需要Object，强制转换成T是没必要的
    public ArrayDeque()
    {
        items = (T[])new Object[8];
        size = 0;
        headIndex = 0;
        tailIndex = 0;
        capacity = 8;
    }

    public ArrayDeque(ArrayDeque other)
    {

    }

    public T get(int index)
    {
        if(index < 0 || index >= size) {
            return null;
        }
        else {
            int realIndex = (headIndex + index) % size;
            if(realIndex < 0 || realIndex >= size) {
                System.out.println("realIndex error!! " + " realIndex is " + realIndex + " while size is " + size);
                return null;
            }
            return items[realIndex];
        }
    }

    /**
     * 扩容multiple倍
     * @param multiple
     */
    public void scale(int multiple)
    {
        if(multiple < 1)
            return;
        T[] scaledItems = (T[])new Object[capacity * multiple];
        //拷贝的时候应该分为两种情况
        //（1）head在左侧，tail在右侧，这就是正常顺序
        //（2）tail在左侧，head在右侧，这是一端超过末尾了
        if(headIndex < tailIndex) {
            System.arraycopy(items, headIndex, scaledItems, 0, tailIndex - headIndex + 1);
        }
        else {
            //复制从第headIndex元素到线型数组最后一个下标所在的元素
            //复制从第0个下标对应元素到tailIndex（应该是headIndex - 1）的所有元素
            System.arraycopy(items, headIndex, scaledItems, 0, capacity - headIndex);
            System.arraycopy(items, 0, scaledItems, capacity - headIndex, tailIndex + 1);
        }
        items = scaledItems;
        capacity *= multiple;
        criticalValue = capacity / 4;       //只有在每次扩容和缩容时计算一次临界值，这样每次只要和临界值比大小就可以了
        //重置头尾节点
        headIndex = 0;
        tailIndex = size - 1;
    }

    /**
     * 缩容的时候有需要注意，因为有效元素可能在中间
     * 我的处理是
     * @param shrink
     */
    public void descale(int shrink)
    {
        if(shrink < 1)
            return;
        //缩容通常是2倍缩小
        T[] scaledItems = (T[])new Object[capacity / shrink];
        if(headIndex < tailIndex) {
            // 无论缩容还是扩容，都不会出现headIndex和tailIndex重合的情况（这两者只有size = 0时候才会重合）
            System.arraycopy(items, headIndex, scaledItems, 0, tailIndex - headIndex + 1);
        }
        else {
            System.arraycopy(items, headIndex, scaledItems, 0, capacity - headIndex);
            System.arraycopy(items, 0, scaledItems, capacity - headIndex, tailIndex + 1);
        }
        items = scaledItems;
        capacity /= shrink;
        criticalValue = capacity / 4;       //只有在每次扩容和缩容时计算一次临界值，这样每次只要和临界值比大小就可以了

        //重置头尾节点
        headIndex = 0;
        tailIndex = size - 1;
    }

    /**
     * 从圆形图上看，头部朝一个方向，尾部朝相反的方向
     * 但是一个顺时针，一个逆时针的方向是不变的
     * 如果在线性数组上，则是往头部添加，相当于一直往左（一直递减取余）
     * 往尾部添加，就是一直往右（一直递增取余）
     * 添加到第一个，注意扩容
     * @param item
     */
    public void addFirst(T item)
    {
        if(size + 1 > capacity)
        {
            //扩容一倍
            scale(FACTOR);
        }
        if (size != 0) {
            headIndex = (headIndex + capacity - 1) % capacity;
        }
        items[headIndex] = item;
        size++;
    }

    /**
     * 往尾部添加
     * @param item
     */
    public void addLast(T item)
    {
        if(size + 1 > capacity)
        {
            //扩容一倍
            scale(FACTOR);
        }
        if(size != 0) {
            tailIndex = (tailIndex + capacity + 1) % capacity;
        }
        //当tailIndex和headIndex重合时，就是0个元素
        items[tailIndex] = item;
        size++;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public int size()
    {
        return size;
    }

    public void printDeque()
    {
        int count = 0;
        for(int i = headIndex; count < size; count++, i = (i + 1 + capacity) % capacity)
        {
            System.out.println(items[i]);
        }
        System.out.println();
    }

    public T removeFirst()
    {
        if(size == 0) {
            return null;
        }
        else {
            //注意缩容
            if(capacity >= 16 && size - 1 < criticalValue) {
                //此时需要缩容，小于四分之一时
                descale(FACTOR);
            }
            T result = items[headIndex];
            headIndex = (headIndex + capacity + 1) % capacity;
            size--;
            return result;
        }
    }

    public T removeLast()
    {
        if(size == 0) {
            return null;
        }
        else {
            //注意缩容
            if(capacity >= 16 && size - 1 < criticalValue) {
                //此时需要缩容，小于四分之一时
                descale(FACTOR);
            }
            T result = items[tailIndex];
            tailIndex = (tailIndex + capacity - 1) % capacity;
            size--;
            return result;
        }
    }
    
}
