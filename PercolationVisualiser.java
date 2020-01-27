import java.util.Scanner;
import java.io.IOException;
import java.io.File;

public class PercolationVisualiser
{
    private static int N;
    public static void main(String[] args) throws IOException
    {
        Scanner fileReader = new Scanner(new File("in.txt"));
        N = Integer.parseInt(fileReader.nextLine());
        Percolation sim = new Percolation(N);

        StdDraw.setXscale(0, N);
        StdDraw.setYscale(0, N);
        StdDraw.setPenColor(0, 0, 0);
        StdDraw.filledSquare(N/2, N/2, N/2);

        while (fileReader.hasNextLine()){
            int x = Integer.parseInt(fileReader.next());
            int y = Integer.parseInt(fileReader.next());
            sim.open(x, y);
            StdDraw.setPenColor(255, 255, 255);
            StdDraw.filledSquare(CANVASCOORD(y + 0.5), CANVASCOORD(x + 0.5), 0.45);
            for (int i = 0; i < N; i++){
                for (int j = 0; j < N; j++){
                    if (sim.isFull(i, j)){
                        //System.out.println(i + " " + j);
                        StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
                        StdDraw.filledSquare(x + 0.5, CANVASCOORD(y + 0.5), 0.45);
                    }
                }
            }
            for (int i = 0; i < 14; i++){
                for (int j = 0; j < 14; j++){
                    if (sim.isOpen(i, j)){
                        if (sim.isFull(i, j)){
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
    }
    private static double CANVASCOORD(double i){
        return N - i;
    }

    private static double drawSite(double i){
        return N - i;
    }
}