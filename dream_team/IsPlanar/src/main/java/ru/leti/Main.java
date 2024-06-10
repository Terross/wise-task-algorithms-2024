import com.mathsystem.api.graph.GraphFactory;
import ru.leti.IsPlanar;

import java.io.File;
import java.io.FileNotFoundException;

public class Main{
    public static void main(String[] args) throws FileNotFoundException {
        IsPlanar isPl = new IsPlanar();
        var testGraph = GraphFactory.loadGraphFromFile(new File("graphs/1-connected_k5.txt"));
        boolean result = isPl.execute(testGraph);
        System.out.println(result);
    }
}