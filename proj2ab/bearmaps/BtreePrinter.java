package bearmaps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class BTreePrinter
{

    public static void printNode(KDTree.KdNode root, int unitLength)
    {
        int maxLevel = BTreePrinter.maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel, unitLength);
    }

    private static void printNodeInternal(List<KDTree.KdNode> nodes, int level, int maxLevel, int count)
    {
        if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
            return;

        //int count = (Double.toString(nodes.get(0).getPoint().getX()) + Double.toString(nodes.get(0).getPoint().getY())).length() + 2;
        int floor = maxLevel - level;
        //int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int endgeLines = (int) Math.max(Math.pow(2, floor - 1), 0);
        endgeLines *= count;
        //int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        firstSpaces *= count;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;
        betweenSpaces *= count;

        BTreePrinter.printWhitespaces(firstSpaces);

        List<KDTree.KdNode> newNodes = new ArrayList<>();
        for (KDTree.KdNode node : nodes)
        {
            if (node != null)
            {
                System.out.print(node.getPoint().getX() + ", " + node.getPoint().getY());
                newNodes.add(node.leftNode);
                newNodes.add(node.rightNode);
            }
            else
            {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(String.format("%" + count + "s", " "));
            }

            BTreePrinter.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        //打印斜杠
        for (int i = 1; i <= endgeLines; i++)
        {
            for (int j = 0; j < nodes.size(); j++)
            {
                BTreePrinter.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null)
                {
                    //BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                    BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + count + count);
                    continue;
                }

                if (nodes.get(j).leftNode != null)
                    System.out.print("/");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(count + i + i - 2);

                if (nodes.get(j).rightNode != null)
                    System.out.print("\\");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel, count);
    }

    private static void printWhitespaces(int count)
    {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static int maxLevel(KDTree.KdNode node)
    {
        if (node == null)
            return 0;

        return Math.max(BTreePrinter.maxLevel(node.leftNode), BTreePrinter.maxLevel(node.rightNode)) + 1;
    }

    private static boolean isAllElementsNull(List list)
    {
        for (Object object : list)
        {
            if (object != null)
                return false;
        }

        return true;
    }

}
