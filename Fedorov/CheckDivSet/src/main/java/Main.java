import com.mathsystem.api.graph.GraphFactory;


import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/main/resources/test.txt"));
        CheckDivSet checkDivSet = new CheckDivSet();
        boolean result = checkDivSet.execute(testGraph);
        System.out.println(result);
    }
}
