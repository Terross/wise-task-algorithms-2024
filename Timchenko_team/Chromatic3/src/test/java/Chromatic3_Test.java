import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class Chromatic3_Test {

    @Test
    public void activateSimpleTest() throws FileNotFoundException {
        Chromatic3 Chromatic3 = new Chromatic3();

        var trueGraph1 = GraphFactory.loadGraphFromFile(new File("src/test/resources/trueGraph1.txt"));
        assertThat(Chromatic3.execute(trueGraph1)).isTrue();

        var trueGraph2 = GraphFactory.loadGraphFromFile(new File("src/test/resources/trueGraph2.txt"));
        assertThat(Chromatic3.execute(trueGraph2)).isTrue();

        var trueGraph3 = GraphFactory.loadGraphFromFile(new File("src/test/resources/trueGraph3.txt"));
        assertThat(Chromatic3.execute(trueGraph3)).isTrue();

        var falseGraph1 = GraphFactory.loadGraphFromFile(new File("src/test/resources/falseGraph1.txt"));
        assertThat(Chromatic3.execute(falseGraph1)).isFalse();

        var falseGraph2 = GraphFactory.loadGraphFromFile(new File("src/test/resources/falseGraph2.txt"));
        assertThat(Chromatic3.execute(falseGraph2)).isFalse();

        var falseGraph3 = GraphFactory.loadGraphFromFile(new File("src/test/resources/falseGraph3.txt"));
        assertThat(Chromatic3.execute(falseGraph3)).isFalse();

    }
}
