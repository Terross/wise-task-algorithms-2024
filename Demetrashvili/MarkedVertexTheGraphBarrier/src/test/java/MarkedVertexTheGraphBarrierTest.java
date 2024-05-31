import com.mathsystem.api.graph.GraphFactory;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.FileNotFoundException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;



public class MarkedVertexTheGraphBarrierTest {

    @Test
    public void activateSimpleTest() throws FileNotFoundException {
        MarkedVertexTheGraphBarrier graphBarrier = new MarkedVertexTheGraphBarrier();
        var GraphLine = GraphFactory.loadGraphFromFile(new File("src/test/java/resources/barrier/GraphLineFalse.txt"));
        var GraphNet = GraphFactory.loadGraphFromFile(new File("src/test/java/resources/barrier/GraphNetTrue.txt"));
        var GraphNetLackVert = GraphFactory.loadGraphFromFile(new File("src/test/java/resources/barrier/GraphNetLackVertFalse.txt"));
        var GraphNetOverVert = GraphFactory.loadGraphFromFile(new File("src/test/java/resources/barrier/GraphNetOverVertFalse.txt"));
        var GraphAnother = GraphFactory.loadGraphFromFile(new File("src/test/java/resources/barrier/GraphAnother.txt"));

        assertThat(graphBarrier.execute(GraphLine)).isFalse();
        assertThat(graphBarrier.execute(GraphNet)).isTrue();
        assertThat(graphBarrier.execute(GraphNetLackVert)).isFalse();
        assertThat(graphBarrier.execute(GraphNetOverVert)).isFalse();
        assertThat(graphBarrier.execute(GraphAnother)).isTrue();

    }
}