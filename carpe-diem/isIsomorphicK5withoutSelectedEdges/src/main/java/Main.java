import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        IsIsomorphicK5withoutSelectedEdges funcObj = new IsIsomorphicK5withoutSelectedEdges();
        var testGraph = GraphFactory.loadGraphFromFile(new File("/home/sadly/Documents/KITG/repo/wise-task-algorithms-2024/carpe-diem/isIsomorphicK5withoutSelectedEdges/src/main/resources/graph.txt"));
        boolean result = funcObj.execute(testGraph);
        System.out.println(result);
    }
}
