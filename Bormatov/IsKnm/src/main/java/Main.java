import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        IsKnm IsKnm = new IsKnm();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/main/resources/K43Bad.txt"));
        boolean result = IsKnm.execute(testGraph);
        System.out.println(result);
    }
}