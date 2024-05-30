import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FourCriticalGraphTest {
    @Test
    public void activateSimpleTest() throws FileNotFoundException {
        FourCriticalGraph isFourCriticalGraph = new FourCriticalGraph();
        var graph = GraphFactory.loadGraphFromFile(new File("C:\\Users\\Наталья\\IdeaProjects\\wise-task-algorithms-2024-main\\FourCriticalGraph\\src\\test\\resources\\simple_test.txt"));
        assertThat(isFourCriticalGraph.execute(graph)).isTrue();
    }

    @Test
    public void emptyTest() throws FileNotFoundException {
        FourCriticalGraph isFourCriticalGraph = new FourCriticalGraph();
        var graph = GraphFactory.loadGraphFromFile(new File("C:\\Users\\Наталья\\IdeaProjects\\wise-task-algorithms-2024-main\\FourCriticalGraph\\src\\test\\resources\\empty_test.txt"));
        assertThat(isFourCriticalGraph.execute(graph)).isFalse();
    }

    @Test
    public void randomNegativeTest() throws FileNotFoundException {
        FourCriticalGraph isFourCriticalGraph = new FourCriticalGraph();
        var graph = GraphFactory.loadGraphFromFile(new File("C:\\Users\\Наталья\\IdeaProjects\\wise-task-algorithms-2024-main\\FourCriticalGraph\\src\\test\\resources\\random_negative_test.txt"));
        assertThat(isFourCriticalGraph.execute(graph)).isFalse();
    }

    @Test
    public void disconnectedGraphTest() throws FileNotFoundException {
        FourCriticalGraph isFourCriticalGraph = new FourCriticalGraph();
        var graph = GraphFactory.loadGraphFromFile(new File("C:\\Users\\Наталья\\IdeaProjects\\wise-task-algorithms-2024-main\\FourCriticalGraph\\src\\test\\resources\\disconnected_graph_test.txt"));
        assertThat(isFourCriticalGraph.execute(graph)).isFalse();
    }

    @Test
    public void notEdgeConnectedTest() throws FileNotFoundException {
        FourCriticalGraph isFourCriticalGraph = new FourCriticalGraph();
        var graph = GraphFactory.loadGraphFromFile(new File("C:\\Users\\Наталья\\IdeaProjects\\wise-task-algorithms-2024-main\\FourCriticalGraph\\src\\test\\resources\\not_edge_connected_test.txt"));
        assertThat(isFourCriticalGraph.execute(graph)).isFalse();
    }

    @Test
    public void checkTest() throws FileNotFoundException {
        FourCriticalGraph isFourCriticalGraph = new FourCriticalGraph();
        var graph = GraphFactory.loadGraphFromFile(new File("C:\\Users\\Наталья\\IdeaProjects\\wise-task-algorithms-2024-main\\FourCriticalGraph\\src\\test\\resources\\graph1.txt"));
        assertThat(isFourCriticalGraph.execute(graph)).isFalse();
    }
}
