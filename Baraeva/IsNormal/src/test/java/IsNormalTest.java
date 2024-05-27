import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class IsNormalTest {
    @Test
    public void activateSimpleTest() throws FileNotFoundException {
        IsNormal isNormal = new IsNormal();
        var trueGraph1 = GraphFactory.loadGraphFromFile(new File("graphs/lonely_red"));
        var trueGraph2 = GraphFactory.loadGraphFromFile(new File("graphs/true_red"));
        var trueGraph3 = GraphFactory.loadGraphFromFile(new File("graphs/gray"));
        var trueGraph4 = GraphFactory.loadGraphFromFile(new File("graphs/snowflake"));
        var trueGraph5 = GraphFactory.loadGraphFromFile(new File("graphs/K33"));
        var falseGraph1 = GraphFactory.loadGraphFromFile(new File("graphs/false_red"));
        var falseGraph2 = GraphFactory.loadGraphFromFile(new File("graphs/twoG_start_red"));
        var falseGraph3 = GraphFactory.loadGraphFromFile(new File("graphs/twoG_start_gray"));
        var falseGraph4 = GraphFactory.loadGraphFromFile(new File("graphs/chain"));
        var falseGraph5 = GraphFactory.loadGraphFromFile(new File("graphs/big_K5"));

        assertThat(isNormal.execute(trueGraph1)).isTrue();
        assertThat(isNormal.execute(trueGraph2)).isTrue();
        assertThat(isNormal.execute(trueGraph3)).isTrue();
        assertThat(isNormal.execute(trueGraph4)).isTrue();
        assertThat(isNormal.execute(trueGraph5)).isTrue();
        assertThat(isNormal.execute(falseGraph1)).isFalse();
        assertThat(isNormal.execute(falseGraph2)).isFalse();
        assertThat(isNormal.execute(falseGraph3)).isFalse();
        assertThat(isNormal.execute(falseGraph4)).isFalse();
        assertThat(isNormal.execute(falseGraph5)).isFalse();
    }
}
