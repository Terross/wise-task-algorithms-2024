import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class EdgeConnectivityTest {

    @Test
    public void K3_3() throws FileNotFoundException {
        var isPlanarWithoutSomeEdge = new EdgeConnectivity();
        var K3_3 = GraphFactory.loadGraphFromFile(new File("src/test/resources/trueGraph.txt"));
        var falseGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/falseGraph.txt"));

        assertThat(isPlanarWithoutSomeEdge.execute(K3_3)).isTrue();
        assertThat(isPlanarWithoutSomeEdge.execute(falseGraph)).isFalse();
    }

    @Test
    public void K5() throws FileNotFoundException {
        var isPlanarWithoutSomeEdge = new EdgeConnectivity();
        var K5 = GraphFactory.loadGraphFromFile(new File("src/test/resources/1.txt"));
        assertThat(isPlanarWithoutSomeEdge.execute(K5)).isTrue();
    }

    @Test
    public void graph_without_intersection() throws FileNotFoundException {
        var isPlanarWithoutSomeEdge = new EdgeConnectivity();
        var graph_without_intersection = GraphFactory.loadGraphFromFile(new File("src/test/resources/2.txt"));
        assertThat(isPlanarWithoutSomeEdge.execute(graph_without_intersection)).isFalse();
    }
    @Test
    public void graph_with_1_intersection() throws FileNotFoundException {
        var isPlanarWithoutSomeEdge = new EdgeConnectivity();
        var graph_with_1_intersection = GraphFactory.loadGraphFromFile(new File("src/test/resources/3.txt"));

        assertThat(isPlanarWithoutSomeEdge.execute(graph_with_1_intersection)).isFalse();
    }
    @Test
    public void graph_with_many_intersections() throws FileNotFoundException {
        var isPlanarWithoutSomeEdge = new EdgeConnectivity();
        var graph_with_many_intersections = GraphFactory.loadGraphFromFile(new File("src/test/resources/4.txt"));

        assertThat(isPlanarWithoutSomeEdge.execute(graph_with_many_intersections)).isFalse();
    }


}
