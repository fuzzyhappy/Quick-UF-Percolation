import java.io.IOException;
import java.io.*;

public class PercCalc{
    public static void main(String[] args) throws IOException{
        PrintWriter writer = new PrintWriter("out.txt");
        double accum = 0;
        for (int i = 50; i <= 400; i += 5){
            writer.print(i + ": ");
            for (int j = 0; j < 30; j++){
                PercStatTracked stat = new PercStatTracked(i, 500);
                accum += stat.getTime();
            }
            writer.println(accum/30);
            accum = 0;
        }
        writer.close();
    }
}
