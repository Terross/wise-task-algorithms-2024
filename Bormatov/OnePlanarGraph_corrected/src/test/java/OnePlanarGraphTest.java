import java.io.File;
import java.io.FileNotFoundException;
import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class OnePlanarGraphTest {
    private OnePlanarGraph graph_test_1;

    @Test
    public void first_false_test() throws FileNotFoundException {
        OnePlanarGraph graph_test_1 = new OnePlanarGraph();
        var test_graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/first_test.txt"));

        assertThat(graph_test_1.execute(test_graph)).isTrue();
    }

    @Test
    public void second_false_test() throws FileNotFoundException {
        OnePlanarGraph graph_test_1 = new OnePlanarGraph();
        var test_graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/second_test.txt"));

        assertThat(graph_test_1.execute(test_graph)).isTrue();
    }

    @Test
    public void third_true_test() throws FileNotFoundException {
        OnePlanarGraph graph_test_1 = new OnePlanarGraph();
        var test_graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/third_test.txt"));

        assertThat(graph_test_1.execute(test_graph)).isTrue();
    }

    @Test
    public void forth_true_test() throws FileNotFoundException {
        OnePlanarGraph graph_test_1 = new OnePlanarGraph();
        var test_graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/forth_test.txt"));

        assertThat(graph_test_1.execute(test_graph)).isTrue();
    }
}