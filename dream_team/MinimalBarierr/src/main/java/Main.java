import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        MinimalBarrier a = new MinimalBarrier();
        var testGraph = GraphFactory.loadGraphFromFile(new File("/home/dasha/4th_semestr/blyat/MinimalBarrier/src/test/resources/false/ther_is_less.txt"));
        System.out.println(a.execute(testGraph));
    }
}