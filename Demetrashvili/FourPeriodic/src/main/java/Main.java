
import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        FourPeriodic fourPeriodic = new FourPeriodic();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/main/resources/graph.txt"));
        boolean result = fourPeriodic.execute(testGraph);
        System.out.println(result);
    }
}