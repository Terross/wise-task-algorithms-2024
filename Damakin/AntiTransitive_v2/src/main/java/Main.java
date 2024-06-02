import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        AntiTransitive_v2 AntiTransitive = new AntiTransitive_v2();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/main/resources/graph.txt"));
        boolean result = AntiTransitive.execute(testGraph);
        System.out.println(result);
    }
}