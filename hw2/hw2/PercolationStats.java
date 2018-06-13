package hw2;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int trials;
    private double mean;
    private double stddev;
    
    /** perform trials independent experiments on an n-by-n grid */
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Both n :" + n + " and trials: " + trials + "should > 0 ");
        }
        double[] fractionArray = new double[trials];
        this.trials = trials;
        
        for (int i = 0; i < trials; i++) {
            Percolation perfrom = new Percolation(n);
            while (!perfrom.percolates()) {
                int x = StdRandom.uniform(0, n) + 1;        //random [1,n]
                int y = StdRandom.uniform(0, n) + 1;
                perfrom.open(x, y);
            }
            double fraction = perfrom.numberOfOpenSites() / (Math.pow(n, 2));
            fractionArray[i] = fraction;
        }
        this.mean = StdStats.mean(fractionArray);
        this.stddev = StdStats.stddev(fractionArray);
    }
    /* sample mean of percolation threshold */
    public double mean() {
        return this.mean;
    }
    /* sample standard deviation of percolation threshold */
    public double stddev() {
        if (this.trials == 1) this.stddev = Double.NaN;
        return this.stddev;
    }
    /* low  endpoint of 95% confidence interval */
    public double confidenceLo() {
        return this.mean() - 1.96 * this.stddev() / Math.sqrt(this.trials);
    }
    /* high endpoint of 95% confidence interval */
    public double confidenceHi() {
        return this.mean() + 1.96 * this.stddev() / Math.sqrt(this.trials);
    }
    
    public static void main(String[] args) {        // test client (described below)
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        Stopwatch timer = new Stopwatch();
        PercolationStats sta = new PercolationStats(n, trials);
        System.out.println("mean                    = " + sta.mean());
        System.out.println("stddev                  = " + sta.stddev());
        System.out.println("95% confidence interval = [" + sta.confidenceLo() + ", " + sta.confidenceHi() + "]");
        double totalRuntime = timer.elapsedTime();
        System.out.println("Total Runtime of PercolationStats is: " + totalRuntime);
    }
}

