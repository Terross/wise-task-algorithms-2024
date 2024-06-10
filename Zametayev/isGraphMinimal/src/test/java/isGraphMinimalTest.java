import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class isGraphMinimalTest {

    @Test
    public void EmptyTest() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/empty.txt"));
        isGraphMinimal isAcyclic = new isGraphMinimal();

        assertThat(isAcyclic.execute(graph)).isTrue();
    }

    @Test
    public void ManyBridgesTest() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph.txt"));
        isGraphMinimal isAcyclic = new isGraphMinimal();

        assertThat(isAcyclic.execute(graph)).isTrue();
    }

    @Test
    public void OneNotBridgeTest() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph1.txt"));
        isGraphMinimal isAcyclic = new isGraphMinimal();

        assertThat(isAcyclic.execute(graph)).isFalse();
    }

    @Test
    public void OnlyBridgesTest() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph2.txt"));
        isGraphMinimal isAcyclic = new isGraphMinimal();

        assertThat(isAcyclic.execute(graph)).isTrue();
    }

    @Test
    public void NoBridgesTest() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph3.txt"));
        isGraphMinimal isAcyclic = new isGraphMinimal();

        assertThat(isAcyclic.execute(graph)).isFalse();
    }

    @Test
    public void TwoVerticesTest() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph4.txt"));
        isGraphMinimal isAcyclic = new isGraphMinimal();

        assertThat(isAcyclic.execute(graph)).isTrue();
    }

    @Test
    public void OneVerticesTest() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph5.txt"));
        isGraphMinimal isAcyclic = new isGraphMinimal();

        assertThat(isAcyclic.execute(graph)).isTrue();
    }
}
