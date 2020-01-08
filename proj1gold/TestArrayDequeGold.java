import static org.junit.Assert.*;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestArrayDequeGold
{
    /**
     * Test addFirst and addLast
     */
    @Test
    public void testStudentAdd()
    {
        // Create a stream to hold the output
        ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
        ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
        PrintStream ps1 = new PrintStream(baos1);
        PrintStream ps2 = new PrintStream(baos2);
        // IMPORTANT: Save the old System.out!
        PrintStream old = System.out;

        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> good1 = new ArrayDequeSolution<>();

        for (int i = 0; i < 10; i += 1) {
            Integer randomNum = StdRandom.uniform(100);

            if (randomNum < 50) {
                sad1.addLast(i);
                good1.addLast(i);
            } else {
                sad1.addFirst(i);
                good1.addFirst(i);
            }

            // Tell Java to use your special stream
            System.setOut(ps1);
            // Print some output: goes to your special stream
            sad1.printDeque();
            // Put things back
            System.out.flush();

            System.setOut(ps2);
            good1.printDeque();
            System.out.flush();

            System.setOut(old);
            // Show what happened
            assertEquals("Different output!\n" +
                        "Student output is: \n" + baos1.toString() +
                        "\nwhile ideal output should be: \n" +
                        baos2.toString(),
                        baos1.toString(),
                        baos2.toString());
            }
    }

    @Test
    public void testAddAndRemove1()
    {
        // Create a stream to hold the output
        ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
        ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
        PrintStream ps1 = new PrintStream(baos1);
        PrintStream ps2 = new PrintStream(baos2);
        // IMPORTANT: Save the old System.out!
        PrintStream old = System.out;

        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> good1 = new ArrayDequeSolution<>();

        for (int i = 0; i < 10; i += 1) {
            Integer randomNum = StdRandom.uniform(100);

            if (randomNum < 50) {
                sad1.addLast(i);
                good1.addLast(i);
            } else {
                sad1.addFirst(i);
                good1.addFirst(i);
            }

            // Tell Java to use your special stream
            System.setOut(ps1);
            // Print some output: goes to your special stream
            sad1.printDeque();
            // Put things back
            System.out.flush();

            System.setOut(ps2);
            good1.printDeque();
            System.out.flush();

            System.setOut(old);
            // Show what happened
            assertEquals("Different output!\n" +
                            "Student output is: \n" + baos1.toString() +
                            "\nwhile ideal output should be: \n" +
                            baos2.toString(),
                    baos1.toString(),
                    baos2.toString());
        }

        for (int i = 0; i < 10; i += 1) {
            Integer sadLast;
            Integer goodLast;

            sadLast = sad1.removeLast();
            goodLast = good1.removeLast();
            assertEquals("remove last element is not equal!", goodLast, sadLast);

            // Tell Java to use your special stream
            System.setOut(ps1);
            // Print some output: goes to your special stream
            sad1.printDeque();
            // Put things back
            System.out.flush();

            System.setOut(ps2);
            good1.printDeque();
            System.out.flush();

            System.setOut(old);
            // Show what happened
            assertEquals("Different output!\n" +
                            "Student output is: \n" + baos1.toString() +
                            "\nwhile ideal output should be: \n" +
                            baos2.toString(),
                    baos1.toString(),
                    baos2.toString());

        }
    }

    @Test
    public void testAddAndRemove2()
    {
        // Create a stream to hold the output
        ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
        ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
        ByteArrayOutputStream functionRecordBaos = new ByteArrayOutputStream();

        PrintStream ps1 = new PrintStream(baos1);
        PrintStream ps2 = new PrintStream(baos2);
        PrintStream pfrb = new PrintStream(functionRecordBaos);

        // IMPORTANT: Save the old System.out!
        PrintStream old = System.out;

        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> good1 = new ArrayDequeSolution<>();

        for (int i = 0; i < 7; i += 1) {
            Integer randomNum = StdRandom.uniform(100);

            System.setOut(pfrb);
            if (randomNum < 50) {
                sad1.addLast(i);
                good1.addLast(i);
                System.out.println("addLast(" + i + ")");
            } else {
                sad1.addFirst(i);
                good1.addFirst(i);
                System.out.println("addFirst(" + i + ")");
            }

            // Tell Java to use your special stream
            System.setOut(ps1);
            // Print some output: goes to your special stream
            sad1.printDeque();
            // Put things back
            System.out.flush();

            System.setOut(ps2);
            good1.printDeque();
            System.out.flush();

            System.setOut(old);
            // Show what happened
            assertEquals("Different output!\n" +
                            "Student output is: \n" + baos1.toString() +
                            "\nwhile ideal output should be: \n" +
                            baos2.toString() +
                            "\nFunction series is:\n" + functionRecordBaos.toString() + "\n",
                    baos1.toString(),
                    baos2.toString());
        }

        System.setOut(old);
        System.out.println("add 10 elements pasted");

        for (int i = 0; i < 7; i += 1) {
            Integer sadFirst;
            Integer goodFirst;

            sadFirst = sad1.removeFirst();
            goodFirst = good1.removeFirst();
            System.setOut(pfrb);
            System.out.println("removeFirst(" + i + ")");

            assertEquals("remove first element is not equal!\n" +
                                  "Function series is:\n" + functionRecordBaos.toString() + "\n", goodFirst, sadFirst);

            // Tell Java to use your special stream
            System.setOut(ps1);
            // Print some output: goes to your special stream
            sad1.printDeque();
            // Put things back
            System.out.flush();

            System.setOut(ps2);
            good1.printDeque();
            System.out.flush();

            System.setOut(old);
            // Show what happened
            assertEquals("Different output!\n" +
                            "Student output is: \n" + baos1.toString() +
                            "\nwhile ideal output should be: \n" +
                            baos2.toString() +
                            "\nFunction series is:\n" + functionRecordBaos.toString() + "\n",
                    baos1.toString(),
                    baos2.toString());

        }
        System.setOut(old);
        System.out.println("Passed Test addAndRemove3");
    }
}
