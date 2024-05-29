import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        IsNormal isNormal = new IsNormal();
        var testGraph = GraphFactory.loadGraphFromFile(new File("graphs/gray"));
        boolean result = isNormal.execute(testGraph);
        System.out.println(result);
    }
}
