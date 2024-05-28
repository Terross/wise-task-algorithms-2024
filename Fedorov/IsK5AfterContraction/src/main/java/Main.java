import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        IsK5AfterContraction isK5AfterContraction = new IsK5AfterContraction();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/main/resources/true2.txt"));
        boolean result = isK5AfterContraction.execute(testGraph);
        System.out.println(result);
    }
}