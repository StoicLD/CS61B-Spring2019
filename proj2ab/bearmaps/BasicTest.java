package bearmaps;

import java.util.ArrayList;

public class BasicTest
{
    public ArrayList<Integer> basicList;
    public BasicTest(ArrayList<Integer> sList)
    {
        basicList = sList;
    }

    public void printList()
    {
        for(int i : basicList)
        {
            System.out.println(i);
        }
    }

    public static void main(String args[])
    {
        int count = 13;
        System.out.println(String.format("%" + count + "s", "as") + String.format("%-20c", ' '));
    }
}
