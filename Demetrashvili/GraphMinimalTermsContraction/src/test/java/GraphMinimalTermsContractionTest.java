import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class GraphMinimalTermsContractionTest {

    @Test
    public void activateSimpleTest() throws FileNotFoundException {
        GraphMinimalTermsContraction nodesEqEdges = new GraphMinimalTermsContraction();

        var negativeWight = GraphFactory.loadGraphFromFile(new File("src/test/java/resources/negativeWeight.txt"));
        var notConnected = GraphFactory.loadGraphFromFile(new File("src/test/java/resources/notConnected.txt"));
        var notWeight = GraphFactory.loadGraphFromFile(new File("src/test/java/resources/notWeight.txt"));
        var test1 = GraphFactory.loadGraphFromFile(new File("src/test/java/resources/test1.txt"));
        var test2 = GraphFactory.loadGraphFromFile(new File("src/test/java/resources/test2.txt"));
        var test3 = GraphFactory.loadGraphFromFile(new File("src/test/java/resources/test3.txt"));

        assertThrows(IllegalArgumentException.class, () -> nodesEqEdges.execute(negativeWight));
        assertThrows(IllegalArgumentException.class, () -> nodesEqEdges.execute(notConnected));
        assertThrows(IllegalArgumentException.class, () -> nodesEqEdges.execute(notWeight));
        assertThat(nodesEqEdges.execute(test1)).isEqualTo(12);
        assertThat(nodesEqEdges.execute(test2)).isEqualTo(33);
        assertThat(nodesEqEdges.execute(test3)).isEqualTo(32);
    }
}