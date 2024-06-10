import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        var testg = GraphFactory.loadGraphFromFile(new File("src/main/resources/k5.txt"));
        IsPancyclic pan = new IsPancyclic();
        boolean pan_int = pan.execute(testg);
        System.out.println(pan_int);
    }
}
