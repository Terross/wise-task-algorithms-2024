import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ChromaticNumberEq5 ChromaticNumberEq5 = new ChromaticNumberEq5();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/main/resources/graph.txt"));
        boolean result = ChromaticNumberEq5.execute(testGraph);
        System.out.println(result);
    }
}
