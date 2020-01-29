/**
 * N^2 logN complexity algorithm for Percolation with backwash solved.
 *
 * Here's the data supporting that this algorithm is N^2 logN:
 *   https://docs.google.com/spreadsheets/d/1BWYulRolDsXQI-KgDsa2CADnZx9h59CijIELTTSoBm8/edit?usp=sharing
 * If I really wanted to be accurate, I would say the complexity is closer to 0.5 N^2 (logN + 1)
 *
 * Just doing it by hand, I can calculate that the complexity is very close to:
                2N + 3 + (0.59 N^2)[(2/N + 2)(logN)]
 * The one-time cost of constructing my 2 union find structs is N+1 and N+2, respectively.
 * On average, the percolation continues until 59% of its grid area (N*N) is filled.
 * I make the assumption that each loop creates, on average, one union (this is probably not true).
 * On each of its loops, it has a 1/N chance to make a connection between the source and the top row or
 *  the sink and the bottom row, respectively, or a 2/N chance overall to make an extra connection
 *  (Connection/union is logN cost, as is find()).
 * Lastly, it checks if the sink and source are connected to continue the loop, which is logN cost.
 *
 * @EWang @1.8 @28.1.2020
 */
public class Percolation
{
  private int N;
  private WeightedQuickUnionUF openNodes;
  private WeightedQuickUnionUF filledNodes;
  private boolean[][] openSites; // 2d array representing the grid of sites (true = open, false = closed)
  private int open = 0; // number of open sites
  private int virtualSource = 0; // imaginary source at the top of the grid
  private int virtualSink; // imaginary sink at the bottom of the grid

  public Percolation(int N){
    this.N = N;
    virtualSink = N * N + 1;
    openSites = new boolean[N][N];
    openNodes = new WeightedQuickUnionUF(N*N + 2); // open nodes accounts for the source and sink
    filledNodes = new WeightedQuickUnionUF(N*N + 1); // filled nodes only counts the source
  }

  public void open(int row, int col) {
    if (!openSites[row][col]){
      open++;
      openSites[row][col] = true;
      // connects to adjacent open sites
      connect(row - 1, col, row, col);
      connect(row, col - 1, row, col);
      connect(row + 1, col, row, col);
      connect(row, col + 1, row, col);
      if (row == 0){ // if the site is in the top row, the site is connected to the source
        openNodes.union(virtualSource, INDEX(row, col));
        filledNodes.union(virtualSource, INDEX(row, col));
      }
      if (row == N - 1){ // if the site is in the bottom row, the site is connected to the sink
        openNodes.union(virtualSink, INDEX(row, col));
      }
    }
  }
  public boolean isOpen(int row, int col){
    return (openSites[row][col]);
  }

  public boolean isFull(int row, int col){
    return filledNodes.connected(virtualSource, INDEX(row, col));
  }

  public int numberOfOpenSites(){
    return(open);
  }

  public boolean percolates(){
    return (openNodes.connected(virtualSource, virtualSink));
  }

  private int INDEX(int row, int col){ // gets the site's index in the unionfind structs
    return (N*row + col + 1);
  }

  private void connect(int nrow, int ncol, int row, int col){
    try {
      if (openSites[nrow][ncol]) {
        openNodes.union(INDEX(nrow, ncol), INDEX(row, col));
        filledNodes.union(INDEX(nrow, ncol), INDEX(row, col));
      }
    }
    catch (IndexOutOfBoundsException E){}
  }

  public static void main(String[] args) throws Exception{
    Percolation p = new Percolation(14);
    while(!p.percolates()){
      p.open((int)(Math.random()*14), (int)(Math.random()*14));
      for (int i = 0; i < 14; i++){
        for (int j = 0; j < 14; j++){
          if (p.isOpen(i, j)){
            if (p.isFull(i, j)){
              System.out.print("_");
            }
            else {
              System.out.print(" ");
            }
          }
          else {
            System.out.print("X");
          }
        }
        System.out.println();
      }
      System.out.println();
    }
    System.out.println(p.numberOfOpenSites());

  }

}
