import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        CheckThreePaths checkThreePaths = new CheckThreePaths();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkThreePaths/undirected/wheel.txt"));
        boolean result = checkThreePaths.execute(testGraph);
        System.out.println(result);
    }
}