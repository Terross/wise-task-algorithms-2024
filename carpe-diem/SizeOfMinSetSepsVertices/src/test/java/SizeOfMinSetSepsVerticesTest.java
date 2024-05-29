import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class SizeOfMinSetSepsVerticesTest {

    @Test
    public void activateSimpleTest() throws FileNotFoundException {
        SizeOfMinSetSepsVertices sizeOfMinSetSepsVertices = new SizeOfMinSetSepsVertices();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/SimpleTest1.txt"));
        Integer result = sizeOfMinSetSepsVertices.execute(testGraph);
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void noWayTest() throws FileNotFoundException
    {
        SizeOfMinSetSepsVertices sizeOfMinSetSepsVertices = new SizeOfMinSetSepsVertices();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/noWayTest.txt"));
        Integer result = sizeOfMinSetSepsVertices.execute(testGraph);
        assertThat(result).isEqualTo(-1);
    }

    @Test
    public void twoDiffWaysTest() throws FileNotFoundException
    {
        SizeOfMinSetSepsVertices sizeOfMinSetSepsVertices = new SizeOfMinSetSepsVertices();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/twoDiffWaysTest.txt"));
        Integer result = sizeOfMinSetSepsVertices.execute(testGraph);
        assertThat(result).isEqualTo(2);
    }

    @Test
    public void cycleTest() throws FileNotFoundException
    {
        SizeOfMinSetSepsVertices sizeOfMinSetSepsVertices = new SizeOfMinSetSepsVertices();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/cycleTest.txt"));
        Integer result = sizeOfMinSetSepsVertices.execute(testGraph);
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void noBlueRedVerticesTest() throws FileNotFoundException
    {
        SizeOfMinSetSepsVertices sizeOfMinSetSepsVertices = new SizeOfMinSetSepsVertices();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/noBlueRedVerticesTest.txt"));
        Integer result = sizeOfMinSetSepsVertices.execute(testGraph);
        assertThat(result).isEqualTo(-1);
    }

    @Test
    public void defaultTest() throws FileNotFoundException
    {
        SizeOfMinSetSepsVertices sizeOfMinSetSepsVertices = new SizeOfMinSetSepsVertices();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/deafultTest.txt"));
        Integer result = sizeOfMinSetSepsVertices.execute(testGraph);
        assertThat(result).isEqualTo(1);
    }


}
