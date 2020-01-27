
public class Percolation
{
  private int N;
  private WeightedQuickUnionUF openNodes;
  private WeightedQuickUnionUF filledNodes;
  private boolean[][] openSites;
  private int open = 0;
  private int virtualSource = 0;
  private int virtualSink;

  public Percolation(int N){
    this.N = N;
    virtualSink = N * N + 1;
    openSites = new boolean[N][N];
    openNodes = new WeightedQuickUnionUF(N*N + 2);
    filledNodes = new WeightedQuickUnionUF(N*N + 1);
  }

  public void open(int row, int col) {
    if (!openSites[row][col]){
      open++;
      openSites[row][col] = true;
      connect(row - 1, col, row, col);
      connect(row, col - 1, row, col);
      connect(row + 1, col, row, col);
      connect(row, col + 1, row, col);
      if (row == 0){
        openNodes.union(virtualSource, INDEX(row, col));
        filledNodes.union(virtualSource, INDEX(row, col));
      }
      if (row == N - 1){
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

  private int INDEX(int row, int col){
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
