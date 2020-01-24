public class Percolation{
  private WeightedQuickUnionUF uf;
  private int N;
  private boolean[][] sites;
  private boolean[][] filledSites;
  private int open = 0;
  private int virtualSource = 0;
  private int virtualSink;

  public Percolation(int N){
    this.N = N;
    virtualSink = N * N + 1;
    sites = new boolean[N][N];
    filledSites = new boolean[N][N];
    uf = new WeightedQuickUnionUF(N*N + 2);
    for (int i = 0; i < N; i++){
      uf.union(virtualSource, INDEX(0, i));
    }
    for (int i = 0; i < N; i++){
      uf.union(virtualSink, INDEX(N-1, i));
    }
  }

  public void open(int row, int col) {
    sites[row][col] = true;
    if (!sites[row][col]){
      try{
        if (sites[row - 1][col]){
          uf.union(INDEX(row - 1, col), INDEX(row, col));
          open++;
        }
      }
      catch (IndexOutOfBoundsException E){}
      try{
        if (sites[row][col - 1]){
          uf.union(INDEX(row, col - 1), INDEX(row, col));
          open++;
        }
      }
      catch (IndexOutOfBoundsException E){}
      try {
        if (sites[row + 1][col]) {
          uf.union(INDEX(row + 1, col), INDEX(row, col));
          open++;
        }
      }
      catch (IndexOutOfBoundsException E){}
      try {
        if (sites[row][col + 1]) {
          uf.union(INDEX(row, col + 1), INDEX(row, col));
          open++;
        }
      }
      catch (IndexOutOfBoundsException E){}
      if (uf.connected(virtualSource, INDEX(row, col))){
        filledSites[row][col] = true;
      }
    }
  }
  public boolean isOpen(int row, int col){
      return (sites[row][col]);
    }

  public boolean isFull(int row, int col){
      if (uf.connected(virtualSource, INDEX(row, col))){
        filledSites[row][col] = true;
        return true;
      }
      return false;
    }
  public int numberOfOpenSites(){
      return(open);
    }
  public boolean percolates(){
      return (uf.connected(virtualSource, virtualSink));
    }

    private int INDEX(int row, int col){
      return (N*row + col + 1);
    }
  public static void main(String[] args) throws Exception{
    Percolation p = new Percolation(7);
    while(!p.percolates()){
      p.open((int)(Math.random()*7), (int)(Math.random()*7));
      for (int i = 0; i < 7; i++){
        for (int j = 0; j < 7; j++){
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
      Thread.sleep(50);
    }
    for (int i = 0; i < 7; i++){
      for (int j = 0; j < 7; j++){
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

  }

}
