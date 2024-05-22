import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        IsTwoCritical isTwoCritical = new IsTwoCritical();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/main/resources/false_graph.txt"));
        boolean result = isTwoCritical.execute(testGraph);
        System.out.println(result);
    }
}