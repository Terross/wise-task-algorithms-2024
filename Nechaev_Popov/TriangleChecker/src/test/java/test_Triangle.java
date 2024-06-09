import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class test_Triangle {

    @Test
    public void activateSimpleTest() throws FileNotFoundException {
        TriangleChecker DegreeIsTwo = new TriangleChecker();
        var falseGraph1 = GraphFactory.loadGraphFromFile(new File("src/test/java/testTriangle/graph1(false).txt"));
        var falseGraph2 = GraphFactory.loadGraphFromFile(new File("src/test/java/testTriangle/graph2(false).txt"));
        var falseGraph3 = GraphFactory.loadGraphFromFile(new File("src/test/java/testTriangle/graph3(false).txt"));
        var falseGraph4= GraphFactory.loadGraphFromFile(new File("src/test/java/testTriangle/graph5(false).txt"));
        var falseGraph5 = GraphFactory.loadGraphFromFile(new File("src/test/java/testTriangle/graph7(false).txt"));
        var trueGraph1 = GraphFactory.loadGraphFromFile(new File("src/test/java/testTriangle/graph4(true).txt"));
        var trueGraph2 = GraphFactory.loadGraphFromFile(new File("src/test/java/testTriangle/graph6(true).txt"));
        var trueGraph3 = GraphFactory.loadGraphFromFile(new File("src/test/java/testTriangle/graph8(true).txt"));

        assertThat(DegreeIsTwo.execute(falseGraph1)).isFalse();
        assertThat(DegreeIsTwo.execute(falseGraph2)).isFalse();
        assertThat(DegreeIsTwo.execute(falseGraph3)).isFalse();
        assertThat(DegreeIsTwo.execute(falseGraph4)).isFalse();
        assertThat(DegreeIsTwo.execute(falseGraph5)).isFalse();
        assertThat(DegreeIsTwo.execute(trueGraph1)).isTrue();
        assertThat(DegreeIsTwo.execute(trueGraph2)).isTrue();
        assertThat(DegreeIsTwo.execute(trueGraph3)).isTrue();
    }
}