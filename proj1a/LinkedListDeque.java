import sun.awt.image.ImageWatched;

import javax.print.attribute.standard.MediaSize;

public class LinkedListDeque<T>
{
    /*
        须知
        （1）add和remove不能递归和循环
        （2）get只能循环
        （3）size常数时间
        （4）占用内存与item多少成线性比例关系
     */
    private Node sentinelNode;
    private int size;

    public class Node
    {
        public T item;
        public Node next;
        public Node prev;
        public Node(Node prev, T item, Node next)
        {
            this.next = next;
            this.prev = prev;
            this.item = item;
        }
    }

    public LinkedListDeque()
    {
        sentinelNode = new Node(null, null, null);
        sentinelNode.next = sentinelNode;
        sentinelNode.prev = sentinelNode;
        size = 0;
    }

    public LinkedListDeque(LinkedListDeque other)
    {
        this();
        if(other.size != 0) {
            Node ptr = other.sentinelNode.next;
            // 注意！！！，千万不能用判断null！！！
            // 因为这是一个循环链表！！！
            while(ptr != other.sentinelNode)
            {
                addLast((T)ptr.item);
                ptr = ptr.next;
            }
        }
    }

    public T get(int index)
    {
        if(index < 0 || index > size - 1 || isEmpty()) {
            return null;
        }
        else {
            Node ptr = sentinelNode.next;
            for(int i = 0; i < index; i++)
            {
                ptr = ptr.next;
            }
            return ptr.item;
        }
    }

    /**
     * 递归的返回指定下标的item数据
     * @param index 下标
     * @return 返回item元素
     */
    public T getRecursive(int index)
    {
        if(index < 0 || index > size - 1) {
            return null;
        }
        else {
            Node currNode = sentinelNode.next;
            return getRecursiveHelper(index, currNode);
        }
    }

    public T getRecursiveHelper(int index, Node currNode)
    {
        if(index == 0) {
            return currNode.item;
        }
        else {
            return getRecursiveHelper(index - 1, currNode.next);
        }
    }

    public void addFirst(T item)
    {
        Node rest = sentinelNode.next;
        Node newFirstNode = new Node(sentinelNode, item, rest);
        sentinelNode.next = newFirstNode;
        if(rest != null) {
            rest.prev = sentinelNode.next;
        }
        else {
            //只有一个节点的时候，新创建的节点的下一个和上一个节点都是senti
            newFirstNode.next = sentinelNode;
            sentinelNode.prev = newFirstNode;
        }
        size++;
    }

    public void addLast(T item)
    {
        Node originLast = sentinelNode.prev;
        Node newLastNode = new Node(originLast, item, sentinelNode);
        sentinelNode.prev = newLastNode;
        if(originLast != null) {
            originLast.next = sentinelNode.prev;
        }
        else {
            //加入节点就是最后一个的情况
            newLastNode.prev = sentinelNode;
            sentinelNode.next = newLastNode;
        }
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
        if(!isEmpty()) {
            Node ptr = sentinelNode.next;
            int count = 0;
            while(ptr != sentinelNode) {
                System.out.println("Node " + count + " is " + ptr.item);
                count++;
                ptr = ptr.next;
            }
        }
        else {
            System.out.println("Deque is empty!");
        }
    }

    public T removeFirst()
    {
        if(size == 0) {
            return null;
        }
        else {
            Node first = sentinelNode.next;
            if(size == 1) {
                sentinelNode.next = sentinelNode;
                sentinelNode.prev = sentinelNode;
            }
            else {
                Node second = first.next;
                sentinelNode.next = second;
                second.prev = sentinelNode;
            }
            size--;
            return first.item;
        }
    }

    public T removeLast()
    {
        if(size == 0) {
            return null;
        }
        else {
            Node last = sentinelNode.prev;
            if(size == 1) {
                sentinelNode.next = sentinelNode;
                sentinelNode.prev = sentinelNode;
            }
            else {
                Node secondLast = last.prev;
                secondLast.next = sentinelNode;
                sentinelNode.prev = secondLast;
            }
            size--;
            return last.item;
        }
    }

}
