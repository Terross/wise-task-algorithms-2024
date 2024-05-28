import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class MaxVertexDegreeTest{

    @Test
    public void defaultTest() throws FileNotFoundException
    {
        MaxVertexDegree MaxVertexDegree = new MaxVertexDegree();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/deafultTest.txt"));
        Integer result = MaxVertexDegree.execute(testGraph);
        assertThat(result).isEqualTo(4);
    }

    @Test
    public void SimpleTest() throws FileNotFoundException {
        MaxVertexDegree MaxVertexDegree = new MaxVertexDegree();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/SimpleTest.txt"));
        Integer result = MaxVertexDegree.execute(testGraph);
        assertThat(result).isEqualTo(3);
    }

    @Test
    public void eqDegreesTest() throws FileNotFoundException
    {
        MaxVertexDegree MaxVertexDegree = new MaxVertexDegree();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/eqDegreesTest.txt"));
        Integer result = MaxVertexDegree.execute(testGraph);
        assertThat(result).isEqualTo(4);
    }

    @Test
    public void noEdgesTest() throws FileNotFoundException
    {
        MaxVertexDegree MaxVertexDegree = new MaxVertexDegree();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/noEdgesTest.txt"));
        Integer result = MaxVertexDegree.execute(testGraph);
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void degreesDifferentTest() throws FileNotFoundException
    {
        MaxVertexDegree MaxVertexDegree = new MaxVertexDegree();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/degreesDifferentTest.txt"));
        Integer result = MaxVertexDegree.execute(testGraph);
        assertThat(result).isEqualTo(5);
    }

}
