package bearmaps;

import java.util.*;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T>
{
    //多开一个大小，留空一个位置（index为0的位置）
    //所以最小元素在index = 1的位置上
    private ArrayList<Node> innerMinPQ;
    private HashMap<T, Integer> innerTable;  //用来快速查找是否存在该元素


    private class Node implements Comparable<Node>
    {
        private T item;
        private double priority;

        Node(T e, double p) {
            this.item = e;
            this.priority = p;
        }

        T getItem() {
            return item;
        }

        double getPriority() {
            return priority;
        }

        void setPriority(double priority) {
            this.priority = priority;
        }

        @Override
        public int compareTo(Node other) {
            if (other == null) {
                return -1;
            }
            return Double.compare(this.getPriority(), other.getPriority());
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (o == null || o.getClass() != this.getClass()) {
                return false;
            }
            else {
                return ((Node) o).getItem().equals(getItem());
            }
        }

        @Override
        public int hashCode() {
            return item.hashCode();
        }
    }


    public ArrayHeapMinPQ()
    {
        innerMinPQ = new ArrayList<>();
        innerMinPQ.add(new Node(null, 0));
        innerTable = new HashMap<>();
    }

    private int getParentIndex(int index)
    {
        return index / 2;
    }

    private int getLeftChildIndex(int index)
    {
        return index * 2;
    }

    private int getRightChildIndex(int index)
    {
        return index * 2 + 1;
    }

    private int getLastIndex()
    {
        return size();
    }

    private void swapNode(int index1, int index2)
    {
        if(index1 <= 0 || index1 > getLastIndex() || index2 <= 0 || index2 > getLastIndex()) {
            throw new IndexOutOfBoundsException();
        }
        else {
            Node temp = innerMinPQ.get(index1);
            innerTable.put(innerMinPQ.get(index1).item, index2);
            innerTable.put(innerMinPQ.get(index2).item, index1);

            innerMinPQ.set(index1, innerMinPQ.get(index2));
            innerMinPQ.set(index2, temp);
        }
    }

    public Object[] heapArray()
    {
        T[] heap = (T[])(new Object[size()]);
        for(int i = 1; i < heap.length; i++)
        {
            heap[i] = innerMinPQ.get(i).item;
        }
        return heap;
    }

    public boolean checkMapIndex()
    {
        for(int i = 1; i < innerMinPQ.size(); i++)
        {
            T item = innerMinPQ.get(i).item;
            if(!innerTable.containsKey(item) && innerTable.get(item) != i)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * 注意重复元素需要抛出异常
     *
     * @param item     元素
     * @param priority 优先级
     */
    @Override
    public void add(T item, double priority)
    {
        if (contains(item)) {
            throw new IllegalArgumentException();
        }
        else {
            //最小堆的插入
            Node newNode = new Node(item, priority);
            innerMinPQ.add(newNode);
            //只能假设这是个O(1)操作，可以的，只要平均是O(logN)就可以了
            innerTable.put(item, getLastIndex());
            int currIndex = innerMinPQ.size() - 1;
            int parentIndex = getParentIndex(innerMinPQ.size() - 1);
            while (parentIndex > 0)
            {
                Node parentNode = innerMinPQ.get(parentIndex);
                Node currNode = innerMinPQ.get(currIndex);
                if (parentNode.compareTo(currNode) > 0) {
                    //innerMinPQ.set(currIndex, parentNode);
                    //innerMinPQ.set(parentIndex, currNode);
                    swapNode(currIndex, parentIndex);
                    currIndex = parentIndex;
                    parentIndex = getParentIndex(parentIndex);

                }
                else {
                    break;
                }
            }
        }
    }

    /**
     * 这个也要做到O(logN)
     *
     * @param item 元素
     * @return 是否包含该元素
     */
    @Override
    public boolean contains(T item)
    {
        return innerTable.containsKey(item);
    }

    @Override
    public T getSmallest()
    {
        if (size() <= 0) {
            throw new NoSuchElementException();
        }

        return innerMinPQ.get(1).item;
    }

    @Override
    public T removeSmallest()
    {
        if (getLastIndex() < 1) {
            throw new NoSuchElementException();
        }
        else {
            swapNode(1, getLastIndex());
            int currIndex = 1;
            int nextIndex = 1;
            while(currIndex < getLastIndex() - 1)
            {
                int leftIndex = getLeftChildIndex(currIndex);
                int rightIndex = getRightChildIndex(currIndex);
                if (leftIndex < getLastIndex() && innerMinPQ.get(currIndex).compareTo(innerMinPQ.get(leftIndex)) > 0)
                {
                    nextIndex = leftIndex;
                }
                if (rightIndex < getLastIndex() && innerMinPQ.get(nextIndex).compareTo(innerMinPQ.get(rightIndex)) > 0)
                {
                    nextIndex = rightIndex;
                }
                if(currIndex == nextIndex || nextIndex >= getLastIndex()) {
                    break;
                }
                swapNode(currIndex, nextIndex);
                currIndex = nextIndex;
            }
            Node removed = innerMinPQ.remove(getLastIndex());
            innerTable.remove(removed.item);
            return removed.item;
        }
    }

    /**
     * innerMinPQ的多一个虚拟的头节点，所以返回大小要减去1
     * @return 大小
     */
    @Override
    public int size()
    {
        return innerMinPQ.size() - 1;
    }

    @Override
    public void changePriority(T item, double priority)
    {

    }
}
