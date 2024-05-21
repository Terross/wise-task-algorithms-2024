import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class NodesEqEdgesTest {

    @Test
    public void activateSimpleTest() throws FileNotFoundException {
        FindCube nodesEqEdges = new FindCube();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/trueGraph.txt"));
        var falseGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/falseGraph.txt"));

        assertThat(nodesEqEdges.execute(trueGraph)).isTrue();
        assertThat(nodesEqEdges.execute(falseGraph)).isFalse();
    }
}
