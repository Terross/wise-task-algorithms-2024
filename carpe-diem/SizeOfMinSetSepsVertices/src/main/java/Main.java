import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        SizeOfMinSetSeparatingVertices sizeOfMinSetSeparatingVertices = new SizeOfMinSetSeparatingVertices();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/main/resources/graph.txt"));
        Integer result = sizeOfMinSetSeparatingVertices.execute(testGraph);
        System.out.println(result);
    }
}
