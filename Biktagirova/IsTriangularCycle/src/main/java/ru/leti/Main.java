import com.mathsystem.api.graph.GraphFactory;
import ru.leti.IsTriangularCycle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;

public class Main{
    public static void main(String[] args) throws FileNotFoundException {
        IsTriangularCycle isTrC = new IsTriangularCycle();
        var testGraph = GraphFactory.loadGraphFromFile(new File("graphs/true1.txt"));
        boolean result = isTrC.execute(testGraph);
        System.out.println(result);
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(1);
        list.add(2);
    }
}