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

    private static int callingTimes = 0;

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

    /**
     * 插入的helper func，小的节点在右侧，大的在左侧
     * @param currNode 当前节点
     * @param insertedNode  插入的节点
     * @param compareX  当前比较的是x or y，true是x，false是y
     * @param height    当前的节点深度
     * @return
     */
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

    public static int getTimes()
    {
        return callingTimes;
    }

    /**
     * 先尝试实现一个lecture note上面的版本
     * @param x x坐标
     * @param y y坐标
     * @return Kd树中最近的点
     */
    public Point nearest(double x, double y)
    {
        callingTimes = 0;
        //(TreeHeight - height) % 2来判定x还是y
        Point targetPoint = new Point(x, y);
        return nearestHelper(root, targetPoint, root.getPoint());
    }

    private Point nearestHelper(KdNode currNode, Point targetPoint, Point currBestPoint)
    {
        callingTimes++;
        if(currNode == null)
        {
            return currBestPoint;
        }
        if(currNode.getPoint().equals(targetPoint))
        {
            return currNode.getPoint();
        }
        else
        {
            double dis = Point.distance(currNode.getPoint(), targetPoint);
            if(dis < Point.distance(currBestPoint, targetPoint))
            {
                currBestPoint = currNode.getPoint();
            }

            KdNode goodNode;
            KdNode badNode;
            double directDis = 0;
            //比较X
            if((currNode.height % 2) == 0)
            {
                directDis = Math.abs(currNode.getPoint().getX() - targetPoint.getX());
                if(targetPoint.getX() < currNode.getPoint().getX())
                {
                    goodNode = currNode.leftNode;
                    badNode = currNode.rightNode;
                }
                else
                {
                    goodNode = currNode.rightNode;
                    badNode = currNode.leftNode;
                }
            }
            else
            {
                //比较Y
                directDis = Math.abs(currNode.getPoint().getY() - targetPoint.getY());
                if(targetPoint.getY() < currNode.getPoint().getY())
                {
                    goodNode = currNode.leftNode;
                    badNode = currNode.rightNode;
                }
                else
                {
                    goodNode = currNode.rightNode;
                    badNode = currNode.leftNode;
                }
            }
            currBestPoint = nearestHelper(goodNode, targetPoint, currBestPoint);

            //下面是决定是否要访问badNode那一个分支
            if(Point.distance(currBestPoint, targetPoint) > directDis * directDis)
            {
                currBestPoint = nearestHelper(badNode, targetPoint, currBestPoint);
            }
            return currBestPoint;
        }
    }

}
