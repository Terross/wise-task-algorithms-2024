import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        SumDegIsMoreOrEqVertexCount chromaticNumber2 = new SumDegIsMoreOrEqVertexCount();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/main/resources/graph.txt"));
        boolean result = chromaticNumber2.execute(testGraph);
        System.out.println(result);
    }
}
