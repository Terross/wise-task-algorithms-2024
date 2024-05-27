import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class AlmostPerfectMatchTest {

    @Test
    public void activateSimpleTest() throws FileNotFoundException {
        AlmostPerfectMatch nodesEqEdges = new AlmostPerfectMatch();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/trueGraph.txt"));
        var falseGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/falseGraph.txt"));
        var notBipartiteGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/notBipartiteGraph.txt"));
        var trueBigGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/trueBigGraph.txt"));
        var falseBigGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/falseBigGraph.txt"));
        var oneVerticeGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/oneVerticeGraph.txt"));
        var twoVerticesGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/twoVerticesGraph.txt"));

        assertThat(nodesEqEdges.execute(trueGraph)).isTrue();
        assertThat(nodesEqEdges.execute(falseGraph)).isFalse();
        assertThat(nodesEqEdges.execute(notBipartiteGraph)).isFalse();
        assertThat(nodesEqEdges.execute(trueBigGraph)).isTrue();
        assertThat(nodesEqEdges.execute(falseBigGraph)).isFalse();
        assertThat(nodesEqEdges.execute(oneVerticeGraph)).isTrue();
        assertThat(nodesEqEdges.execute(twoVerticesGraph)).isFalse();


    }
}
