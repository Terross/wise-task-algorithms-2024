import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        FindCube chromaticNumber2 = new FindCube ();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/main/resources/nosimplecube.txt"));
        boolean result = chromaticNumber2.execute(testGraph);
        System.out.println(result);
    }
}
