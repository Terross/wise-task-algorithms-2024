import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class IsGraph3ConnectedAfterEdgeContractionTest  {

    @Test
    public void activateSimpleTest() throws FileNotFoundException {
        IsGraph3ConnectedAfterEdgeContraction isGraph3ConnectedAfterEdgeContraction = new IsGraph3ConnectedAfterEdgeContraction();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/trueGraph.txt"));
        var falseGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/falseGraph.txt"));

        assertThat(isGraph3ConnectedAfterEdgeContraction.execute(trueGraph)).isTrue();
        assertThat(isGraph3ConnectedAfterEdgeContraction.execute(falseGraph)).isFalse();
    }

    @Test
    public void oneVertexTest() throws FileNotFoundException {
        IsGraph3ConnectedAfterEdgeContraction isGraph3ConnectedAfterEdgeContraction = new IsGraph3ConnectedAfterEdgeContraction();
        var oneVertexGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/oneVertexGraph.txt"));
        assertThat(isGraph3ConnectedAfterEdgeContraction.execute(oneVertexGraph)).isFalse();
    }

    @Test
    public void twoVertexTest() throws FileNotFoundException {
        IsGraph3ConnectedAfterEdgeContraction isGraph3ConnectedAfterEdgeContraction = new IsGraph3ConnectedAfterEdgeContraction();
        var twoVertexGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/twoVertexGraph.txt"));
        assertThat(isGraph3ConnectedAfterEdgeContraction.execute(twoVertexGraph)).isFalse();
    }

    @Test
    public void noEdgesTest() throws FileNotFoundException {
        IsGraph3ConnectedAfterEdgeContraction isGraph3ConnectedAfterEdgeContraction = new IsGraph3ConnectedAfterEdgeContraction();
        var noEdgesGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/noEdgesGraph.txt"));
        assertThat(isGraph3ConnectedAfterEdgeContraction.execute(noEdgesGraph)).isFalse();
    }

    @Test
    public void connectedGraph() throws FileNotFoundException {
        IsGraph3ConnectedAfterEdgeContraction isGraph3ConnectedAfterEdgeContraction = new IsGraph3ConnectedAfterEdgeContraction();
        var connectedGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/connectedGraph.txt"));
        assertThat(isGraph3ConnectedAfterEdgeContraction.execute(connectedGraph)).isFalse();
    }

    @Test
    public void threeConnectedGraph() throws FileNotFoundException {
        IsGraph3ConnectedAfterEdgeContraction isGraph3ConnectedAfterEdgeContraction = new IsGraph3ConnectedAfterEdgeContraction();
        var connectedGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/3connectedGraph.txt"));
        assertThat(isGraph3ConnectedAfterEdgeContraction.execute(connectedGraph)).isTrue();
    }

    @Test
    public void k6Graph() throws FileNotFoundException {
        IsGraph3ConnectedAfterEdgeContraction isGraph3ConnectedAfterEdgeContraction = new IsGraph3ConnectedAfterEdgeContraction();
        var ksixGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/k6Graph.txt"));
        assertThat(isGraph3ConnectedAfterEdgeContraction.execute(ksixGraph)).isTrue();
    }

    @Test
    public void emptyGraph() throws FileNotFoundException {
        IsGraph3ConnectedAfterEdgeContraction isGraph3ConnectedAfterEdgeContraction = new IsGraph3ConnectedAfterEdgeContraction();
        var emptyGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/emptyGraph.txt"));
        assertThat(isGraph3ConnectedAfterEdgeContraction.execute(emptyGraph)).isFalse();
    }

}
