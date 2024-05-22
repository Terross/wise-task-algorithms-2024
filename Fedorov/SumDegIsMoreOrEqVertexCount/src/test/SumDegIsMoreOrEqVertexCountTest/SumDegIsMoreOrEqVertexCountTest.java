import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class SumDegIsMoreOrEqVertexCountTest {

    @Test
    public void trueGraph() throws FileNotFoundException {
        SumDegIsMoreOrEqVertexCount SumDegIsMoreOrEqVertexCount = new SumDegIsMoreOrEqVertexCount();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph_k2.txt"));
        assertThat(SumDegIsMoreOrEqVertexCount.execute(trueGraph)).isFalse();
    }
    @Test
    public void unconnectedGraph() throws FileNotFoundException {
        SumDegIsMoreOrEqVertexCount SumDegIsMoreOrEqVertexCount = new SumDegIsMoreOrEqVertexCount();
        var falseGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/unconnectedgraph.txt"));
        assertThat(SumDegIsMoreOrEqVertexCount.execute(falseGraph)).isFalse();
    }
    @Test
    public void starGraph() throws FileNotFoundException {
        SumDegIsMoreOrEqVertexCount SumDegIsMoreOrEqVertexCount = new SumDegIsMoreOrEqVertexCount();
        var falseGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/starcube.txt"));
        assertThat(SumDegIsMoreOrEqVertexCount.execute(falseGraph)).isTrue();
    }
    @Test
    public void emptyGraph() throws FileNotFoundException {
        SumDegIsMoreOrEqVertexCount SumDegIsMoreOrEqVertexCount = new SumDegIsMoreOrEqVertexCount();
        var falseGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/emptygraph.txt"));
        assertThat(SumDegIsMoreOrEqVertexCount.execute(falseGraph)).isFalse();
    }
    @Test
    public void twoCompsGraph() throws FileNotFoundException {
        SumDegIsMoreOrEqVertexCount SumDegIsMoreOrEqVertexCount = new SumDegIsMoreOrEqVertexCount();
        var falseGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/2comps1cube.txt"));
        assertThat(SumDegIsMoreOrEqVertexCount.execute(falseGraph)).isFalse();
    }

}