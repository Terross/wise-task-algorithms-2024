import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class CheckThreePathsTest {
    //    UNDIRECTED
    @Test
    public void UndSimpleTest() throws FileNotFoundException {
        CheckThreePaths checkThreePaths = new CheckThreePaths();
        var first = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkThreePaths/undirected/graph0.txt"));
        var second = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkThreePaths/undirected/graph1.txt"));
        var third = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkThreePaths/undirected/graph3.txt"));

        assertThat(checkThreePaths.execute(first)).isTrue();
        assertThat(checkThreePaths.execute(second)).isTrue();
        assertThat(checkThreePaths.execute(third)).isTrue();
    }

    @Test
    public void UndMiddleTest() throws FileNotFoundException {
        CheckThreePaths checkThreePaths = new CheckThreePaths();
        var first = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkThreePaths/undirected/graph2.txt"));
        var second = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkThreePaths/undirected/graph5.txt"));
        var third = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkThreePaths/undirected/graph6.txt"));

        assertThat(checkThreePaths.execute(first)).isTrue();
        assertThat(checkThreePaths.execute(second)).isTrue();
        assertThat(checkThreePaths.execute(third)).isTrue();
    }

    @Test
    public void UndComplexTest() throws FileNotFoundException {
        CheckThreePaths checkThreePaths = new CheckThreePaths();
        var true_graph = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkThreePaths/undirected/complex.txt"));
        var false_graph = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkThreePaths/undirected/complex_false.txt"));

        assertThat(checkThreePaths.execute(true_graph)).isTrue();
        assertThat(checkThreePaths.execute(false_graph)).isFalse();
    }

    @Test
    public void UndNoEdges() throws FileNotFoundException {
        CheckThreePaths checkThreePaths = new CheckThreePaths();
        var graph = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkThreePaths/undirected/noEdges.txt"));

        assertThat(checkThreePaths.execute(graph)).isFalse();
    }

    @Test
    public void UndCompleteTest() throws FileNotFoundException {
        CheckThreePaths checkThreePaths = new CheckThreePaths();
        var k2 = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkThreePaths/undirected/k2.txt"));
        var k3 = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkThreePaths/undirected/k3.txt"));
        var k5 = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkThreePaths/undirected/k5.txt"));
        var k6 = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkThreePaths/undirected/k6.txt"));
        var k8 = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkThreePaths/undirected/k8.txt"));

        assertThat(checkThreePaths.execute(k2)).isFalse();
        assertThat(checkThreePaths.execute(k3)).isFalse();
        assertThat(checkThreePaths.execute(k5)).isTrue();
        assertThat(checkThreePaths.execute(k6)).isTrue();
        assertThat(checkThreePaths.execute(k8)).isTrue();
    }

    @Test
    public void UndCompleteBipartiteTest() throws FileNotFoundException {
        CheckThreePaths checkThreePaths = new CheckThreePaths();
        var k_2_2 = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkThreePaths/undirected/k_2_2.txt"));
        var k_2_3 = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkThreePaths/undirected/k_2_3.txt"));
        var k_4_3 = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkThreePaths/undirected/k_4_3.txt"));
        var k_4_4 = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkThreePaths/undirected/k_4_4.txt"));

        assertThat(checkThreePaths.execute(k_2_2)).isFalse();
        assertThat(checkThreePaths.execute(k_2_3)).isFalse();
        assertThat(checkThreePaths.execute(k_4_3)).isTrue();
        assertThat(checkThreePaths.execute(k_4_4)).isTrue();
    }

    @Test
    public void ComponentsTest() throws FileNotFoundException {
        CheckThreePaths checkThreePaths = new CheckThreePaths();
        var first = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkThreePaths/undirected/two_components_t_f.txt"));
        var second = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkThreePaths/undirected/two_components_f_f.txt"));
        var third = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkThreePaths/undirected/added.txt"));

        assertThat(checkThreePaths.execute(first)).isFalse();
        assertThat(checkThreePaths.execute(second)).isFalse();
        assertThat(checkThreePaths.execute(third)).isFalse();
    }

    @Test
    public void NotObviousTest() throws FileNotFoundException {
        CheckThreePaths checkThreePaths = new CheckThreePaths();
        var first = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkThreePaths/undirected/wrong1.txt"));
        var second = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkThreePaths/undirected/wrong4.txt"));

        assertThat(checkThreePaths.execute(first)).isFalse();
        assertThat(checkThreePaths.execute(second)).isFalse();
    }

    @Test
    public void WheelTest() throws FileNotFoundException {
        CheckThreePaths checkThreePaths = new CheckThreePaths();
        var first = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkThreePaths/undirected/wheel.txt"));

        assertThat(checkThreePaths.execute(first)).isTrue();
    }

    //    DIRECTED
    @Test
    public void DirSimpleTest() throws FileNotFoundException {
        CheckThreePaths checkThreePaths = new CheckThreePaths();
        var first = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkThreePaths/directed/graph1.txt"));
        var second = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkThreePaths/directed/graph2.txt"));

        assertThat(checkThreePaths.execute(first)).isFalse();
        assertThat(checkThreePaths.execute(second)).isFalse();
    }

    @Test
    public void DirMiddleTest() throws FileNotFoundException {
        CheckThreePaths checkThreePaths = new CheckThreePaths();
        var first = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkThreePaths/directed/graph3.txt"));
        var second = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkThreePaths/directed/almostK_5_5.txt"));
        var third = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkThreePaths/directed/almostK10.txt"));

        assertThat(checkThreePaths.execute(first)).isFalse();
        assertThat(checkThreePaths.execute(second)).isFalse();
        assertThat(checkThreePaths.execute(third)).isTrue();
    }

    @Test
    public void DirNoEdges() throws FileNotFoundException {
        CheckThreePaths checkThreePaths = new CheckThreePaths();
        var graph = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkThreePaths/directed/noEdges.txt"));

        assertThat(checkThreePaths.execute(graph)).isFalse();
    }

    @Test
    public void DirComplexTest() throws FileNotFoundException {
        CheckThreePaths checkThreePaths = new CheckThreePaths();
        var true_graph = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkThreePaths/directed/graph0.txt"));
        var false_graph = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkThreePaths/directed/wrong0.txt"));

        assertThat(checkThreePaths.execute(true_graph)).isTrue();
        assertThat(checkThreePaths.execute(false_graph)).isFalse();
    }
}