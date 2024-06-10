import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class BlockCheckTest {
    @Test
    public void activateSimpleTest() throws FileNotFoundException {
        BlockCheck blockCheck = new BlockCheck();
        var singlevertex = GraphFactory.loadGraphFromFile(new File("src/test/resources/singlevertex.txt"));
        var twovertices = GraphFactory.loadGraphFromFile(new File("src/test/resources/twovertices.txt"));
        var disconnectedgraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/disconnectedgraph.txt"));
        var nonmaximumblock = GraphFactory.loadGraphFromFile(new File("src/test/resources/non-maximumblock.txt"));
        var articulationpointblock = GraphFactory.loadGraphFromFile(new File("src/test/resources/articulationpointblock.txt"));
        var largegraphblock = GraphFactory.loadGraphFromFile(new File("src/test/resources/largegraphblock.txt"));
        var twolargegraphblocks = GraphFactory.loadGraphFromFile(new File("src/test/resources/twolargegraphblocks.txt"));
        var graphbridge = GraphFactory.loadGraphFromFile(new File("src/test/resources/graphbridge.txt"));
        var adjacentblocks = GraphFactory.loadGraphFromFile(new File("src/test/resources/adjacentblocks.txt"));
        var acompletelyredgraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/acompletelyredgraph.txt"));

        assertThat(blockCheck.execute(singlevertex)).isTrue();
        assertThat(blockCheck.execute(twovertices)).isTrue();
        assertThat(blockCheck.execute(disconnectedgraph)).isFalse();
        assertThat(blockCheck.execute(nonmaximumblock)).isFalse();
        assertThat(blockCheck.execute(articulationpointblock)).isFalse();
        assertThat(blockCheck.execute(largegraphblock)).isTrue();
        assertThat(blockCheck.execute(twolargegraphblocks)).isFalse();
        assertThat(blockCheck.execute(graphbridge)).isTrue();
        assertThat(blockCheck.execute(adjacentblocks)).isFalse();
        assertThat(blockCheck.execute(acompletelyredgraph)).isFalse();
    }
}