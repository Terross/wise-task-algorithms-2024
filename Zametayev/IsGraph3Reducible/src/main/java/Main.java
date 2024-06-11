import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/main/resources/grahp.txt"));

        IsGraph3Reducible IsGraph3Reducible = new IsGraph3Reducible();
        boolean result = IsGraph3Reducible.execute(testGraph);
        System.out.println(result);
    }
}