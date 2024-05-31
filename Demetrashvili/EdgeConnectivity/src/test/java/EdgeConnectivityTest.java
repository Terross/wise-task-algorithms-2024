import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class EdgeConnectivityTest {

    @Test
    public void activateSimpleTest() throws FileNotFoundException {
        var isPlanarWithoutSomeEdge = new EdgeConnectivity();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/trueGraph.txt"));
        var falseGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/falseGraph.txt"));

        assertThat(isPlanarWithoutSomeEdge.execute(trueGraph)).isTrue();
        assertThat(isPlanarWithoutSomeEdge.execute(falseGraph)).isFalse();
    }

    @Test
    public void oneDoteSimpleTest() throws FileNotFoundException {
        var isPlanarWithoutSomeEdge = new EdgeConnectivity();
        var oneDotGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/1.txt"));
        assertThat(isPlanarWithoutSomeEdge.execute(oneDotGraph)).isFalse();
    }

    @Test
    public void aLotDoteSimpleTest() throws FileNotFoundException {
        var isPlanarWithoutSomeEdge = new EdgeConnectivity();
        var aLotDoteSimpleGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/2.txt"));
        assertThat(isPlanarWithoutSomeEdge.execute(aLotDoteSimpleGraph)).isFalse();
    }
    @Test
    public void twoDotOneLineTest() throws FileNotFoundException {
        var isPlanarWithoutSomeEdge = new EdgeConnectivity();
        var twoDotGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/3.txt"));

        assertThat(isPlanarWithoutSomeEdge.execute(twoDotGraph)).isFalse();
    }
    @Test
    public void aLotDotsAndLinesTest() throws FileNotFoundException {
        var isPlanarWithoutSomeEdge = new EdgeConnectivity();
        var defaultTrueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/4.txt"));

        assertThat(isPlanarWithoutSomeEdge.execute(defaultTrueGraph)).isFalse();
    }


}
