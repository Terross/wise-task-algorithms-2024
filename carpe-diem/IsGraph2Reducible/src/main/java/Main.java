import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        IsGraph2Reducible isGraph2Reducible = new IsGraph2Reducible();
        var testGraph = GraphFactory.loadGraphFromFile(new File("carpe-diem/IsGraph2Reducible/src/main/resources/graph.txt"));
        boolean result = isGraph2Reducible.execute(testGraph);
        System.out.println(result);

    }
}

// проверить функцию на полном графе
