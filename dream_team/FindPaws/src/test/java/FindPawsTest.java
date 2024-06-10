import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class FindPawsTest {

    @Test
    public void activateSimpleTest() throws FileNotFoundException {
        FindPaws nodesEqEdges = new FindPaws();
        // Must be true:
        var k13 = GraphFactory.loadGraphFromFile(new File("src/test/resources/k13.txt"));
        var k13_withoutred = GraphFactory.loadGraphFromFile(new File("src/test/resources/k13_withoutred.txt"));
        var k13_plus_1 = GraphFactory.loadGraphFromFile(new File("src/test/resources/k13_plus_1.txt"));
        var k13_two = GraphFactory.loadGraphFromFile(new File("src/test/resources/k13_two.txt"));
        var k132 = GraphFactory.loadGraphFromFile(new File("src/test/resources/k132.txt"));
        assertThat(nodesEqEdges.execute(k13)).isTrue();
        assertThat(nodesEqEdges.execute(k13_withoutred)).isTrue();
        assertThat(nodesEqEdges.execute(k13_plus_1)).isTrue();
        assertThat(nodesEqEdges.execute(k13_two)).isTrue();
        assertThat(nodesEqEdges.execute(k132)).isTrue();
        // Must be false:
        var k12 = GraphFactory.loadGraphFromFile(new File("src/test/resources/k12.txt"));
        var k13k14 = GraphFactory.loadGraphFromFile(new File("src/test/resources/k13k14.txt"));
        var k13k13_tree = GraphFactory.loadGraphFromFile(new File("src/test/resources/k13k13_tree.txt"));
        var false_k13 = GraphFactory.loadGraphFromFile(new File("src/test/resources/false_k13.txt"));
        var false_k121 = GraphFactory.loadGraphFromFile(new File("src/test/resources/false_k121.txt"));
        assertThat(nodesEqEdges.execute(k12)).isFalse();
        assertThat(nodesEqEdges.execute(k13k14)).isFalse();
        assertThat(nodesEqEdges.execute(k13k13_tree)).isFalse();
        assertThat(nodesEqEdges.execute(false_k13)).isFalse();
        assertThat(nodesEqEdges.execute(false_k121)).isFalse();
    }
}
