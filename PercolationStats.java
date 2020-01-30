/**
 * % java PercolationStats
 * Mean: 0.5924103999999998
 * Standard Deviation: 0.00974898473141137
 * Confidence Interval: 0.5918061516661407 - 0.5930146483338589
 *
 * Estimates the true average proportion of open sites needed before a grid percolates.
 * Also computes a 95% confidence interval around that estimate.
 *
 * @EWang @1.8 @28.1.2020
 */
public class PercolationStats
{
   private final double confidenceCoeff = 1.96;
   private double mean;
   private double stdDev;
   private double confidenceLow;
   private double confidenceHigh;

   public PercolationStats(int N, int T){
      // accumulator variable
      double accum = 0;
      double[] statistics = new double[T];

      for (int t = 0; t < T; t++){
         Percolation sim = new Percolation(N);
         while (!sim.percolates()){
            sim.open((int)(Math.random()*N), (int)(Math.random()*N));
         }
         // finds the statistic of proportion of open sites for a percolating grid
         statistics[t] = (double) sim.numberOfOpenSites() / (N*N);
         accum += statistics[t];
      }
      mean = accum / T;

      accum = 0;
      // finds std deviation
      for (double stat : statistics){
         accum += (stat - mean) * (stat - mean);
      }
      accum /= T - 1;
      stdDev = Math.sqrt(accum);

      // finds bounds of 95% confidence interval
      confidenceHigh = mean + confidenceCoeff * stdDev / Math.sqrt(T);
      confidenceLow = mean - confidenceCoeff * stdDev / Math.sqrt(T);

   }

   public double mean(){
      return mean;
   }

   public double stddev(){
      return stdDev;
   }

   public double confidenceLow(){
      return confidenceLow;
   }

   public double confidenceHigh(){
      return confidenceHigh;
   }

   public static void main(String[] args){
       PercolationStats percStat = new PercolationStats(200, 1000);
       System.out.println("Mean: " + percStat.mean());
       System.out.println("Standard Deviation: " + percStat.stddev());
       System.out.println("Confidence Interval: " +
               percStat.confidenceLow() + " - " + percStat.confidenceHigh());
       System.out.println();
   }
}
