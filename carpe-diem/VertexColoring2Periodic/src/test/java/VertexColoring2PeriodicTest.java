import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;
import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class VertexColoring2PeriodicTest {

    @Test
    public void simpleTest() throws FileNotFoundException {
        VertexColoring2Periodic vertexColoring = new VertexColoring2Periodic();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/SimpleTest1.txt"));
        boolean result = vertexColoring.execute(testGraph);
        assertThat(result).isTrue(); 
    }

    @Test
    public void oneColorTest() throws FileNotFoundException {
        VertexColoring2Periodic vertexColoring = new VertexColoring2Periodic();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/oneColor.txt"));
        boolean result = vertexColoring.execute(testGraph);
        assertThat(result).isTrue();
    }

    @Test
    public void twoPeriodicTest() throws FileNotFoundException {
        VertexColoring2Periodic vertexColoring = new VertexColoring2Periodic();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/TwoPeriodic.txt"));
        boolean result = vertexColoring.execute(testGraph);
        assertThat(result).isTrue();
    }

    @Test
    public void manyColorWheelTest() throws FileNotFoundException {
        VertexColoring2Periodic vertexColoring = new VertexColoring2Periodic();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/manyColorsWheel.txt"));
        boolean result = vertexColoring.execute(testGraph);
        assertThat(result).isFalse();
    }

    @Test
    public void noColorTest() throws FileNotFoundException {
        VertexColoring2Periodic vertexColoring = new VertexColoring2Periodic();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/noColor.txt"));
        boolean result = vertexColoring.execute(testGraph);
        assertThat(result).isTrue();
    }
    @Test
    public void fullGraphTrueTest() throws FileNotFoundException {
        VertexColoring2Periodic vertexColoring = new VertexColoring2Periodic();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/fullGraphTrue.txt"));
        boolean result = vertexColoring.execute(testGraph);
        assertThat(result).isTrue();
    }
    @Test
    public void fullGraphFalseTest() throws FileNotFoundException {
        VertexColoring2Periodic vertexColoring = new VertexColoring2Periodic();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/fullGraphFalse.txt"));
        boolean result = vertexColoring.execute(testGraph);
        assertThat(result).isFalse();
    }
}

