import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ChromaticNumber4 ChromaticNumber4 = new ChromaticNumber4();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/main/resources/graph1.txt"));
        boolean result = ChromaticNumber4.execute(testGraph);
        System.out.println(result);
    }
}
