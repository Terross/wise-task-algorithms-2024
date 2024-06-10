import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class IsAcyclicTest {

    @Test
    public void EmptyTest() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/EmptyTest.txt"));
        IsAcyclic isAcyclic = new IsAcyclic();

        assertThat(isAcyclic.execute(graph)).isTrue();
    }

    @Test
    public void OneVertexTest() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/OneVertexTest.txt"));
        IsAcyclic isAcyclic = new IsAcyclic();

        assertThat(isAcyclic.execute(graph)).isTrue();
    }

    @Test
    public void TwoVerticesOneEdgeTest() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/TwoVerticesOneEdgeTest.txt"));
        IsAcyclic isAcyclic = new IsAcyclic();

        assertThat(isAcyclic.execute(graph)).isTrue();
    }

    @Test
    public void TwoVerticesTwoEdgesTest() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/TwoVerticesTwoEdgesTest.txt"));
        IsAcyclic isAcyclic = new IsAcyclic();

        assertThat(isAcyclic.execute(graph)).isFalse();
    }

    @Test
    public void CompleteFiveVerticesTest() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/CompleteFiveVerticesTest.txt"));
        IsAcyclic isAcyclic = new IsAcyclic();

        assertThat(isAcyclic.execute(graph)).isFalse();
    }

    @Test
    public void ComplexAcyclicTest() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/ComplexAcyclicTest.txt"));
        IsAcyclic isAcyclic = new IsAcyclic();

        assertThat(isAcyclic.execute(graph)).isTrue();
    }

    @Test
    public void ComplexAcyclicMultComponentsTest() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/ComplexAcyclicMultComponentsTest.txt"));
        IsAcyclic isAcyclic = new IsAcyclic();

        assertThat(isAcyclic.execute(graph)).isTrue();
    }

    @Test
    public void ComplexCyclicTest() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/ComplexCyclicTest.txt"));
        IsAcyclic isAcyclic = new IsAcyclic();

        assertThat(isAcyclic.execute(graph)).isFalse();
    }

    @Test
    public void ComplexCyclicMultComponentsTest() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/ComplexCyclicMultComponentsTest.txt"));
        IsAcyclic isAcyclic = new IsAcyclic();

        assertThat(isAcyclic.execute(graph)).isFalse();
    }
}
