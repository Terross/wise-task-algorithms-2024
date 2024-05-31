import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class CountDeficit228Test {

    @Test
    public void activateSimpleTest() throws FileNotFoundException {
        CountDeficit228 answer = new CountDeficit228();
        var Graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph1.txt"));

        assertThat(answer.execute(Graph)).isEqualTo(2);
    }
    @Test
    public void activateZeroDeficitTest() throws FileNotFoundException {
        CountDeficit228 answer = new CountDeficit228();
        var Graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph2.txt"));

        assertThat(answer.execute(Graph)).isEqualTo(0);
    }
    @Test
    public void activateOneVertexTest() throws FileNotFoundException {
        CountDeficit228 answer = new CountDeficit228();
        var Graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph3.txt"));

        assertThat(answer.execute(Graph)).isEqualTo(4);
    }
    @Test
    public void activateFullGraphTest() throws FileNotFoundException {
        CountDeficit228 answer = new CountDeficit228();
        var Graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph5.txt"));

        assertThat(answer.execute(Graph) < 0).isTrue();
    }

}