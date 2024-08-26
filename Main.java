import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Main{
    public static void main(String[] args) throws IOException{
        System.out.println("Hello");
        Recursos r = new Recursos(10, 10, 480);
        r.run();
        
        FileWriter arq = new FileWriter("C:/Users/Usuario/Documents/SO/Relatório.txt");
        PrintWriter gravarArq = new PrintWriter(arq);

        gravarArq.printf("lala");

        arq.close();
    }
}