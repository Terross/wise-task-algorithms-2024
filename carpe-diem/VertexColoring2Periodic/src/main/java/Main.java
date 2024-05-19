import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        VertexColoring2Periodic vertexColoring = new VertexColoring2Periodic(); // Создаем экземпляр нашего класса
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/main/resources/graph.txt")); // Загружаем граф
        boolean is2Periodic = vertexColoring.execute(testGraph); // Проверяем 2-периодичность
        System.out.println(is2Periodic? "Граф является 2-периодическим" : "Граф не является 2-периодическим");
    }
}

