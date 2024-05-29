import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class IsPlanarWithoutSomeEdgeTest {

    @Test
    public void activateSimpleTest() throws FileNotFoundException {
        IsPlanarWithoutSomeEdge isPlanarWithoutSomeEdge = new IsPlanarWithoutSomeEdge();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/trueGraph.txt"));
        var falseGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/falseGraph.txt"));

        assertThat(isPlanarWithoutSomeEdge.execute(trueGraph)).isTrue();
        assertThat(isPlanarWithoutSomeEdge.execute(falseGraph)).isFalse();
    }

    @Test
    public void oneDoteSimpleTest() throws FileNotFoundException {
        IsPlanarWithoutSomeEdge isPlanarWithoutSomeEdge = new IsPlanarWithoutSomeEdge();
        var oneDotGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/one_dot.txt"));
        assertThat(isPlanarWithoutSomeEdge.execute(oneDotGraph)).isFalse();
    }

    @Test
    public void aLotDoteSimpleTest() throws FileNotFoundException {
        IsPlanarWithoutSomeEdge isPlanarWithoutSomeEdge = new IsPlanarWithoutSomeEdge();
        var aLotDoteSimpleGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/alot_dots.txt"));
        assertThat(isPlanarWithoutSomeEdge.execute(aLotDoteSimpleGraph)).isFalse();
    }
    @Test
    public void twoDotOneLineTest() throws FileNotFoundException {
        IsPlanarWithoutSomeEdge isPlanarWithoutSomeEdge = new IsPlanarWithoutSomeEdge();
        var twoDotGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/two_dot_one_line.txt"));

        assertThat(isPlanarWithoutSomeEdge.execute(twoDotGraph)).isFalse();
    }
    @Test
    public void aLotDotsAndLinesTest() throws FileNotFoundException {
        IsPlanarWithoutSomeEdge isPlanarWithoutSomeEdge = new IsPlanarWithoutSomeEdge();
        var defaultTrueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/must not be.txt"));

        assertThat(isPlanarWithoutSomeEdge.execute(defaultTrueGraph)).isFalse();
    }
    @Test
    public void crossTest() throws FileNotFoundException {
        IsPlanarWithoutSomeEdge isPlanarWithoutSomeEdge = new IsPlanarWithoutSomeEdge();
        var crossGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/cross.txt"));

        assertThat(isPlanarWithoutSomeEdge.execute(crossGraph)).isFalse();
    }
    @Test
    public void k5Test() throws FileNotFoundException {
        IsPlanarWithoutSomeEdge isPlanarWithoutSomeEdge = new IsPlanarWithoutSomeEdge();
        var k5 = GraphFactory.loadGraphFromFile(new File("src/test/resources/k5.txt"));

        assertThat(isPlanarWithoutSomeEdge.execute(k5)).isTrue();
    }
    @Test
    public void k33Test() throws FileNotFoundException {
        IsPlanarWithoutSomeEdge isPlanarWithoutSomeEdge = new IsPlanarWithoutSomeEdge();
        var k33 = GraphFactory.loadGraphFromFile(new File("src/test/resources/k33.txt"));

        assertThat(isPlanarWithoutSomeEdge.execute(k33)).isTrue();
    }
    @Test
    public void defaultTrueWithAloneTest() throws FileNotFoundException {
        IsPlanarWithoutSomeEdge isPlanarWithoutSomeEdge = new IsPlanarWithoutSomeEdge();
        var defaultTrueWithAloneGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/must_be+1 alone.txt"));

        assertThat(isPlanarWithoutSomeEdge.execute(defaultTrueWithAloneGraph)).isFalse();
    }
    @Test
    public void defaultFalseTest() throws FileNotFoundException {
        IsPlanarWithoutSomeEdge isPlanarWithoutSomeEdge = new IsPlanarWithoutSomeEdge();
        var defaultFalseGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/should not be.txt"));

        assertThat(isPlanarWithoutSomeEdge.execute(defaultFalseGraph)).isFalse();
    }
}
