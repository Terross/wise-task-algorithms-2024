import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class D_Choosable_Graph_Test {

    @Test
    public void activateSimpleTest() throws FileNotFoundException {
        D_Choosable_Graph D_Choosable_Graph = new D_Choosable_Graph();

        var trueGraph1 = GraphFactory.loadGraphFromFile(new File("src/test/resources/trueGraph1.txt"));
        assertThat(D_Choosable_Graph.execute(trueGraph1)).isTrue();

        var trueGraph2 = GraphFactory.loadGraphFromFile(new File("src/test/resources/trueGraph2.txt"));
        assertThat(D_Choosable_Graph.execute(trueGraph2)).isTrue();

        var trueGraph3 = GraphFactory.loadGraphFromFile(new File("src/test/resources/trueGraph3.txt"));
        assertThat(D_Choosable_Graph.execute(trueGraph3)).isTrue();

        var falseGraph1 = GraphFactory.loadGraphFromFile(new File("src/test/resources/falseGraph1.txt"));
        assertThat(D_Choosable_Graph.execute(falseGraph1)).isFalse();

        var falseGraph2 = GraphFactory.loadGraphFromFile(new File("src/test/resources/falseGraph1.txt"));
        assertThat(D_Choosable_Graph.execute(falseGraph2)).isFalse();

        var falseGraph3 = GraphFactory.loadGraphFromFile(new File("src/test/resources/falseGraph3.txt"));
        assertThat(D_Choosable_Graph.execute(falseGraph3)).isFalse();

    }
}
