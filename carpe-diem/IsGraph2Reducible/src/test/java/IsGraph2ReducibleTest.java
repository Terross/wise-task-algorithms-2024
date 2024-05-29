import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class IsGraph2ReducibleTest {

    @Test
    public void emptyGraph() throws FileNotFoundException {
        IsGraph2Reducible isGraph2Reducible = new IsGraph2Reducible();
        var emptyGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/emptyGraph.txt"));
        assertThat(isGraph2Reducible.execute(emptyGraph)).isTrue();
    }

    @Test
    public void oneVertexGraph() throws FileNotFoundException {
        IsGraph2Reducible isGraph2Reducible = new IsGraph2Reducible();
        var oneVertexGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/oneVertexGraph.txt"));
        assertThat(isGraph2Reducible.execute(oneVertexGraph)).isTrue();
    }

    @Test
    public void twoVertexGraph() throws FileNotFoundException {
        IsGraph2Reducible isGraph2Reducible = new IsGraph2Reducible();
        var twoVertexGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/twoVertexGraph.txt"));
        assertThat(isGraph2Reducible.execute(twoVertexGraph)).isTrue();
    }

    @Test
    public void unconnectedGraph() throws FileNotFoundException {
        IsGraph2Reducible isGraph2Reducible = new IsGraph2Reducible();
        var unconnectedGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/unconnectedGraph.txt"));
        assertThat(isGraph2Reducible.execute(unconnectedGraph)).isTrue();
    }

    @Test
    public void k6Graph() throws FileNotFoundException {
        IsGraph2Reducible isGraph2Reducible = new IsGraph2Reducible();
        var k6Graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/k6Graph.txt"));
        assertThat(isGraph2Reducible.execute(k6Graph)).isFalse();
    }

    @Test
    public void cycleGraph() throws FileNotFoundException {
        IsGraph2Reducible isGraph2Reducible = new IsGraph2Reducible();
        var cycleGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/cycleGraph.txt"));
        assertThat(isGraph2Reducible.execute(cycleGraph)).isFalse();
    }

    @Test
    public void noEdgesGraph() throws FileNotFoundException {
        IsGraph2Reducible isGraph2Reducible = new IsGraph2Reducible();
        var noEdgesGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/noEdgesGraph.txt"));
        assertThat(isGraph2Reducible.execute(noEdgesGraph)).isTrue();
    }

    @Test
    public void chainGraph() throws FileNotFoundException {
        IsGraph2Reducible isGraph2Reducible = new IsGraph2Reducible();
        var chainGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/chainGraph.txt"));
        assertThat(isGraph2Reducible.execute(chainGraph)).isTrue();
    }

    @Test
    public void minDegreeGreater2Graph() throws FileNotFoundException {
        IsGraph2Reducible isGraph2Reducible = new IsGraph2Reducible();
        var minDegreeGreater2Graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/minDegreeGreater2Graph.txt"));
        assertThat(isGraph2Reducible.execute(minDegreeGreater2Graph)).isFalse();
    }
}