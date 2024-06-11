import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class IsGraph3ReducibleTest{
    @Test
    public void EmptyTest() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph0.txt"));
        IsGraph3Reducible IsGraph3Reducible = new IsGraph3Reducible();

        assertThat(IsGraph3Reducible.execute(graph)).isTrue();
    }

    @Test
    public void OneVertexTest() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph1.txt"));
        IsGraph3Reducible IsGraph3Reducible = new IsGraph3Reducible();

        assertThat(IsGraph3Reducible.execute(graph)).isTrue();
    }

    @Test
    public void OneNotBridgeTest() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph1.txt"));
        IsGraph3Reducible IsGraph3Reducible = new IsGraph3Reducible();

        assertThat(IsGraph3Reducible.execute(graph)).isTrue();
    }

    @Test
    public void OneTriangle() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph2.txt"));
        IsGraph3Reducible IsGraph3Reducible = new IsGraph3Reducible();

        assertThat(IsGraph3Reducible.execute(graph)).isTrue();
    }

    @Test
    public void TwoTriangleWithBridge() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph3.txt"));
        IsGraph3Reducible IsGraph3Reducible = new IsGraph3Reducible();

        assertThat(IsGraph3Reducible.execute(graph)).isTrue();
    }

    @Test
    public void TwoTriangle() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph4.txt"));
        IsGraph3Reducible IsGraph3Reducible = new IsGraph3Reducible();

        assertThat(IsGraph3Reducible.execute(graph)).isTrue();
    }

    @Test
    public void k4WithoutEdge() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph5.txt"));
        IsGraph3Reducible IsGraph3Reducible = new IsGraph3Reducible();

        assertThat(IsGraph3Reducible.execute(graph)).isTrue();
    }
    @Test
    public void k4() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph6.txt"));
        IsGraph3Reducible IsGraph3Reducible = new IsGraph3Reducible();

        assertThat(IsGraph3Reducible.execute(graph)).isFalse();
    }

}
