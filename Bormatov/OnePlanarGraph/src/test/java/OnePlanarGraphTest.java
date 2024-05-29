import java.io.File;
import java.io.FileNotFoundException;
import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;
import ru.leti.OnePlanarGraph;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class OnePlanarGraphTest{
    private OnePlanarGraph graph_test_1;

    @Test
    public void first_false_test() throws FileNotFoundException{
        OnePlanarGraph graph_test_1 = new OnePlanarGraph();
        var test_graph = GraphFactory.loadGraphFromFile(new File("src/main/resources/first_false_test.txt"));

        assertThat(graph_test_1.execute(test_graph)).isFalse();
    }

    @Test
    public void second_false_test() throws FileNotFoundException{
        OnePlanarGraph graph_test_1 = new OnePlanarGraph();
        var test_graph = GraphFactory.loadGraphFromFile(new File("src/main/resources/second_false_test.txt"));

        assertThat(graph_test_1.execute(test_graph)).isFalse();
    }

    @Test
    public void third_true_test() throws FileNotFoundException{
        OnePlanarGraph graph_test_1 = new OnePlanarGraph();
        var test_graph = GraphFactory.loadGraphFromFile(new File("src/main/resources/third_true_test.txt"));

        assertThat(graph_test_1.execute(test_graph)).isTrue();
    }
    @Test
    public void forth_true_test() throws FileNotFoundException{
        OnePlanarGraph graph_test_1 = new OnePlanarGraph();
        var test_graph = GraphFactory.loadGraphFromFile(new File("src/main/resources/forth_true_test.txt"));

        assertThat(graph_test_1.execute(test_graph)).isTrue();
    }

    @Test
    public void five_true_test() throws FileNotFoundException{
        OnePlanarGraph graph_test_1 = new OnePlanarGraph();
        var test_graph = GraphFactory.loadGraphFromFile(new File("src/main/resources/five_true_test.txt"));

        assertThat(graph_test_1.execute(test_graph)).isTrue();
    }
}
