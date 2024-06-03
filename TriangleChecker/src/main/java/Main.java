import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        TriangleChecker tr = new TriangleChecker();
        var testGraph = GraphFactory.loadGraphFromFile(new File("C:\\Users\\пользователь\\IdeaProjects\\TriangleChecker\\src\\main\\resources\\graph2(false).txt"));
        boolean result = tr.execute(testGraph);
        System.out.println(result);
    }
}