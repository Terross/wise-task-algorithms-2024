import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AllEdgesInTriangleTest {
    @Test
    public void EmptyTest() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/EmptyGraph.txt"));
        AllEdgesInTriangle allEdgesInTriangle = new AllEdgesInTriangle();

        assertThat(allEdgesInTriangle.execute(graph)).isFalse();
    }

    @Test
    public void BridgeTest() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/Bridge.txt"));
        AllEdgesInTriangle allEdgesInTriangle = new AllEdgesInTriangle();

        assertThat(allEdgesInTriangle.execute(graph)).isFalse();
    }

    @Test
    public void DirectedGraphTest() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/DirectedGraph.txt"));
        AllEdgesInTriangle allEdgesInTriangle = new AllEdgesInTriangle();

        assertThat(allEdgesInTriangle.execute(graph)).isTrue();
    }

    @Test
    public void K33_Test() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/K33.txt"));
        AllEdgesInTriangle allEdgesInTriangle = new AllEdgesInTriangle();

        assertThat(allEdgesInTriangle.execute(graph)).isFalse();
    }

    @Test
    public void SimpleNotTriangleTest() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/SimpleNotTriangle.txt"));
        AllEdgesInTriangle allEdgesInTriangle = new AllEdgesInTriangle();

        assertThat(allEdgesInTriangle.execute(graph)).isFalse();
    }

    @Test
    public void SimpleTriangleTest() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/SimpleTriangle.txt"));
        AllEdgesInTriangle allEdgesInTriangle = new AllEdgesInTriangle();

        assertThat(allEdgesInTriangle.execute(graph)).isTrue();
    }

    @Test
    public void TrianglesTest() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/Triangles.txt"));
        AllEdgesInTriangle allEdgesInTriangle = new AllEdgesInTriangle();

        assertThat(allEdgesInTriangle.execute(graph)).isTrue();
    }

    @Test
    public void TrianglesWithSquaresTest() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/TrianglesWithSquares.txt"));
        AllEdgesInTriangle allEdgesInTriangle = new AllEdgesInTriangle();

        assertThat(allEdgesInTriangle.execute(graph)).isFalse();
    }

    @Test
    public void TwoConnectivityComponentsFalseTest() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/TwoConnectivityComponentsFalse.txt"));
        AllEdgesInTriangle allEdgesInTriangle = new AllEdgesInTriangle();

        assertThat(allEdgesInTriangle.execute(graph)).isFalse();
    }

    @Test
    public void TwoConnectivityComponentsTrueTest() throws FileNotFoundException {
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/TwoConnectivityComponentsTrue.txt"));
        AllEdgesInTriangle allEdgesInTriangle = new AllEdgesInTriangle();

        assertThat(allEdgesInTriangle.execute(graph)).isTrue();
    }
}

