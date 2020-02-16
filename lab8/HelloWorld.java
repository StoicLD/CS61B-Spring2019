import java.util.LinkedList;

public class HelloWorld{

    static class Node
    {
        String key;
        int value;
        public Node()
        {

        }
        public Node(String k, int v)
        {
            key = k;
            value = v;
        }
    }
    Node[] nodes;
    int size = 0;

    HelloWorld()
    {
        nodes = new Node[10];
    }

    public void put(Node node)
    {
        nodes[size] = node;
    }

    public Object get(String key)
    {
        for(Node item : nodes)
        {
            if(item.key.equals(key))
            {
                return item;
            }
        }
        return null;
    }

    public Node getNode(String key)
    {

        Node node = (Node)get(key);
        System.out.println(node.getClass() == Node.class);
        return node;
    }

    public static void check(Object k, Object v)
    {
        System.out.println(k.getClass());
        System.out.println(v.getClass());

    }

    public static void main(String []args){
        LinkedList<Node> l1 = new LinkedList<>();
        LinkedList<Node> l2 = new LinkedList<>();
        Node n1 = new Node("11",2);
        l1.add(n1);
        l2.add(n1);
        l1.clear();
        System.out.println(l2.size());
        System.out.println(l1.size());

        System.out.println(l2.getFirst().key);

        System.out.println("Hello World");
    }
}