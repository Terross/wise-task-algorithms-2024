import com.mathsystem.api.graph.GraphFactory;



import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ThreeCritical nodesEqEdges = new ThreeCritical();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/main/resources/graph.txt"));
        boolean result = nodesEqEdges.execute(testGraph);
        System.out.println(result);
    }
}