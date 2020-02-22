package bearmaps;

import edu.princeton.cs.algs4.BST;

import java.util.*;
import java.util.logging.Level;

/**
 * 事实上，这是一个二维的KD-Tree
 */
public class KDTree
{
    private KdNode root;
    private int treeHeight;

    public static class KdNode
    {
        private Point point;
        KdNode leftNode;
        KdNode rightNode;
        int height = 0;

        KdNode(Point e)
        {
            this.point = e;
            leftNode = null;
            rightNode = null;
        }

        KdNode(Point e, int height)
        {
            this(e);
            this.height = height;
        }

        KdNode(Point e, KdNode left, KdNode right)
        {
            this(e);
            leftNode = left;
            rightNode = right;
        }

        Point getPoint()
        {
            return point;
        }

        //不是重载
        public int compareTo(KdNode other, boolean compareX)
        {
            if (other == null)
            {
                return -1;
            }
            if(compareX)
            {
                return Double.compare(point.getX(), other.point.getX());
            }
            else
            {
                return Double.compare(point.getY(), other.point.getY());
            }
        }

        @Override
        public boolean equals(Object o)
        {
            if (o == null || o.getClass() != this.getClass())
            {
                return false;
            }
            else
            {
                return ((KdNode) o).getPoint().equals(getPoint());
            }
        }

        @Override
        public int hashCode()
        {
            return point.hashCode();
        }
    }

    public KDTree(List<Point> points)
    {
        if(points.size() < 1)
        {
            throw new IllegalArgumentException();
        }
        else
        {
            for(Point p : points)
            {
                insert(new KdNode(p));
            }
        }
    }

    public KdNode getRoot()
    {
        return root;
    }

    private void insert(KdNode node)
    {
        root = insertHelper(root, node, true, 0);
    }

    private KdNode insertHelper(KdNode currNode, KdNode insertedNode, boolean compareX, int height)
    {
        if(currNode == null)
        {
            treeHeight = Math.max(treeHeight, height);
            return new KdNode(insertedNode.getPoint(), height);
        }
        else
        {
            if(!currNode.equals(insertedNode))
            {
                if(currNode.compareTo(insertedNode, compareX) > 0)
                    currNode.leftNode = insertHelper(currNode.leftNode, insertedNode,!compareX, currNode.height + 1);
                else
                    currNode.rightNode = insertHelper(currNode.rightNode, insertedNode,!compareX,currNode.height + 1);
            }
        }
        return currNode;
    }

    /*
    public void printTree()
    {
        Queue<KdNode> printQueue = new LinkedList<>();
        printQueue.offer(root);
        KdNode top;
        int currHeight = 0;
        while ((top = printQueue.poll()) != null)
        {
            int count = treeHeight - top.height;
            if(currHeight != top.height)
            {
                currHeight = top.height;
                System.out.println();
            }
            System.out.print(String.format("%" + count + "s", top.point.getX() + ", " + top.point.getY()) + String.format("%-" + count + "s", ' '));
            if(top.leftNode != null)
            {
                printQueue.offer(top.leftNode);
                System.out.print(String.format("%" + (count-1) + "s", "/") + String.format("%-" + count + "s", ' '));
            }
            if(top.rightNode != null)
                printQueue.offer(top.rightNode);
        }
    }
    */

    public Point nearest(double x, double y)
    {
        return null;
    }
}
