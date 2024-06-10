import com.mathsystem.api.graph.GraphFactory;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        TransitiveClosure transitiveClosure = new TransitiveClosure();
        var testGraph = GraphFactory.loadGraphFromFile(new File("/home/mikhail/github/mishIgr_wise-task-algorithms-2024/Fedorov/TransitiveClosure/src/main/resources/graph.txt"));
        boolean result = transitiveClosure.execute(testGraph);
        System.out.println(result);
    }
}
