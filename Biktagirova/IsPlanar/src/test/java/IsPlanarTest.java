import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;
import ru.leti.IsPlanar;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class IsPlanarTest {
    @Test
    public void activateSimpleTest() throws FileNotFoundException {
        IsPlanar isPl = new IsPlanar();
        var trueGraph1 = GraphFactory.loadGraphFromFile(new File("graphs/k5-1.txt"));
        var trueGraph2 = GraphFactory.loadGraphFromFile(new File("graphs/1-connected_k5-1e.txt"));
        var trueGraph3 = GraphFactory.loadGraphFromFile(new File("graphs/k33.txt"));
        var trueGraph4 = GraphFactory.loadGraphFromFile(new File("graphs/multiple_components_true.txt"));
        var falseGraph1 = GraphFactory.loadGraphFromFile(new File("graphs/homeomorphic_k5.txt"));
        var falseGraph2 = GraphFactory.loadGraphFromFile(new File("graphs/1-connected_k5.txt"));
        var falseGraph3 = GraphFactory.loadGraphFromFile(new File("graphs/k33-1.txt"));
        var falseGraph4 = GraphFactory.loadGraphFromFile(new File("graphs/multiple_components_false.txt"));

        assertThat(isPl.execute(trueGraph1)).isTrue();
        assertThat(isPl.execute(trueGraph2)).isTrue();
        assertThat(isPl.execute(trueGraph3)).isTrue();
        assertThat(isPl.execute(trueGraph4)).isTrue();
        assertThat(isPl.execute(falseGraph1)).isFalse();
        assertThat(isPl.execute(falseGraph2)).isFalse();
        assertThat(isPl.execute(falseGraph3)).isFalse();
        assertThat(isPl.execute(falseGraph4)).isFalse();
    }
}