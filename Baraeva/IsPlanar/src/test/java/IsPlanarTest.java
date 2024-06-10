import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class IsPlanarTest {
    @Test
    public void activateSimpleTest() throws FileNotFoundException {
        IsPlanar isPlanar = new IsPlanar();
        var trueGraph1 = GraphFactory.loadGraphFromFile(new File("graphs/K33-1e"));
        var trueGraph2 = GraphFactory.loadGraphFromFile(new File("graphs/K4"));
        var trueGraph3 = GraphFactory.loadGraphFromFile(new File("graphs/2chain"));
        var trueGraph4 = GraphFactory.loadGraphFromFile(new File("graphs/K33wrong"));
        var trueGraph5 = GraphFactory.loadGraphFromFile(new File("graphs/triangleGraph"));
        var falseGraph1 = GraphFactory.loadGraphFromFile(new File("graphs/K33+4e"));
        var falseGraph2 = GraphFactory.loadGraphFromFile(new File("graphs/Peterson"));
        var falseGraph3 = GraphFactory.loadGraphFromFile(new File("graphs/K33"));
        var falseGraph4 = GraphFactory.loadGraphFromFile(new File("graphs/K5"));
        var falseGraph5 = GraphFactory.loadGraphFromFile(new File("graphs/K5+K33+"));

        assertThat(isPlanar.execute(trueGraph1)).isTrue();
        assertThat(isPlanar.execute(trueGraph2)).isTrue();
        assertThat(isPlanar.execute(trueGraph3)).isTrue();
        assertThat(isPlanar.execute(trueGraph4)).isTrue();
        assertThat(isPlanar.execute(trueGraph5)).isTrue();
        assertThat(isPlanar.execute(falseGraph1)).isFalse();
        assertThat(isPlanar.execute(falseGraph2)).isFalse();
        assertThat(isPlanar.execute(falseGraph3)).isFalse();
        assertThat(isPlanar.execute(falseGraph4)).isFalse();
        assertThat(isPlanar.execute(falseGraph5)).isFalse();
    }
}