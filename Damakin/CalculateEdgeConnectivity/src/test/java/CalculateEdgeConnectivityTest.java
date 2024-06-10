import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CalculateEdgeConnectivityTest {

    @Test
    public void CalculateEdgeConnectivityTest() throws FileNotFoundException {
        CalculateEdgeConnectivity CalculateEdgeConnectivity = new CalculateEdgeConnectivity();

        var Graph1 = GraphFactory.loadGraphFromFile(new File("src/test/resources/Graph1.txt"));
        var Graph2 = GraphFactory.loadGraphFromFile(new File("src/test/resources/Graph2.txt"));
        var Graph3 = GraphFactory.loadGraphFromFile(new File("src/test/resources/Graph3.txt"));
        var Graph4 = GraphFactory.loadGraphFromFile(new File("src/test/resources/Graph4.txt"));
        var Graph5 = GraphFactory.loadGraphFromFile(new File("src/test/resources/Graph5.txt"));
        var Graph6 = GraphFactory.loadGraphFromFile(new File("src/test/resources/Graph6.txt"));
        var Graph7 = GraphFactory.loadGraphFromFile(new File("src/test/resources/Graph7.txt"));

        assertThat(CalculateEdgeConnectivity.execute(Graph1) == 1).isTrue();
        assertThat(CalculateEdgeConnectivity.execute(Graph2) == 2).isTrue();
        assertThat(CalculateEdgeConnectivity.execute(Graph3) == 3).isTrue();
        assertThat(CalculateEdgeConnectivity.execute(Graph4) == 2).isTrue();
        assertThat(CalculateEdgeConnectivity.execute(Graph5) == 3).isTrue();
        assertThat(CalculateEdgeConnectivity.execute(Graph6) == 4).isTrue();
        assertThat(CalculateEdgeConnectivity.execute(Graph7) == 2).isTrue();

    }
}