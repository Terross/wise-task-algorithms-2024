import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;
import java.io.File;
import java.io.FileNotFoundException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class NumberFacesPlanarGraphTest {

    @Test
    public void activateSimpleTest() throws FileNotFoundException {
        NumberFacesPlanarGraph nodesEqEdges = new NumberFacesPlanarGraph();

        var nPlanar = GraphFactory.loadGraphFromFile(new File("src/test/java/resources/nPlanar.txt"));
        var zeroVerticesAndEdge = GraphFactory.loadGraphFromFile(new File("src/test/java/resources/zeroVerticesAndEdge.txt"));
        var zeroEdge = GraphFactory.loadGraphFromFile(new File("src/test/java/resources/zeroEdge.txt"));
        var nConnected = GraphFactory.loadGraphFromFile(new File("src/test/java/resources/nConnected.txt"));
        var faceCounting = GraphFactory.loadGraphFromFile(new File("src/test/java/resources/faceCounting.txt"));
        var difficultGraph = GraphFactory.loadGraphFromFile(new File("src/test/java/resources/difficultGraph.txt"));

        assertThrows(IllegalArgumentException.class, () -> nodesEqEdges.execute(nPlanar));
        assertThrows(IllegalArgumentException.class, () -> nodesEqEdges.execute(zeroVerticesAndEdge));
        assertThrows(IllegalArgumentException.class, () -> nodesEqEdges.execute(zeroEdge));
        assertThrows(IllegalArgumentException.class, () -> nodesEqEdges.execute(nConnected));
        assertThat(nodesEqEdges.execute(faceCounting)).isEqualTo(3);
        assertThat(nodesEqEdges.execute(difficultGraph)).isEqualTo(7);
    }
}