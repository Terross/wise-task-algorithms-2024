import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/main/resources/graph7.txt"));

        isGraphMinimal isGraphMinimal = new isGraphMinimal();
        boolean result = isGraphMinimal.execute(testGraph);
        System.out.println(result);
    }
}