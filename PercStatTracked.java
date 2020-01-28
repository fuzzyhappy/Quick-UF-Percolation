public class PercStatTracked
{
    private Stopwatch time;
    private double endTime;

    public PercStatTracked(int N, int T){

        time = new Stopwatch();
        for (int t = 0; t < T; t++){
            Percolation sim = new Percolation(N);
            while (!sim.percolates()){
                sim.open((int)(Math.random()*N), (int)(Math.random()*N));
            }
            endTime = time.elapsedTime();
        }

    }

    public double getTime(){
        return endTime;
    }

    public static void main(String[] args){
        PercolationStats percStat = new PercolationStats(200, 10000);
        System.out.println("Mean: " + percStat.mean());
        System.out.println("Standard Deviation: " + percStat.stddev());
        System.out.println("Confidence Interval: " +
                percStat.confidenceLow() + " - " + percStat.confidenceHigh());
        System.out.println();
    }
}
