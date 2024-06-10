import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Connected_3 connected_3 = new Connected_3();
        var testGraph = GraphFactory.loadGraphFromFile(new File("D:\\work\\java\\Connected_3\\Connected_3\\src\\main\\resources\\3graph.txt"));
        boolean result = connected_3.execute(testGraph);
        System.out.println(result);
    }
}
