import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        IsOddCycle isOddCycle = new IsOddCycle();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/main/resources/isOddCycle/undirected/big_flower_correct.txt"));
        boolean result = isOddCycle.execute(testGraph);
        System.out.println(result);
    }
}