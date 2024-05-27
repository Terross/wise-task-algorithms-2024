import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class blockTests{

    @Test
    public void Simple2Blocks() throws FileNotFoundException
    {
        BlockCount blockCounts = new BlockCount();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/Simple2Blocks.txt"));
        Integer result = blockCounts.execute(testGraph);
        assertThat(result).isEqualTo(2);
    }

    @Test
    public void threeBlocksTest() throws FileNotFoundException
    {
        BlockCount blockCounts = new BlockCount();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/3blocks.txt"));
        Integer result = blockCounts.execute(testGraph);
        assertThat(result).isEqualTo(3);
    }

    @Test
    public void fiveBlocksTest() throws FileNotFoundException {
        BlockCount blockCounts = new BlockCount();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/5blocks.txt"));
        Integer result = blockCounts.execute(testGraph);
        assertThat(result).isEqualTo(5);
    }

    @Test
    public void bigGraphTest() throws FileNotFoundException
    {
        BlockCount blockCounts = new BlockCount();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/bigGraph.txt"));
        Integer result = blockCounts.execute(testGraph);
        assertThat(result).isEqualTo(7);
    }

    @Test
    public void bridgeTest() throws FileNotFoundException
    {
        BlockCount blockCounts = new BlockCount();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/bridge.txt"));
        Integer result = blockCounts.execute(testGraph);
        assertThat(result).isEqualTo(4);
    }

    @Test
    public void listGraphTest() throws FileNotFoundException
    {
        BlockCount blockCounts = new BlockCount();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/listGraph.txt"));
        Integer result = blockCounts.execute(testGraph);
        assertThat(result).isEqualTo(5);
    }

}