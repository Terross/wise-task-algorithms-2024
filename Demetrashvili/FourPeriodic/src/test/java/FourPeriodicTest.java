import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class FourPeriodicTest {

    @Test
    public void activateSimpleTest() throws FileNotFoundException {
        FourPeriodic fourPeriodic = new FourPeriodic();
        var test1 = GraphFactory.loadGraphFromFile(new File("src/test/resources/simple_graph.txt"));
        var test2 = GraphFactory.loadGraphFromFile(new File("src/test/resources/List_true.txt"));

        assertThat(fourPeriodic.execute(test1)).isFalse();
        assertThat(fourPeriodic.execute(test2)).isTrue();

    }
}