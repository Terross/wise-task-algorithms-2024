import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ChromaticNumberEqualSix example = new ChromaticNumberEqualSix();
        var testGraph = GraphFactory.loadGraphFromFile(new File("C:\\Users\\Наталья\\IdeaProjects\\wise-task-algorithms-2024-main\\ChromaticNumberEqualSix\\src\\main\\resources\\graph.txt"));
        boolean result = example.execute(testGraph);
        System.out.println(result);
    }
}