import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class IsTwoCriticalTest {

    @Test
    public void trueGraph() throws FileNotFoundException {
        IsTwoCriticalTest IsTwoCriticalTest = new IsTwoCriticalTest();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph_k2.txt"));
        assertThat(IsTwoCriticalTest.execute(trueGraph)).isTrue();
    }
    @Test
    public void unconnectedGraph() throws FileNotFoundException {
        IsTwoCriticalTest IsTwoCriticalTest = new IsTwoCriticalTest();
        var falseGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/unconnectedgraph.txt"));
        assertThat(IsTwoCriticalTest.execute(falseGraph)).isFalse();
    }
    @Test
    public void starGraph() throws FileNotFoundException {
        IsTwoCriticalTest IsTwoCriticalTest = new IsTwoCriticalTest();
        var falseGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/starcube.txt"));
        assertThat(IsTwoCriticalTest.execute(falseGraph)).isFalse();
    }
    @Test
    public void emptyGraph() throws FileNotFoundException {
        IsTwoCriticalTest IsTwoCriticalTest = new IsTwoCriticalTest();
        var falseGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/emptygraph.txt"));
        assertThat(IsTwoCriticalTest.execute(falseGraph)).isFalse();
    }
    @Test
    public void twoCompsGraph() throws FileNotFoundException {
        IsTwoCriticalTest IsTwoCriticalTest = new IsTwoCriticalTest();
        var falseGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/2comps1cube.txt"));
        assertThat(IsTwoCriticalTest.execute(falseGraph)).isFalse();
    }

}
