import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Simple2ways Simple2ways = new Simple2ways();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/main/resources/graph_false_two_strong_connection.txt"));
        boolean result = Simple2ways.execute(testGraph);
        System.out.println(result);
    }
}