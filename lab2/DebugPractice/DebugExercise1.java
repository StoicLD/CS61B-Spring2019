import org.junit.Test;

/**
 * Exercise for learning how the debug, breakpoint, and step-into
 * feature work.
 */
class TestClass
{
    int x;
    TestClass next;
    public TestClass()
    {
        x = 0;
        next = null;
    }
    public TestClass(int _x, TestClass _next)
    {
        x = _x;
        next = _next;
    }
    public TestClass Change(TestClass t)
    {
        TestClass result = new TestClass(t.x + 10, null);
        TestClass ptr = t;
        while(ptr.next != null)
        {
            result.next = new TestClass(ptr.next.x, null);
            ptr = ptr.next;
        }
        return result;
    }

}

public class DebugExercise1 {
    public static int divideThenRound(int top, int bottom) {
        int quotient = top / bottom;
        int result = Math.round(quotient);
        return result;
    }

    public static void main(String[] args) {
        TestClass origin = new TestClass(10, new TestClass());
        TestClass newOrigin = origin.Change(origin);
        System.out.println(origin.x);
        System.out.println(newOrigin.x);
        int t = 10;
        int b = 2;
        int result = divideThenRound(t, b);
        System.out.println("round(" + t + "/" + b + ")=" + result);

        int t2 = 9;
        int b2 = 4;
        int result2 = divideThenRound(t2, b2);
        System.out.println("round(" + t2 + "/" + b2 + ")=" + result2);

        int t3 = 3;
        int b3 = 4;
        int result3 = divideThenRound(t3, b3);
        System.out.println("round(" + t3 + "/" + b3 + ")=" + result3);
    }
}
