import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        IsPlanar isPlanar = new IsPlanar();
        var testGraph = GraphFactory.loadGraphFromFile(new File("graphs/K33-1e"));
        boolean result = isPlanar.execute(testGraph);
        System.out.println(result);
    }
}
