import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class FourPeriodicTest {

    @Test
    public void activateSimpleTest() throws FileNotFoundException {
        FourPeriodic fourPeriodic = new FourPeriodic();
        var FourPeriodicGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/FourPeriodicGraph.txt"));
        var FalseFourPeriodicGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/FalseFourPeriodicGraph.txt"));
        var ThreeVerticesGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/ThreeVerticesGraph.txt"));
        var FiveColorsGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/FiveColorsGraph.txt"));
        var ThreeColorsGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/ThreeColorsGraph.txt"));
        var ExtendedFourPeriodicGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/ExtendedFourPeriodicGraph.txt"));
        var K4Graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/K4Graph.txt"));
        var DoubledK4Graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/DoubledK4Graph.txt"));
        var CycledGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/CycledGraph.txt"));
        var FiveVerticlesGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/FiveVerticlesGraph.txt"));

        assertThat(fourPeriodic.execute(FourPeriodicGraph)).isTrue();
        assertThat(fourPeriodic.execute(FalseFourPeriodicGraph)).isFalse();
        assertThat(fourPeriodic.execute(ThreeVerticesGraph)).isFalse();
        assertThat(fourPeriodic.execute(FiveColorsGraph)).isFalse();
        assertThat(fourPeriodic.execute(ThreeColorsGraph)).isFalse();
        assertThat(fourPeriodic.execute(ExtendedFourPeriodicGraph)).isFalse();
        assertThat(fourPeriodic.execute(K4Graph)).isTrue();
        assertThat(fourPeriodic.execute(DoubledK4Graph)).isFalse();
        assertThat(fourPeriodic.execute(CycledGraph)).isFalse();
        assertThat(fourPeriodic.execute(FiveVerticlesGraph)).isFalse();
    }
}