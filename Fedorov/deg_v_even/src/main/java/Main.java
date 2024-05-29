import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        deg_v_even dvn = new deg_v_even();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/main/resources/graph3d.txt"));
        boolean result = dvn.execute(testGraph);
        System.out.println(result);
    }
}