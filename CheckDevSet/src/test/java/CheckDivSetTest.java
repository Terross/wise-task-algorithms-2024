import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class CheckDivSetTest {

    @Test
    public void simpleTrueTest() throws FileNotFoundException {
        CheckDivSet nodesEqEdges = new CheckDivSet();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/trueTest.txt"));
        assertThat(nodesEqEdges.execute(graph)).isTrue();
    }

    @Test
    public void notRedTest() throws FileNotFoundException {
        CheckDivSet nodesEqEdges = new CheckDivSet();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/notRedTest.txt"));
        assertThat(nodesEqEdges.execute(graph)).isFalse();
    }

    @Test
    public void allRedTest() throws FileNotFoundException {
        CheckDivSet nodesEqEdges = new CheckDivSet();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/allRedTest.txt"));
        assertThat(nodesEqEdges.execute(graph)).isFalse();
    }

    @Test
    public void randomTest() throws FileNotFoundException {
        CheckDivSet nodesEqEdges = new CheckDivSet();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/randomTest.txt"));
        assertThat(nodesEqEdges.execute(graph)).isTrue();
    }

    @Test
    public void oneRedRandomTest() throws FileNotFoundException {
        CheckDivSet nodesEqEdges = new CheckDivSet();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/oneRedRandomTest.txt"));
        assertThat(nodesEqEdges.execute(graph)).isTrue();
    }

    @Test
    public void anyListTest() throws FileNotFoundException {
        CheckDivSet nodesEqEdges = new CheckDivSet();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/anyListTest.txt"));
        assertThat(nodesEqEdges.execute(graph)).isFalse();
    }

    @Test
    public void CircleTwoRedTest() throws FileNotFoundException {
        CheckDivSet nodesEqEdges = new CheckDivSet();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/CircleTwoRedTest.txt"));
        assertThat(nodesEqEdges.execute(graph)).isFalse();
    }

    @Test
    public void emptyTest() throws FileNotFoundException {
        CheckDivSet nodesEqEdges = new CheckDivSet();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/emptyTest.txt"));
        assertThat(nodesEqEdges.execute(graph)).isFalse();
    }

    @Test
    public void graphK5GrayTest() throws FileNotFoundException {
        CheckDivSet nodesEqEdges = new CheckDivSet();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graphK5GrayTest.txt"));
        assertThat(nodesEqEdges.execute(graph)).isFalse();
    }

    @Test
    public void K55Test() throws FileNotFoundException {
        CheckDivSet nodesEqEdges = new CheckDivSet();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/K55Test.txt"));
        assertThat(nodesEqEdges.execute(graph)).isTrue();
    }

    @Test
    public void neuronsMoreTest() throws FileNotFoundException {
        CheckDivSet nodesEqEdges = new CheckDivSet();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/neuronsMoreTest.txt"));
        assertThat(nodesEqEdges.execute(graph)).isTrue();
    }

    @Test
    public void neuronsTest() throws FileNotFoundException {
        CheckDivSet nodesEqEdges = new CheckDivSet();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/neuronsTest.txt"));
        assertThat(nodesEqEdges.execute(graph)).isTrue();
    }

    @Test
    public void pointsGreayTest() throws FileNotFoundException {
        CheckDivSet nodesEqEdges = new CheckDivSet();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/pointsGreayTest.txt"));
        assertThat(nodesEqEdges.execute(graph)).isFalse();
    }

    @Test
    public void pointsTest() throws FileNotFoundException {
        CheckDivSet nodesEqEdges = new CheckDivSet();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/pointsTest.txt"));
        assertThat(nodesEqEdges.execute(graph)).isFalse();
    }

    @Test
    public void TreeTest() throws FileNotFoundException {
        CheckDivSet nodesEqEdges = new CheckDivSet();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/TreeTest.txt"));
        assertThat(nodesEqEdges.execute(graph)).isTrue();
    }

    @Test
    public void TwoK5Test() throws FileNotFoundException {
        CheckDivSet nodesEqEdges = new CheckDivSet();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/TwoK5Test.txt"));
        assertThat(nodesEqEdges.execute(graph)).isTrue();
    }

    @Test
    public void UNREALTEST() throws FileNotFoundException {
        CheckDivSet nodesEqEdges = new CheckDivSet();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/UNREALTEST.txt"));
        assertThat(nodesEqEdges.execute(graph)).isTrue();
    }
}
