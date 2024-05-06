import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class IsFourSimpleWaysTest {

    @Test
    public void activateSimpleTest() throws FileNotFoundException {
        IsFourSimpleWays isFourSimpleWays = new IsFourSimpleWays();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/trueGraph.txt"));
        var falseGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/falseGraph.txt"));

        assertThat(isFourSimpleWays.execute(trueGraph)).isTrue();
        assertThat(isFourSimpleWays.execute(falseGraph)).isFalse();
    }

    @Test
    public void oneDoteSimpleTest() throws FileNotFoundException {
        IsFourSimpleWays isFourSimpleWays = new IsFourSimpleWays();
        var oneDotGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/one_dot.txt"));
        assertThat(isFourSimpleWays.execute(oneDotGraph)).isFalse();
    }
    @Test
    public void k33Test() throws FileNotFoundException {
        IsFourSimpleWays isFourSimpleWays = new IsFourSimpleWays();
        var k33 = GraphFactory.loadGraphFromFile(new File("src/test/resources/k33.txt"));
        assertThat(isFourSimpleWays.execute(k33)).isFalse();
    }
    @Test
    public void k44Test() throws FileNotFoundException {
        IsFourSimpleWays isFourSimpleWays = new IsFourSimpleWays();
        var k44 = GraphFactory.loadGraphFromFile(new File("src/test/resources/k44.txt"));
        assertThat(isFourSimpleWays.execute(k44)).isTrue();
    }
    @Test
    public void k55Test() throws FileNotFoundException {
        IsFourSimpleWays isFourSimpleWays = new IsFourSimpleWays();
        var k55 = GraphFactory.loadGraphFromFile(new File("src/test/resources/k55.txt"));
        assertThat(isFourSimpleWays.execute(k55)).isTrue();
    }

    @Test
    public void aLotDoteSimpleTest() throws FileNotFoundException {
        IsFourSimpleWays isFourSimpleWays = new IsFourSimpleWays();
        var aLotDoteSimpleGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/alot_dots.txt"));
        assertThat(isFourSimpleWays.execute(aLotDoteSimpleGraph)).isFalse();
    }
    @Test
    public void twoDotOneLineTest() throws FileNotFoundException {
        IsFourSimpleWays isFourSimpleWays = new IsFourSimpleWays();
        var twoDotGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/two_dot_one_line.txt"));

        assertThat(isFourSimpleWays.execute(twoDotGraph)).isFalse();
    }
    @Test
    public void defaultTrueTest() throws FileNotFoundException {
        IsFourSimpleWays isFourSimpleWays = new IsFourSimpleWays();
        var defaultTrueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/must be.txt"));

        assertThat(isFourSimpleWays.execute(defaultTrueGraph)).isTrue();
    }
    @Test
    public void defaultTrueWithAloneTest() throws FileNotFoundException {
        IsFourSimpleWays isFourSimpleWays = new IsFourSimpleWays();
        var defaultTrueWithAloneGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/must_be+1 alone.txt"));

        assertThat(isFourSimpleWays.execute(defaultTrueWithAloneGraph)).isFalse();
    }
    @Test
    public void defaultFalseTest() throws FileNotFoundException {
        IsFourSimpleWays isFourSimpleWays = new IsFourSimpleWays();
        var defaultFalseGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/should not be.txt"));

        assertThat(isFourSimpleWays.execute(defaultFalseGraph)).isFalse();
    }
}