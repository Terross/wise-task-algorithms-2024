import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        FindPaws findPaws = new FindPaws();
        var testGraph = GraphFactory.loadGraphFromFile(new File("FindPaws/src/main/resources/k13.txt"));
        boolean result = findPaws.execute(testGraph);
        System.out.println(result);
    }
}
