import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class ThreeCriticalTest {
    @Test
    public void testCanigGraphReturnsTrue() throws FileNotFoundException {
        ThreeCritical chromaticNumber2 = new ThreeCritical();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/CanigGraph.txt"));
        assertThat(chromaticNumber2.execute(graph)).isTrue();
    }

    @Test
    public void testgraphReturnsTrue() throws FileNotFoundException {
        ThreeCritical chromaticNumber2 = new ThreeCritical();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph.txt"));
        assertThat(chromaticNumber2.execute(graph)).isTrue();
    }

    @Test
    public void testPadgeGraphReturnsTrue() throws FileNotFoundException {
        ThreeCritical chromaticNumber2 = new ThreeCritical();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/PadgeGraph.txt"));
        assertThat(chromaticNumber2.execute(graph)).isTrue();
    }

    @Test
    public void testDisconnectedGraphReturns() throws FileNotFoundException {
        ThreeCritical chromaticNumber2 = new ThreeCritical();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/DisconnectedCorrectGraph.txt"));
        assertThat(chromaticNumber2.execute(graph)).isTrue();
    }

    @Test
    public void testColoredGraphReturnsTrue() throws FileNotFoundException {
        ThreeCritical chromaticNumber2 = new ThreeCritical();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/ColoredGraph.txt"));
        assertThat(chromaticNumber2.execute(graph)).isTrue();
    }

    @Test
    public void testSmallGraphReturnsFalse() throws FileNotFoundException {
        ThreeCritical chromaticNumber2 = new ThreeCritical();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/smallGraph.txt"));
        assertThat(chromaticNumber2.execute(graph)).isFalse();
    }

    @Test
    public void testSmallGraph2ReturnsFalse() throws FileNotFoundException {
        ThreeCritical chromaticNumber2 = new ThreeCritical();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/smallGraph2.txt"));
        assertThat(chromaticNumber2.execute(graph)).isFalse();
    }

    @Test
    public void testOneVertexInGraphReturnsFalse() throws FileNotFoundException {
        ThreeCritical chromaticNumber2 = new ThreeCritical();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/oneVertexGraph.txt"));
        assertThat(chromaticNumber2.execute(graph)).isFalse();
    }

    @Test
    public void testregularGraphReturnsFalse() throws FileNotFoundException {
        ThreeCritical chromaticNumber2 = new ThreeCritical();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/regularGraph.txt"));
        assertThat(chromaticNumber2.execute(graph)).isFalse();
    }

    @Test
    public void testDisconnectedGraphReturnsFalse() throws FileNotFoundException {
        ThreeCritical chromaticNumber2 = new ThreeCritical();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/DisconnectedGraph.txt"));
        assertThat(chromaticNumber2.execute(graph)).isFalse();
    }
}