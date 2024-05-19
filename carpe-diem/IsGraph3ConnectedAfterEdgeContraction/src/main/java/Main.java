import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        IsGraph3ConnectedAfterEdgeContraction  isGraph3ConnectedAfterEdgeContraction = new IsGraph3ConnectedAfterEdgeContraction();
        var testGraph = GraphFactory.loadGraphFromFile(new File("carpe-diem/IsGraph3ConnectedAfterEdgeContraction/src/main/resources/graph.txt"));
        boolean result = isGraph3ConnectedAfterEdgeContraction.execute(testGraph);
        System.out.println(result);

    }
}

