import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    //private static final Set<String> exclusions = Set.of();

    public static void main(String[] args) throws FileNotFoundException {
        TwoOddCon twoOddCon = new TwoOddCon();
        // "TwoOddCon/src/main/resources/k13.txt"
        var testGraph = GraphFactory.loadGraphFromFile(new File("TwoOddCon/src/test/resources/true1.txt"));
        boolean result = twoOddCon.execute(testGraph);
        System.out.println(result);
    }
}
