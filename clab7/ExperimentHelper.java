/**
 * Created by hug.
 */
import edu.princeton.cs.algs4.AcyclicSP;
import junit.framework.*;
import org.junit.Assert;

public class ExperimentHelper
{
    static double log(int x, int base)
    {
        return (Math.log(x) / Math.log(base));
    }

    /**
     * Returns the internal path length for an optimum binary search tree of
     * size N. Examples:
     * N = 1, OIPL: 0
     * N = 2, OIPL: 1
     * N = 3, OIPL: 2
     * N = 4, OIPL: 4
     * N = 5, OIPL: 6
     * N = 6, OIPL: 8
     * N = 7, OIPL: 10
     * N = 8, OIPL: 13
     */
    public static int optimalIPL(int N)
    {
        //应该就是对于bushy的tree来说
        //假设N不会溢出
        if(N <= 0)
            throw new IllegalArgumentException();
        int lowBoundN = (int)Math.ceil(log(N + 1, 2));
        if(N > 3)
        {
            return (lowBoundN - 1) * N + lowBoundN - (int)Math.pow(2, lowBoundN) + 1;
        }
        else
        {
            return (N - 1);
        }
    }

    /**
     * Returns the average depth for nodes in an optimal BST of
     * size N.
     * Examples:
     * N = 1, OAD: 0
     * N = 5, OAD: 1.2
     * N = 8, OAD: 1.625
     *
     * @return
     */
    public static double optimalAverageDepth(int N)
    {
        return (double) optimalIPL(N) / N;
    }

    public static void main(String args[])
    {
        Assert.assertEquals(0, optimalIPL(1));
        Assert.assertEquals(1, optimalIPL(2));
        Assert.assertEquals(2, optimalIPL(3));
        Assert.assertEquals(4, optimalIPL(4));
        Assert.assertEquals(8, optimalIPL(6));
        Assert.assertEquals(10, optimalIPL(7));
        Assert.assertEquals(13, optimalIPL(8));
        Assert.assertEquals(34, optimalIPL(15));
        Assert.assertEquals(98, optimalIPL(31));

        Assert.assertEquals(0, optimalAverageDepth(1), 0.00001);
        Assert.assertEquals(1.2, optimalAverageDepth(5), 0.00001);
        Assert.assertEquals(1.625, optimalAverageDepth(8), 0.00001);

        System.out.println("You have passed all the tests!");
    }
}
