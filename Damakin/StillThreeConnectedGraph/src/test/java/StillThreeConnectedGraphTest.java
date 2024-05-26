import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StillThreeConnectedGraphTest {

    @Test
    public void activateSimpleTest() throws FileNotFoundException {
        StillThreeConnectedGraph StillThreeConnected = new StillThreeConnectedGraph();
        var falseGraph1 = GraphFactory.loadGraphFromFile(new File("src/test/resources/falseGraph1.txt"));
        var falseGraph2 = GraphFactory.loadGraphFromFile(new File("src/test/resources/falseGraph2.txt"));
        var falseGraph3 = GraphFactory.loadGraphFromFile(new File("src/test/resources/falseGraph3.txt"));
        var falseGraph4 = GraphFactory.loadGraphFromFile(new File("src/test/resources/falseGraph4.txt"));

        var trueGraph1 = GraphFactory.loadGraphFromFile(new File("src/test/resources/trueGraph1.txt"));
        var trueGraph2 = GraphFactory.loadGraphFromFile(new File("src/test/resources/trueGraph2.txt"));
        var trueGraph3 = GraphFactory.loadGraphFromFile(new File("src/test/resources/trueGraph3.txt"));

        assertThat(StillThreeConnected.execute(falseGraph1)).isFalse();
        assertThat(StillThreeConnected.execute(falseGraph2)).isFalse();
        assertThat(StillThreeConnected.execute(falseGraph3)).isFalse();
        assertThat(StillThreeConnected.execute(falseGraph4)).isFalse();

        assertThat(StillThreeConnected.execute(trueGraph1)).isTrue();
        assertThat(StillThreeConnected.execute(trueGraph2)).isTrue();
        assertThat(StillThreeConnected.execute(trueGraph3)).isTrue();
    }
}