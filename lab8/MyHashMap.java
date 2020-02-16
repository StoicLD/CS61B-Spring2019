import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class MyHashMap<K extends Comparable<K>, V> implements Map61B
{

    //LinkedList<Node> Bucket;
    private LinkedList<Node>[] buckets;
    private int mapCapacity = 16;
    private double loadFactor = 0.75;
    private int size;
    private final int resizeFactor = 2;

    class Node
    {
        K key;
        V value;
        Node()
        {

        }
        Node(K key, V value)
        {
            this.key = key;
            this.value = value;
        }

        @Override
        public int hashCode()
        {
            return key.hashCode();
        }
    }

    @SuppressWarnings("unchecked")
    public MyHashMap()
    {

        buckets = new LinkedList[mapCapacity];
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public MyHashMap(int initialSize)
    {
        if (initialSize <= 0)
        {
            throw new IllegalArgumentException();
        }
        mapCapacity = initialSize;
        buckets = new LinkedList[mapCapacity];
        size = 0;
    }

    public MyHashMap(int initialSize, double loadFactor)
    {
        this(initialSize);
        this.loadFactor = loadFactor;
        if (loadFactor <= 0)
        {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void clear()
    {
        buckets = new LinkedList[16];
        size = 0;
    }

    @Override
    public boolean containsKey(Object key)
    {
        for (LinkedList<Node> it : buckets)
        {
            if (it != null)
            {
                for (Node node : it)
                {
                    if (node.key.equals(key))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public Object get(Object key)
    {
        for (LinkedList<Node> it : buckets)
        {
            if (it != null)
            {
                for (Node node : it)
                {
                    if (node.key.equals(key))
                    {
                        return node.value;
                    }
                }
            }
        }
        return null;
    }

    private Node getNode(Object key)
    {
        for (LinkedList<Node> it : buckets)
        {
            if (it != null)
            {
                for (Node node : it)
                {
                    if (node.key.equals(key))
                    {
                        return node;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public void put(Object key, Object value)
    {
        Node node = getNode(key);
        if(node != null)
        {
            node.value = (V) value;
            //System.out.println(Node.class);
        }
        else
        {
            Node putNode = new Node((K) key, (V) value);
            if ((double) (size + 1) / (double) mapCapacity > loadFactor)
            {
                resize();
            }

            int hashCode = putNode.hashCode();
            int num = Math.abs(hashCode) % mapCapacity;
            if (buckets[num] == null)
            {
                buckets[num] = new LinkedList<Node>();
            }
            buckets[num].add(putNode);
            size++;
        }
    }

    public void resize()
    {
        int newCapacity = mapCapacity * resizeFactor;
        LinkedList<Node>[] largerBuckets = new LinkedList[newCapacity];
        for(LinkedList<Node> lls : buckets)
        {
            if(lls != null)
            {
                for(Node node : lls)
                {
                    int num = Math.abs(node.hashCode()) % newCapacity;
                    if(largerBuckets[num] == null)
                    {
                        largerBuckets[num] = new LinkedList<Node>();
                    }
                    largerBuckets[num].add(node);
                }
            }
        }
        buckets = largerBuckets;
    }

    @Override
    public Set keySet()
    {
        Set<K> set = new HashSet<K>();
        for(LinkedList<Node> it : buckets)
        {
            for(Node node : it)
            {
                set.add(node.key);
            }
        }
        return set;
    }

    @Override
    public Object remove(Object key)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object remove(Object key, Object value)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator iterator()
    {
        return keySet().iterator();
    }
}
