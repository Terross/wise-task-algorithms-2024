import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class CoveringVertexSetTest {

    @Test
    public void activateSimpleTest() throws FileNotFoundException {
        CoveringVertexSet coveringVertexSet = new CoveringVertexSet();
        var oneGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/type_one1187.txt"));
        var twoGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/type_two1187.txt"));


        assertThat(coveringVertexSet.execute(oneGraph)==1).isTrue();
        assertThat(coveringVertexSet.execute(twoGraph)!=2).isFalse();
    }
}
