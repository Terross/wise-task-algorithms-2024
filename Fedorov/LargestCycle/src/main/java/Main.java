import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.UUID;
import com.mathsystem.api.graph.model.Vertex;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        // создаем класс
        LargestCycle nodesEqEdges = new LargestCycle();
        // считываем граф с txt файла
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/main/resources/graph.txt"));
        // выполняем функцию поска наибольшего цикла для полученного гарафа
        int result = nodesEqEdges.execute(testGraph);
        // выводим результат
        System.out.println(result);

    }
}
