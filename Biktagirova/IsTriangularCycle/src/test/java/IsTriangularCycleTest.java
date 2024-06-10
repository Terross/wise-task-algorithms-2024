import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;
import ru.leti.IsTriangularCycle;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class IsTriangularCycleTest {
    @Test
    public void activateSimpleTest() throws FileNotFoundException {
        IsTriangularCycle isTrC = new IsTriangularCycle();
        var trueGraph1 = GraphFactory.loadGraphFromFile(new File("graphs/true1.txt"));
        var trueGraph2 = GraphFactory.loadGraphFromFile(new File("graphs/true2.txt"));
        var falseGraph1 = GraphFactory.loadGraphFromFile(new File("graphs/false1.txt"));
        var falseGraph2 = GraphFactory.loadGraphFromFile(new File("graphs/false2.txt"));
        var falseGraph3 = GraphFactory.loadGraphFromFile(new File("graphs/internal_edge_in_c.txt"));
        var falseGraph4 = GraphFactory.loadGraphFromFile(new File("graphs/external_edge_in_c.txt"));
        var falseGraph5 = GraphFactory.loadGraphFromFile(new File("graphs/not_c.txt"));

        assertThat(isTrC.execute(trueGraph1)).isTrue();
        assertThat(isTrC.execute(trueGraph2)).isTrue();
        assertThat(isTrC.execute(falseGraph1)).isFalse();
        assertThat(isTrC.execute(falseGraph2)).isFalse();
        assertThat(isTrC.execute(falseGraph3)).isFalse();
        assertThat(isTrC.execute(falseGraph4)).isFalse();
        assertThat(isTrC.execute(falseGraph5)).isFalse();
    }
}