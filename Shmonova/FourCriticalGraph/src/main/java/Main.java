import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        FourCriticalGraph example = new FourCriticalGraph();
        var testGraph = GraphFactory.loadGraphFromFile(new File("C:\\Users\\Наталья\\IdeaProjects\\wise-task-algorithms-2024-main\\FourCriticalGraph\\src\\main\\resources\\graph.txt"));
        boolean result = example.execute(testGraph);
        System.out.println(result);
    }
}