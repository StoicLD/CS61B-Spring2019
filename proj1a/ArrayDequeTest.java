/**
 * 数组链表的测试类
 */
public class ArrayDequeTest
{
    /* Utility method for printing out empty checks. */
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out empty checks. */
    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Prints a nice message based on whether a test passed.
     * The \n means newline. */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }

    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public static void addIsEmptySizeTest() {
        System.out.println("Running add/isEmpty/Size test.");
        ArrayDeque<String> ald1 = new ArrayDeque<>();

        boolean passed = checkEmpty(true, ald1.isEmpty());

        ald1.addFirst("1");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        passed = checkSize(1, ald1.size()) && passed;
        passed = checkEmpty(false, ald1.isEmpty()) && passed;

        ald1.addLast("2");
        passed = checkSize(2, ald1.size()) && passed;

        ald1.addLast("3");
        passed = checkSize(3, ald1.size()) && passed;

        System.out.println("Printing out deque: ");
        ald1.printDeque();

        ald1.addLast(" 4");
        ald1.addLast(" 5");
        ald1.addLast(" 6");
        ald1.addLast(" 7");
        ald1.addLast(" 8");
        ald1.printDeque();
        System.out.println("应当按顺序输出全部的元素");


        ald1.addLast(" 114");
        ald1.addLast(" 115");
        ald1.addLast(" 116");
        ald1.printDeque();
        System.out.println("应当扩容之后按顺序输出全部的元素");

        printTestStatus(passed);
    }

    public static void addTest1()
    {
        System.out.println("Running add test1.");
        ArrayDeque<String> ald1 = new ArrayDeque<>();

        ald1.addFirst("1");
        ald1.addFirst("0");
        ald1.addLast("2");
        ald1.addLast("3");
        ald1.addLast("4");
        ald1.addLast("5");
        ald1.addLast("6");
        ald1.addLast("7");
        ald1.printDeque();

        ald1.addLast("8");
        ald1.addLast("9");
        ald1.addLast("10");
        ald1.addLast("11");
        ald1.printDeque();
        System.out.println("Finished.");
    }

    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public static void addRemoveTest1()
    {
        System.out.println("Running add and Remove test.");
        ArrayDeque<Integer> ald1 = new ArrayDeque<>();
        for(int i = 0; i < 40; i++)
        {
            ald1.addLast(i);
        }
        ald1.printDeque();
        System.out.println("按顺序输出，应该正常扩容");

        for(int i = 0; i < 25; i++)
        {
            ald1.removeFirst();
        }
        ald1.printDeque();
        System.out.println("按顺序输出，应该正常缩容");
    }

    public static void addRemoveTest2()
    {
        System.out.println("Running add and Remove test2.");
        ArrayDeque<Integer> ald1 = new ArrayDeque<>();
        for(int i = 0; i < 40; i++)
        {
            ald1.addLast(i);
        }

        for(int i = 0; i < 10; i++)
        {
            ald1.addFirst(-i - 1);
        }
        ald1.printDeque();
        System.out.println("按顺序输出，应该正常扩容");

        for(int i = 0; i < 35; i++)
        {
            ald1.removeLast();
        }
        ald1.printDeque();
        System.out.println("按顺序输出，应该正常缩容");
    }


    public static void main(String[] args)
    {
        //addIsEmptySizeTest();
        //addTest1();
        //addRemoveTest1();
        addRemoveTest2();
    }
}
