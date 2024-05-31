import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/main/resources/graph7.txt"));
        var solution = new CountDeficit228();
        System.out.println(solution.execute(graph));
    }
}