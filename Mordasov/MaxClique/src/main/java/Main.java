import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        MaxClique max_clique = new MaxClique();
         var testGraph = GraphFactory.loadGraphFromFile(new File("D:\\work\\java\\MaxClique\\MaxClique\\src\\main\\resources\\4graph.txt"));
//        var testGraph = GraphFactory.loadGraphFromFile(new File("D:\\work\\java\\MaxClique\\MaxClique\\src\\test\\resources\\graph3.txt"));
        int result = max_clique.execute(testGraph);
        System.out.println(result);
    }
}
