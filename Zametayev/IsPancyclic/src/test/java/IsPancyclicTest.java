import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class IsPancyclicTest {

    @Test
    public void EmptyTest() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/EmptyTest.txt"));
        IsPancyclic isPancyclic = new IsPancyclic();

        assertThat(isPancyclic.execute(graph)).isFalse();
    }

    @Test
    public void TwoVerticesOneEdgeTest() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/TwoVerticesOneEdgeTest.txt"));
        IsPancyclic isPancyclic = new IsPancyclic();

        assertThat(isPancyclic.execute(graph)).isFalse();
    }

    @Test
    public void TriangleTest() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/TriangleTest.txt"));
        IsPancyclic isPancyclic = new IsPancyclic();

        assertThat(isPancyclic.execute(graph)).isTrue();
    }

    @Test
    public void ComplexNonPancyclicTest() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/ComplexNonPancyclicTest.txt"));
        IsPancyclic isPancyclic = new IsPancyclic();

        assertThat(isPancyclic.execute(graph)).isFalse();
    }

    @Test
    public void ComplexPancyclicTest() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/ComplexPancyclicTest.txt"));
        IsPancyclic isPancyclic = new IsPancyclic();

        assertThat(isPancyclic.execute(graph)).isTrue();
    }

    @Test
    public void MultComponentsTest() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/MultComponentsTest.txt"));
        IsPancyclic isPancyclic = new IsPancyclic();

        assertThat(isPancyclic.execute(graph)).isFalse();
    }
}
