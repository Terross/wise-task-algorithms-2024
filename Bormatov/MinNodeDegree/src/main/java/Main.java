import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] argv) throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/main/resources/graph.txt"));
        var solution = new MinNodeDegree();
        System.out.println(solution.execute(graph));
    }
}
