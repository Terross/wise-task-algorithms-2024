import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        SizeOfMinSetSepsVertices sizeOfMinSetSepsVertices = new SizeOfMinSetSepsVertices();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/main/resources/graph.txt"));
        Integer result = sizeOfMinSetSepsVertices.execute(testGraph);
        System.out.println(result);
    }
}
