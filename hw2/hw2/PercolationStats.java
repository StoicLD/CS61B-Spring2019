package hw2;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.introcs.StdRandom;

public class PercolationStats {
    private int times;
    double[] fractions;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf)
    {
        fractions = new double[T];
        times = T;
        for(int i = 0; i < T; i++)
        {
            Percolation pc = pf.make(N);
            int count = 0;
            while (!pc.percolates()) {
                int index = StdRandom.uniform(N * N);
                if(!pc.isOpen(index)) {
                    pc.open(index);
                    count++;
                }
            }
            fractions[i] = (double)count / (double)(N * N);
            System.out.println("No." + i + ":  p = " + fractions[i]);
        }
    }

    // sample mean of percolation threshold
    public double mean()
    {
        return StdStats.mean(fractions);
    }

    // sample standard deviation of percolation threshold
    public double stddev()
    {
        return StdStats.stddev(fractions);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow()
    {
        return (mean() - (1.96 * stddev() / Math.sqrt(times)));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh()
    {
        return (mean() + (1.96 * stddev() / Math.sqrt(times)));
    }

    //用来做单元测试
    public static void main(String args[])
    {
        PercolationStats pc = new PercolationStats(100, 20, new PercolationFactory());
        System.out.println("mean: " + pc.mean());
        System.out.println("standard division is: " + pc.stddev());
    }
}
