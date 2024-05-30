import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        AlmostPerfectMatch almostPerfectMatch = new AlmostPerfectMatch();

        var testGraph = GraphFactory.loadGraphFromFile(new File("C:\\Users\\я\\Downloads\\wise-task-algorithms-2024-main\\wise-task-algorithms-2024-main\\example\\AlmostPerfectMatch\\src\\main\\resources\\graph.txt"));
        boolean result = almostPerfectMatch.execute(testGraph);
        System.out.println(result);
    }
}
