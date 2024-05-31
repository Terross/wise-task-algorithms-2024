import java.io.File;
import java.io.FileNotFoundException;
import com.mathsystem.api.graph.GraphFactory;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.Test;

public class PerfectMatchingTest {

    @Test
    public void activateSimpleTest() throws FileNotFoundException {
        PerfectMatching perfectMatching = new PerfectMatching();
        var graph_with_no_edges = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph_with_no_edges.txt"));
        var K4_graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/K4_graph.txt"));
        var graph_with_2_connectivity_components = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph_with_2_connectivity_components.txt"));
        var bipartite_graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/bipartite_graph.txt"));
        var graph_with_cycles = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph_with_cycles.txt"));

        assertThat(perfectMatching.execute(graph_with_no_edges)).isFalse();
        assertThat(perfectMatching.execute(K4_graph)).isTrue();
        assertThat(perfectMatching.execute(bipartite_graph)).isFalse();
        assertThat(perfectMatching.execute(graph_with_2_connectivity_components)).isTrue();
        assertThat(perfectMatching.execute(graph_with_cycles)).isTrue();
    }
}