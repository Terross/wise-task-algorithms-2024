import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class AntiTransitiveTest {

    @Test
    public void activateSimpleTest() throws FileNotFoundException {
        AntiTransitive antitransitive = new AntiTransitive();
        for (int i = 1; i <= 4; i++) {
            var graph = GraphFactory.loadGraphFromFile(new File(String.format("src/test/resources/graph%d.txt", i)));
            assertThat(antitransitive.execute(graph)).isTrue();
        }
        for (int i = 5; i <= 8; i++) {
            var graph = GraphFactory.loadGraphFromFile(new File(String.format("src/test/resources/graph%d.txt", i)));
            assertThat(antitransitive.execute(graph)).isFalse();
        }
    }
}