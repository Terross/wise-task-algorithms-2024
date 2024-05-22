import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class LargestCycleTest {

    @Test
    public void bigCircleTest() throws FileNotFoundException {
        LargestCycle nodesEqEdges = new LargestCycle();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/bigCircle.txt"));

        assertThat(nodesEqEdges.execute(trueGraph)).isEqualTo(13);
    }
    @Test
    public void bigTestTest() throws FileNotFoundException {
        LargestCycle nodesEqEdges = new LargestCycle();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/bigTest.txt"));

        assertThat(nodesEqEdges.execute(trueGraph)).isEqualTo(16);
    }
    @Test
    public void DnaPodymatTest() throws FileNotFoundException {
        LargestCycle nodesEqEdges = new LargestCycle();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/DnaPodymatTest.txt"));

        assertThat(nodesEqEdges.execute(trueGraph)).isEqualTo(4);
    }
    @Test
    public void DreverseTest() throws FileNotFoundException {
        LargestCycle nodesEqEdges = new LargestCycle();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/DreverseTest.txt"));

        assertThat(nodesEqEdges.execute(trueGraph)).isEqualTo(3);
    }
    @Test
    public void DsimpleTest() throws FileNotFoundException {
        LargestCycle nodesEqEdges = new LargestCycle();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/DsimpleTest.txt"));

        assertThat(nodesEqEdges.execute(trueGraph)).isEqualTo(5);
    }
    @Test
    public void DtwoCompTest() throws FileNotFoundException {
        LargestCycle nodesEqEdges = new LargestCycle();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/DtwoCompTest.txt"));

        assertThat(nodesEqEdges.execute(trueGraph)).isEqualTo(4);
    }
    @Test
    public void EmptyTest() throws FileNotFoundException {
        LargestCycle nodesEqEdges = new LargestCycle();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/emptyTest.txt"));

        assertThat(nodesEqEdges.execute(trueGraph)).isEqualTo(0);
    }
    @Test
    public void kovshTest() throws FileNotFoundException {
        LargestCycle nodesEqEdges = new LargestCycle();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/kovshTest.txt"));

        assertThat(nodesEqEdges.execute(trueGraph)).isEqualTo(5);
    }
    @Test
    public void noCircleTest() throws FileNotFoundException {
        LargestCycle nodesEqEdges = new LargestCycle();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/noCircleTest.txt"));

        assertThat(nodesEqEdges.execute(trueGraph)).isEqualTo(0);
    }
    @Test
    public void noEdgeTest() throws FileNotFoundException {
        LargestCycle nodesEqEdges = new LargestCycle();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/noEdgeTest.txt"));

        assertThat(nodesEqEdges.execute(trueGraph)).isEqualTo(0);
    }
    @Test
    public void oneVerTest() throws FileNotFoundException {
        LargestCycle nodesEqEdges = new LargestCycle();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/oneVerTest.txt"));

        assertThat(nodesEqEdges.execute(trueGraph)).isEqualTo(0);
    }
    @Test
    public void threeVerTest() throws FileNotFoundException {
        LargestCycle nodesEqEdges = new LargestCycle();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/threeVerTest.txt"));

        assertThat(nodesEqEdges.execute(trueGraph)).isEqualTo(3);
    }
    @Test
    public void twoKompTest() throws FileNotFoundException {
        LargestCycle nodesEqEdges = new LargestCycle();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/twoKompTest.txt"));

        assertThat(nodesEqEdges.execute(trueGraph)).isEqualTo(4);
    }
    @Test
    public void twoVerTest() throws FileNotFoundException {
        LargestCycle nodesEqEdges = new LargestCycle();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/twoVerTest.txt"));

        assertThat(nodesEqEdges.execute(trueGraph)).isEqualTo(0);
    }
    @Test
    public void TreeCircleTest() throws FileNotFoundException {
        LargestCycle nodesEqEdges = new LargestCycle();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/ThreeCircleTest.txt"));

        assertThat(nodesEqEdges.execute(trueGraph)).isEqualTo(13);
    }
    @Test
    public void ThreeCircleConnectTest() throws FileNotFoundException {
        LargestCycle nodesEqEdges = new LargestCycle();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/ThreeCircleConnectTest.txt"));

        assertThat(nodesEqEdges.execute(trueGraph)).isEqualTo(27);
    }
    @Test
    public void circlesDirConnectTest() throws FileNotFoundException {
        LargestCycle nodesEqEdges = new LargestCycle();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/circlesDirConnectTest.txt"));

        assertThat(nodesEqEdges.execute(trueGraph)).isEqualTo(27);
    }
}