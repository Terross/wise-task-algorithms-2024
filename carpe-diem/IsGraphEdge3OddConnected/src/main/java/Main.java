import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        IsGraphEdge3OddConnected isGraphEdge3OddConnected = new IsGraphEdge3OddConnected();
        var testGraph = GraphFactory.loadGraphFromFile(new File("carpe-diem/IsGraphEdge3OddConnected/src/main/resources/graph.txt"));
        boolean result = isGraphEdge3OddConnected.execute(testGraph);
        System.out.println(result);

    }
}

// проверить функцию на полном графе
