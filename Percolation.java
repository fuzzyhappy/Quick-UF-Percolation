public class Percolation{
  private WeightedQuickFind uf;
  private int N;
  private boolean[][] sites;
  private boolean[][] filledSites;
  public Percolation(int N){
    this.N = N;
    sites = new boolean[N][N];
    filledSites = new boolean[N][N];
    uf = new WeightedQuickFind(N*N);
  }
  public void open(int row, int col) {
    sites[row][col] = true;
    try{
      if (sites[row - 1][col]){
        uf.union(N*col + row - 1, row*col);
      }
    }
    catch (IndexOutOfBoundsException E){}
    try{
      if (sites[row][col - 1]){
        uf.union(N*(col - 1) + row, row*col);
      }
    catch (IndexOutOfBoundsException E){}
    try{
      if (sites[row + 1][col]){
        uf.union(N*col + row + 1, row*col);
      }
    catch (IndexOutOfBoundsException E){}
    try{
      if (sites[row][col + 1]){
        uf.union(N*(col + 1) + row, row*col);
      }
    catch (IndexOutOfBoundsException E){}
    if (uf.find(N*col + row) >= 0 && uf.find(N*col + row) < N){
      filledSites[row, col] = true;
    }
  }
  public boolean isOpen(int row, int col){}
  public boolean isFull(int row, int col){}
  public int numberOfOpenSites(){}
  public boolean percolates(){}
  public static void main(String[] args){}


}
