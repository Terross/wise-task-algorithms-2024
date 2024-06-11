import com.mathsystem.api.graph.GraphFactory;

import java.io.FileNotFoundException;

import java.io.File;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Odd_component cmp = new Odd_component();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/main/resources/graph7_2.txt"));
        Integer result = cmp.execute(testGraph);
        System.out.println(result);
    }
}