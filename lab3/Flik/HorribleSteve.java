import static org.junit.Assert.*;
import org.junit.Test.*;
import org.junit.runners.JUnit4;

public class HorribleSteve {
    public static void main(String [] args) throws Exception {
        int i = 0;
        for (int j = 0; i < 500; ++i, ++j) {
            if (!Flik.isSameNumber(i, j)) {
//                throw new Exception(
//                        String.format("i:%d not same as j:%d ??", i, j));
                System.out.println("i = " + i + ", j = " + j);
                assertTrue(i == j);
            }
        }
        System.out.println("i is " + i);
    }
}
