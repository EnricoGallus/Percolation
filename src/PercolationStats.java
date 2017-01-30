import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Created by enrico on 30.01.17.
 */
public class PercolationStats {

    private int runs;
    private double[] thresholds;

    /**
     * perform trials independent experiments on an n-by-n grid
     * @param n
     * @param trials
     */
    public PercolationStats(int n, int trials) {
        if (n < 1 || trials < 1) {
            throw new IllegalArgumentException();
        }

        runs = trials;
        thresholds = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {

                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                percolation.open(row, col);
            }

            thresholds[i] = (double) percolation.numberOfOpenSites() / (n * n);
        }
    }

    /**
     * sample mean of percolation threshold
     * @return
     */
    public double mean() {
        return StdStats.mean(thresholds);
    }

    /**
     * sample standard deviation of percolation threshold
     * @return
     */
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    /**
     * low  endpoint of 95% confidence interval
     * @return
     */
    public double confidenceLo() {
        return mean() - (1.96 * stddev()) / Math.sqrt(runs);
    }

    /**
     * high endpoint of 95% confidence interval
     * @return
     */
    public double confidenceHi() {
        return mean() + (1.96 * stddev()) / Math.sqrt(runs);
    }

    /**
     * test client (described below)
     *  two command-line arguments n and T, performs T independent computational experiments
     *  on an n-by-n grid, and prints the sample mean, sample standard deviation,
     *  and the 95% confidence interval for the percolation threshold
     *  Use StdRandom to generate random numbers; use StdStats to compute the sample mean and sample standard deviation
     * @param args
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException();
        }

        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, t);

        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        String confidence = ps.confidenceLo() + ", " + ps.confidenceHi();
        StdOut.println("95% confidence interval = " + confidence);
    }
}
