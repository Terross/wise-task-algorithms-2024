import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class IsTwoCriticalTest {

    @Test
    public void trueGraph() throws FileNotFoundException {
        NodesEqEdges nodesEqEdges = new NodesEqEdges();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph_k2.txt"));
        assertThat(nodesEqEdges.execute(trueGraph)).isTrue();
    }
    @Test
    public void unconnectedGraph() throws FileNotFoundException {
        NodesEqEdges nodesEqEdges = new NodesEqEdges();
        var falseGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/unconnectedgraph.txt"));
        assertThat(nodesEqEdges.execute(falseGraph)).isFalse();
    }
    @Test
    public void starGraph() throws FileNotFoundException {
        NodesEqEdges nodesEqEdges = new NodesEqEdges();
        var falseGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/starcube.txt"));
        assertThat(nodesEqEdges.execute(falseGraph)).isFalse();
    }
    @Test
    public void emptyGraph() throws FileNotFoundException {
        NodesEqEdges nodesEqEdges = new NodesEqEdges();
        var falseGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/emptygraph.txt"));
        assertThat(nodesEqEdges.execute(falseGraph)).isFalse();
    }
    @Test
    public void twoCompsGraph() throws FileNotFoundException {
        NodesEqEdges nodesEqEdges = new NodesEqEdges();
        var falseGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/2comps1cube.txt"));
        assertThat(nodesEqEdges.execute(falseGraph)).isFalse();
    }

}