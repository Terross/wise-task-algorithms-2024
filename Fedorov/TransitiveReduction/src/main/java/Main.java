import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        TransitiveReduction transitiveReduction = new TransitiveReduction();
        var testGraph = GraphFactory.loadGraphFromFile(new File("/home/mikhail/github/mishIgr_wise-task-algorithms-2024/Fedorov/TransitiveReduction/src/test/resources/false_add.txt"));
        boolean result = transitiveReduction.execute(testGraph);
        System.out.println(result);
    }
}
