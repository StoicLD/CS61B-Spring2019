import java.util.*;

public class MyTrieSet implements TrieSet61B
{
    private Node root;

    private static class Node
    {
        boolean isKey = false;
        Character character;
        HashMap<Character, Node> links;

        public Node(Character c, boolean isKey)
        {
            links = new HashMap<>();
            this.character = c;
            this.isKey = isKey;
        }

        public void add(Character c, boolean isKey)
        {
            links.put(c, new Node(c, isKey));
        }

        public Node getKey(Character c)
        {
            return links.get(c);
        }
    }

    public MyTrieSet()
    {
        clear();
    }

    //基于HashTable的TrieSet
    @Override
    public void clear()
    {
        root = new Node(null, false);
    }

    @Override
    public boolean contains(String key)
    {
        if (key == null || key.length() < 1)
        {
            return false;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++)
        {
            char c = key.charAt(i);
            curr = curr.links.get(c);
            if (curr == null)
            {
                return false;
            }
        }
        return curr.isKey;
    }

    @Override
    public void add(String key)
    {
        if (key == null || key.length() < 1)
        {
            return;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++)
        {
            char c = key.charAt(i);
            if (!curr.links.containsKey(c))
            {
                curr.links.put(c, new Node(c, false));
            }
            curr = curr.links.get(c);
        }
        curr.isKey = true;
    }

    @Override
    public List<String> keysWithPrefix(String prefix)
    {
        if (prefix == null || prefix.length() < 1)
        {
            return null;
        }
        Node curr = root;
        for (int i = 0, n = prefix.length(); i < n; i++)
        {
            char c = prefix.charAt(i);
            curr = curr.links.get(c);
            if (curr == null)
            {
                return null;
            }
        }
        List<String> result = new LinkedList<>();
        prefixHelper(curr, prefix, result);
        return result;
    }

    public void prefixHelper(Node node, String prefix, List<String> result)
    {
        if (node.isKey)
        {
            result.add(prefix);
        }
        for (Character it : node.links.keySet())
        {
            Node nextNode = node.links.get(it);
            if (nextNode != null)
            {
                prefixHelper(nextNode, prefix + nextNode.character, result);
            }
        }
    }

    @Override
    public String longestPrefixOf(String key)
    {
        throw new UnsupportedOperationException();
    }
}
