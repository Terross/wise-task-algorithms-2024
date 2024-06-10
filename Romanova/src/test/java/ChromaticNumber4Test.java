import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class ChromaticNumber4Test {

    @Test
    public void activateSimpleTest() throws FileNotFoundException {
        ChromaticNumber4 chromatic_number = new ChromaticNumber4();
        for (int i = 1; i <= 3; i++) {
            var graph = GraphFactory.loadGraphFromFile(new File(String.format("src/test/resources/graph%d.txt", i)));
            assertThat(chromatic_number.execute(graph)).isTrue();
        }
        for (int i = 4; i <= 9; i++) {
            var graph = GraphFactory.loadGraphFromFile(new File(String.format("src/test/resources/graph%d.txt", i)));
            assertThat(chromatic_number.execute(graph)).isFalse();
        }
    }
}
