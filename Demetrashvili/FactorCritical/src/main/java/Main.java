import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        FactorCritical factorCritical = new FactorCritical();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/main/resources/GraphLineTrue.txt"));
        Boolean result = factorCritical.execute(testGraph);
        System.out.println(result);
    }
}