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
    public void DefTest() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/DefTest.txt"));
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

    @Test
    public void SimpleTest() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/SimpleTest.txt"));
        IsPancyclic isPancyclic = new IsPancyclic();

        assertThat(isPancyclic.execute(graph)).isTrue();
    }

    @Test
    public void K7Test() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/K7Test.txt"));
        IsPancyclic isPancyclic = new IsPancyclic();

        assertThat(isPancyclic.execute(graph)).isTrue();
    }
}
