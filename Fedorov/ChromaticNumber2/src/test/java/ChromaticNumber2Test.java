import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class ChromaticNumber2Test {
    @Test
    public void testSmallGraphReturnsTrue() throws FileNotFoundException {
        ChromaticNumber2 chromaticNumber2 = new ChromaticNumber2();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/smallCorrectGraph.txt"));
        assertThat(chromaticNumber2.execute(graph)).isTrue();
    }

    @Test
    public void testRegularGraphReturnsTrue() throws FileNotFoundException {
        ChromaticNumber2 chromaticNumber2 = new ChromaticNumber2();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/regularCorrectGraph.txt"));
        assertThat(chromaticNumber2.execute(graph)).isTrue();
    }

    @Test
    public void testManyVerticesOneEdgeInGraphReturnsTrue() throws FileNotFoundException {
        ChromaticNumber2 chromaticNumber2 = new ChromaticNumber2();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/manyVerticesOneEdgeGraph.txt"));
        assertThat(chromaticNumber2.execute(graph)).isTrue();
    }

    @Test
    public void testDisconnectedGraphReturnsTrue() throws FileNotFoundException {
        ChromaticNumber2 chromaticNumber2 = new ChromaticNumber2();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/disconnectedCorrectGraph.txt"));
        assertThat(chromaticNumber2.execute(graph)).isTrue();
    }

    @Test
    public void testColoredGraphReturnsTrue() throws FileNotFoundException {
        ChromaticNumber2 chromaticNumber2 = new ChromaticNumber2();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/coloredCorrectGraph.txt"));
        assertThat(chromaticNumber2.execute(graph)).isTrue();
    }

    @Test
    public void testSmallGraphReturnsFalse() throws FileNotFoundException {
        ChromaticNumber2 chromaticNumber2 = new ChromaticNumber2();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/smallIncorrectGraph.txt"));
        assertThat(chromaticNumber2.execute(graph)).isFalse();
    }

    @Test
    public void testRegularGraphReturnsFalse() throws FileNotFoundException {
        ChromaticNumber2 chromaticNumber2 = new ChromaticNumber2();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/regularIncorrectGraph.txt"));
        assertThat(chromaticNumber2.execute(graph)).isFalse();
    }

    @Test
    public void testOneVertexInGraphReturnsFalse() throws FileNotFoundException {
        ChromaticNumber2 chromaticNumber2 = new ChromaticNumber2();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/oneVertexGraph.txt"));
        assertThat(chromaticNumber2.execute(graph)).isFalse();
    }

    @Test
    public void testNoEdgesInGraphReturnsFalse() throws FileNotFoundException {
        ChromaticNumber2 chromaticNumber2 = new ChromaticNumber2();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/noEdgesGraph.txt"));
        assertThat(chromaticNumber2.execute(graph)).isFalse();
    }

    @Test
    public void testDisconnectedGraphReturnsFalse() throws FileNotFoundException {
        ChromaticNumber2 chromaticNumber2 = new ChromaticNumber2();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/disconnectedIncorrectGraph.txt"));
        assertThat(chromaticNumber2.execute(graph)).isFalse();
    }
}