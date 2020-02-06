//import edu.princeton.cs.algs4.BST;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>
{
    Node root;
    int size;

    class Node
    {
        K key;
        V value;
        Node left;
        Node right;
        public Node(K key, V value)
        {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    public BSTMap()
    {
        clear();
    }

    @Override
    public void clear()
    {
        size = 0;
        root = null;
    }

    @Override
    public boolean containsKey(K key)
    {
        return containsKeyHelper(root, key);
    }

    public boolean containsKeyHelper(Node node, K key)
    {
        if(node == null) {
            return false;
        }
        else if (node.key.compareTo(key) > 0) {
            //默认这种情况下，当前的key比比较的key大，搜索left
            return containsKeyHelper(node.left, key);
        }
        else if (node.key.compareTo(key) < 0 ) {
            return containsKeyHelper(node.right, key);
        }
        else {
            return true;
        }
    }

    @Override
    public V get(K key)
    {
        return getHelper(root, key);
    }

    public V getHelper(Node node, K key)
    {
        if(node == null) {
            return null;
        }
        else if (node.key.compareTo(key) > 0) {
            return getHelper(node.left, key);
        }
        else if (node.key.compareTo(key) < 0){
            return getHelper(node.right, key);
        }
        else {
            return node.value;
        }
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public void put(K key, V value)
    {
        root = putHelper(root, key, value);
        size++;
    }

    public Node putHelper(Node node, K key, V value)
    {
        if (node == null) {
            return new Node(key, value);
        }
        else if (node.key.compareTo(key) > 0) {
             node.left = putHelper(node.left, key, value);
        }
        else{
            //应该不测试重复key的情况，所以默认大于等于都放在右子树
            node.right = putHelper(node.right, key, value);
        }
        return node;
    }

    @Override
    public Set<K> keySet()
    {
        throw new UnsupportedOperationException();
        //return null;
    }

    @Override
    public V remove(K key)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value)
    {
        throw new UnsupportedOperationException();
        //return null;
    }

    @Override
    public Iterator<K> iterator()
    {
        throw new UnsupportedOperationException();
        //return null;
    }

    public void printInOrder()
    {
        printInOrderHelper(root);
    }

    public void printInOrderHelper(Node node)
    {
        if(node == null) {
            return;
        }
        printInOrderHelper(node.left);
        System.out.println("(key: " + node.key + ", value: " + node.value + ")");
        printInOrderHelper(node.right);
    }
}
