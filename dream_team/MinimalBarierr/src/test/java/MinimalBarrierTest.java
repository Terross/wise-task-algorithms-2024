import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class MinimalBarrierTest {

    @Test
    public void activateSimpleTest() throws FileNotFoundException {
        MinimalBarrier a = new MinimalBarrier();
        var trueGraph1 = GraphFactory.loadGraphFromFile(new File("src/test/resources/true/minimal.txt"));
        var trueGraph2 = GraphFactory.loadGraphFromFile(new File("src/test/resources/true/s_eq_0.txt"));
        var trueGraph3 = GraphFactory.loadGraphFromFile(new File("src/test/resources/true/graph.txt"));
        var trueGraph4 = GraphFactory.loadGraphFromFile(new File("src/test/resources/true/s_eq_2.txt"));

        assertThat(a.execute(trueGraph1)).isTrue();
        assertThat(a.execute(trueGraph2)).isTrue();
        assertThat(a.execute(trueGraph3)).isTrue();
        assertThat(a.execute(trueGraph4)).isTrue();

        var falseGraph1 = GraphFactory.loadGraphFromFile(new File("src/test/resources/false/red_but_not_bar.txt"));
        var falseGraph2 = GraphFactory.loadGraphFromFile(new File("src/test/resources/false/supp_barr.txt"));
        var falseGraph3 = GraphFactory.loadGraphFromFile(new File("src/test/resources/false/not_bi.txt"));
        var falseGraph4 = GraphFactory.loadGraphFromFile(new File("src/test/resources/false/ther_is_less.txt"));

        assertThat(a.execute(falseGraph1)).isFalse();
        assertThat(a.execute(falseGraph2)).isFalse();
        assertThat(a.execute(falseGraph3)).isFalse();
        assertThat(a.execute(falseGraph4)).isFalse();


    }
}