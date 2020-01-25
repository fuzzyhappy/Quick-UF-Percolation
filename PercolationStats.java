import java.util.ArrayList;

public class PercolationStats {
   private double mean;
   private double stdDev;
   private double confidenceLow;
   private double confidenceHigh;

   public PercolationStats(int N, int T){
      double accum = 0;
      double[] statistics = new double[T];

      for (int t = 0; t < T; t++){
         Percolation sim = new Percolation(N);
         while (!sim.percolates()){
            sim.open((int)(Math.random()*N), (int)(Math.random()*N));
         }
         statistics[t] = (double) sim.numberOfOpenSites() / (N*N);
         accum += statistics[t];
      }
      mean = accum / T;

      accum = 0;
      for (double stat : statistics){
         accum += (stat - mean) * (stat - mean);
      }
      accum /= T - 1;
      stdDev = Math.sqrt(accum);

      confidenceHigh = mean + 1.96 * stdDev / Math.sqrt(T);
      confidenceLow = mean - 1.96 * stdDev / Math.sqrt(T);

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
