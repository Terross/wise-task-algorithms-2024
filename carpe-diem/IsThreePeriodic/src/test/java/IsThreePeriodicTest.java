import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class IsThreePeriodicTest {

    @Test
    public void activateSimpleTest() throws FileNotFoundException {
        IsThreePeriodic sizeOfMinSetSepsVertices = new IsThreePeriodic();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/SimpleTest1.txt"));
        boolean result = sizeOfMinSetSepsVertices.execute(testGraph);
        assertThat(result).isTrue();
    }

    @Test
    public void noCycleTest() throws FileNotFoundException {
        IsThreePeriodic sizeOfMinSetSepsVertices = new IsThreePeriodic();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/noCycleTest.txt"));
        boolean result = sizeOfMinSetSepsVertices.execute(testGraph);
        assertThat(result).isTrue();
    }

    @Test
    public void cycleTest() throws FileNotFoundException {
        IsThreePeriodic sizeOfMinSetSepsVertices = new IsThreePeriodic();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/cycleTest.txt"));
        boolean result = sizeOfMinSetSepsVertices.execute(testGraph);
        assertThat(result).isTrue();
    }

    @Test
    public void cycleLengthThreeTest() throws FileNotFoundException {
        IsThreePeriodic sizeOfMinSetSepsVertices = new IsThreePeriodic();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/cycleLengthThreeTest.txt"));
        boolean result = sizeOfMinSetSepsVertices.execute(testGraph);
        assertThat(result).isTrue();
    }
}