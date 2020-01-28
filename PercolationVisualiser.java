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
        StdDraw.clear(StdDraw.BLACK);

        while (fileReader.hasNextLine()){
            int x = Integer.parseInt(fileReader.next());
            int y = Integer.parseInt(fileReader.next());
            sim.open(x, y);

            for (int i = 0; i < N; i++){
                for (int j = 0; j < N; j++){
                    StdDraw.setPenColor(255, 255, 255);
                    if (sim.isOpen(i, j)){
                        if (sim.isFull(i, j)){
                            StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
                        }/**
                        else {
                            StdDraw.setPenColor(255, 255, 255);
                            drawSite(x, y);
                        }**/
                        drawSite(x, y);
                    }
                }
            }
            /**
            for (int i = 0; i < N; i++){
                for (int j = 0; j < N; j++){
                    if (sim.isFull(i, j)){
                        StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
                        drawSite(x, y);
                    }
                    else if (sim.isOpen(i, j)){
                        StdDraw.setPenColor(255, 255, 255);
                        drawSite(x, y);
                    }
                }
            }**/
            for (int i = 0; i < N; i++){
                for (int j = 0; j < N; j++){
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

    private static void drawSite(double x, double y){
        StdDraw.filledSquare(y + 0.5, N - x - 0.5, 0.45);
    }
}