import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class TransitiveReductionTests {

    @Test
    public void testLess() throws FileNotFoundException {
        TransitiveReduction transitiveReduction = new TransitiveReduction();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/false_less.txt"));
        assertThat(transitiveReduction.execute(testGraph)).isFalse();
    }

    @Test
    public void testMuch() throws FileNotFoundException {
        TransitiveReduction transitiveReduction = new TransitiveReduction();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/false_much.txt"));
        assertThat(transitiveReduction.execute(testGraph)).isFalse();
    }

    @Test
    public void testWrong() throws FileNotFoundException {
        TransitiveReduction transitiveReduction = new TransitiveReduction();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/false_wrong.txt"));
        assertThat(transitiveReduction.execute(testGraph)).isFalse();
    }

    @Test
    public void testFAdd() throws FileNotFoundException {
        TransitiveReduction transitiveReduction = new TransitiveReduction();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/false_add.txt"));
        assertThat(transitiveReduction.execute(testGraph)).isFalse();
    }

    @Test
    public void testTAdd() throws FileNotFoundException {
        TransitiveReduction transitiveReduction = new TransitiveReduction();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/true_add.txt"));
        assertThat(transitiveReduction.execute(testGraph)).isTrue();
    }

    @Test
    public void testCommon() throws FileNotFoundException {
        TransitiveReduction transitiveReduction = new TransitiveReduction();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/true_common.txt"));
        assertThat(transitiveReduction.execute(testGraph)).isTrue();
    }

    @Test
    public void testCyclic1() throws FileNotFoundException {
        TransitiveReduction transitiveReduction = new TransitiveReduction();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/true_cyclic1.txt"));
        assertThat(transitiveReduction.execute(testGraph)).isTrue();
    }

    @Test
    public void testCyclic2() throws FileNotFoundException {
        TransitiveReduction transitiveReduction = new TransitiveReduction();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/true_cyclic2.txt"));
        assertThat(transitiveReduction.execute(testGraph)).isTrue();
    }

    @Test
    public void testUnconnected() throws FileNotFoundException {
        TransitiveReduction transitiveReduction = new TransitiveReduction();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/true_unconnected.txt"));
        assertThat(transitiveReduction.execute(testGraph)).isTrue();
    }
}
