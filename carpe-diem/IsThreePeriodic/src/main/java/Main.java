import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        IsThreePeriodic IsThreePeriodic = new IsThreePeriodic();
        var testGraph = GraphFactory.loadGraphFromFile(new File("/home/cuga/C&TG/colloquium2024/carpe-diem/IsThreePeriodic/src/main/resources/graph.txt"));
        boolean result = IsThreePeriodic.execute(testGraph);
        System.out.println("Раскраска графа 3-х переодичная? " + result);
    }
}