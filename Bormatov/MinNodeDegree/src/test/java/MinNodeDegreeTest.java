import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;
import org.junit.Before;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class MinNodeDegreeTest {
    private MinNodeDegree min_node_degree;

    @Before
    public void setUp() {
        min_node_degree = new MinNodeDegree();
    }

    @Test
    public void testOneNode() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/one_node.txt"));
        assertThat(min_node_degree.execute(graph)).isEqualTo(0);

    }

    @Test
    public void testThreeNodes() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/three_nodes.txt"));
        assertThat(min_node_degree.execute(graph)).isEqualTo(0);

    }
    @Test
    public void testK6() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/K6.txt"));
        assertThat(min_node_degree.execute(graph)).isEqualTo(5);

    }
    @Test
    public void testMulti() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/multi.txt"));
        assertThat(min_node_degree.execute(graph)).isEqualTo(7);

    }
    @Test
    public void testPeterson() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/peterson.txt"));
        assertThat(min_node_degree.execute(graph)).isEqualTo(3);

    }
    @Test
    public void testOriented() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/oriented.txt"));
        assertThat(min_node_degree.execute(graph)).isEqualTo(1);

    }

    @Test
    public void testEmpty() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/empty.txt"));
        assertThat(min_node_degree.execute(graph)).isEqualTo(0);
    }
}
