import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class CheckTriangulationTest {
    //    UNDIRECTED
    @Test
    public void SimpleTest() throws FileNotFoundException {
        CheckTriangulation checkTriangulation = new CheckTriangulation();
        var first = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkTriangulation/undirected/graph0_correct.txt"));
        var second = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkTriangulation/undirected/graph1_wrong.txt"));

        assertThat(checkTriangulation.execute(first)).isTrue();
        assertThat(checkTriangulation.execute(second)).isFalse();
    }

    @Test
    public void MiddleTest() throws FileNotFoundException {
        CheckTriangulation checkTriangulation = new CheckTriangulation();
        var first = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkTriangulation/undirected/middle0.txt"));
        var second = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkTriangulation/undirected/middle1.txt"));

        assertThat(checkTriangulation.execute(first)).isTrue();
        assertThat(checkTriangulation.execute(second)).isTrue();
    }

    @Test
    public void ComplexTest() throws FileNotFoundException {
        CheckTriangulation checkTriangulation = new CheckTriangulation();
        var first = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkTriangulation/undirected/complex0.txt"));
        var second = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkTriangulation/undirected/complex1.txt"));
        var third = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkTriangulation/undirected/complex2.txt"));

        assertThat(checkTriangulation.execute(first)).isTrue();
        assertThat(checkTriangulation.execute(second)).isTrue();
        assertThat(checkTriangulation.execute(third)).isTrue();
    }

    @Test
    public void WrongTest() throws FileNotFoundException {
        CheckTriangulation checkTriangulation = new CheckTriangulation();
        var first = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkTriangulation/undirected/wrong0.txt"));
        var second = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkTriangulation/undirected/wrong1.txt"));
        var third = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkTriangulation/undirected/wrong2.txt"));

        assertThat(checkTriangulation.execute(first)).isFalse();
        assertThat(checkTriangulation.execute(second)).isFalse();
        assertThat(checkTriangulation.execute(third)).isFalse();
    }

    //    DIRECTED - is the same
    @Test
    public void Test() throws FileNotFoundException {
        CheckTriangulation checkTriangulation = new CheckTriangulation();
        var first = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkTriangulation/directed/test1.txt"));
        var second = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkTriangulation/directed/test2.txt"));

        assertThat(checkTriangulation.execute(first)).isTrue();
        assertThat(checkTriangulation.execute(second)).isTrue();
    }
}