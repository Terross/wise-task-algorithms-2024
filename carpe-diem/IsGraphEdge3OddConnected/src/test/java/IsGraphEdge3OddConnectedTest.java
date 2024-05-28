import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class IsGraphEdge3OddConnectedTest {

    @Test
    public void emptyGraph() throws FileNotFoundException {
        IsGraphEdge3OddConnected isGraphEdge3OddConnected = new IsGraphEdge3OddConnected();
        var emptyGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/emptyGraph.txt"));
        assertThat(isGraphEdge3OddConnected.execute(emptyGraph)).isFalse();
    }

    @Test
    public void noEdgesGraph() throws FileNotFoundException {
        IsGraphEdge3OddConnected isGraphEdge3OddConnected = new IsGraphEdge3OddConnected();
        var noEdgesGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/noEdgesGraph.txt"));
        assertThat(isGraphEdge3OddConnected.execute(noEdgesGraph)).isFalse();
    }

    @Test
    public void oddCountVertexGraph() throws FileNotFoundException {
        IsGraphEdge3OddConnected isGraphEdge3OddConnected = new IsGraphEdge3OddConnected();
        var oddCountVertexGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/oddCountVertexGraph.txt"));
        assertThat(isGraphEdge3OddConnected.execute(oddCountVertexGraph)).isFalse();
    }

    @Test
    public void falseGraph() throws FileNotFoundException {
        IsGraphEdge3OddConnected isGraphEdge3OddConnected = new IsGraphEdge3OddConnected();
        var falseGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/falseGraph.txt"));
        assertThat(isGraphEdge3OddConnected.execute(falseGraph)).isFalse();
    }

    @Test
    public void k4Graph() throws FileNotFoundException {
        IsGraphEdge3OddConnected isGraphEdge3OddConnected = new IsGraphEdge3OddConnected();
        var k4Graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/k4Graph.txt"));
        assertThat(isGraphEdge3OddConnected.execute(k4Graph)).isTrue();
    }

    @Test
    public void twoVertexGraph() throws FileNotFoundException {
        IsGraphEdge3OddConnected isGraphEdge3OddConnected = new IsGraphEdge3OddConnected();
        var twoVertexGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/2VertexGraph.txt"));
        assertThat(isGraphEdge3OddConnected.execute(twoVertexGraph)).isFalse();
    }

    @Test
    public void threeConnectedGraph() throws FileNotFoundException {
        IsGraphEdge3OddConnected isGraphEdge3OddConnected = new IsGraphEdge3OddConnected();
        var threeConnectedGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/3ConnectedGraph.txt"));
        assertThat(isGraphEdge3OddConnected.execute(threeConnectedGraph)).isTrue();
    }

}