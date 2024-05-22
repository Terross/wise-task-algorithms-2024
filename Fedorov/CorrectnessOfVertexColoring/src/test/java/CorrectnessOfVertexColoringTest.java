import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class CorrectnessOfVertexColoringTest {

    @Test
    public void activateNoEdgeGraphTest() throws FileNotFoundException {
        CorrectnessOfVertexColoring correctnessOfVertexColoring = new CorrectnessOfVertexColoring();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/noEdgesGraph.txt"));

        assertThat(correctnessOfVertexColoring.execute(trueGraph)).isTrue();
    }

    @Test
    public void activateTrueCompleteGraphTest() throws FileNotFoundException {
        CorrectnessOfVertexColoring correctnessOfVertexColoring = new CorrectnessOfVertexColoring();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/trueCompleteGraph.txt"));

        assertThat(correctnessOfVertexColoring.execute(trueGraph)).isTrue();
    }

    @Test
    public void activateFalseCompleteGraphTest() throws FileNotFoundException {
        CorrectnessOfVertexColoring correctnessOfVertexColoring = new CorrectnessOfVertexColoring();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/falseCompleteGraph.txt"));

        assertThat(correctnessOfVertexColoring.execute(trueGraph)).isFalse();
    }

    @Test
    public void activateTrueTwoComponentsGraphTest() throws FileNotFoundException {
        CorrectnessOfVertexColoring correctnessOfVertexColoring = new CorrectnessOfVertexColoring();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/trueTwoComponentsGraph.txt"));

        assertThat(correctnessOfVertexColoring.execute(trueGraph)).isTrue();
    }

    @Test
    public void activateTrueRandomGraphTest() throws FileNotFoundException {
        CorrectnessOfVertexColoring correctnessOfVertexColoring = new CorrectnessOfVertexColoring();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/trueRandomGraph.txt"));

        assertThat(correctnessOfVertexColoring.execute(trueGraph)).isTrue();
    }

    @Test
    public void activateFalseRandomGraphTest() throws FileNotFoundException {
        CorrectnessOfVertexColoring correctnessOfVertexColoring = new CorrectnessOfVertexColoring();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/falseRandomGraph.txt"));

        assertThat(correctnessOfVertexColoring.execute(trueGraph)).isFalse();
    }
}

