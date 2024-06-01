import java.io.File;
import java.io.FileNotFoundException;
import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FourConnectedGraphTest{
    @Test
    public void six_test_node() throws FileNotFoundException{
        FourConnectedGraph graph_test_1 = new FourConnectedGraph();
        var test_graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph_test_1.txt"));

        assertThat(graph_test_1.execute(test_graph)).isTrue();
    }

    @Test
    public void four_test_node() throws FileNotFoundException{
        FourConnectedGraph graph_test_1 = new FourConnectedGraph();
        var test_graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph_test_2.txt"));

        assertThat(graph_test_1.execute(test_graph)).isFalse();
    }

    @Test
    public void five_test_node_false() throws FileNotFoundException{
        FourConnectedGraph graph_test_1 = new FourConnectedGraph();
        var test_graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph_test_3.txt"));

        assertThat(graph_test_1.execute(test_graph)).isFalse();
    }
    @Test
    public void five_test_node_true() throws FileNotFoundException{
        FourConnectedGraph graph_test_1 = new FourConnectedGraph();
        var test_graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph_test_4.txt"));

        assertThat(graph_test_1.execute(test_graph)).isTrue();
    }

    @Test
    public void seven_test_node_true() throws FileNotFoundException{
        FourConnectedGraph graph_test_1 = new FourConnectedGraph();
        var test_graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph_test_5.txt"));

        assertThat(graph_test_1.execute(test_graph)).isTrue();
    }
    @Test
    public void test_node_true_21() throws FileNotFoundException{
        FourConnectedGraph graph_test_1 = new FourConnectedGraph();
        var test_graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph_test_6.txt"));

        assertThat(graph_test_1.execute(test_graph)).isFalse();
    }
}
