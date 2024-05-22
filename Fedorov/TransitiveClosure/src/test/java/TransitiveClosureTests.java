import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class TransitiveClosureTests {

    @Test
    public void testLess() throws FileNotFoundException {
        TransitiveClosure transitiveClosure = new TransitiveClosure();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/false_less.txt"));
        assertThat(transitiveClosure.execute(testGraph)).isFalse();
    }

    @Test
    public void testMuch() throws FileNotFoundException {
        TransitiveClosure transitiveClosure = new TransitiveClosure();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/false_much.txt"));
        assertThat(transitiveClosure.execute(testGraph)).isFalse();
    }

    @Test
    public void testWrong() throws FileNotFoundException {
        TransitiveClosure transitiveClosure = new TransitiveClosure();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/false_wrong.txt"));
        assertThat(transitiveClosure.execute(testGraph)).isFalse();
    }

    @Test
    public void testFAdd() throws FileNotFoundException {
        TransitiveClosure transitiveClosure = new TransitiveClosure();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/false_add.txt"));
        assertThat(transitiveClosure.execute(testGraph)).isFalse();
    }

    @Test
    public void testTAdd() throws FileNotFoundException {
        TransitiveClosure transitiveClosure = new TransitiveClosure();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/true_add.txt"));
        assertThat(transitiveClosure.execute(testGraph)).isTrue();
    }

    @Test
    public void testCommon() throws FileNotFoundException {
        TransitiveClosure transitiveClosure = new TransitiveClosure();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/true_common.txt"));
        assertThat(transitiveClosure.execute(testGraph)).isTrue();
    }

    @Test
    public void testCyclic1() throws FileNotFoundException {
        TransitiveClosure transitiveClosure = new TransitiveClosure();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/true_cyclic1.txt"));
        assertThat(transitiveClosure.execute(testGraph)).isTrue();
    }

    @Test
    public void testCyclic2() throws FileNotFoundException {
        TransitiveClosure transitiveClosure = new TransitiveClosure();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/true_cyclic2.txt"));
        assertThat(transitiveClosure.execute(testGraph)).isTrue();
    }

    @Test
    public void testUnconnected() throws FileNotFoundException {
        TransitiveClosure transitiveClosure = new TransitiveClosure();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/true_unconnected.txt"));
        assertThat(transitiveClosure.execute(testGraph)).isTrue();
    }
}
