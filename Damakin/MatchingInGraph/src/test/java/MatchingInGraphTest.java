import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MatchingInGraphTest {

    @Test
    public void Graph1() throws FileNotFoundException {
        MatchingInGraph matchingInGraph = new MatchingInGraph();
        var Graph1 = GraphFactory.loadGraphFromFile(new File("src/test/resources/Graph1.txt"));
        assertThat(matchingInGraph.execute(Graph1) == 0).isTrue();
    }
    @Test
    public void Graph2() throws FileNotFoundException {
        MatchingInGraph matchingInGraph = new MatchingInGraph();
        var Graph2 = GraphFactory.loadGraphFromFile(new File("src/test/resources/Graph2.txt"));
        assertThat(matchingInGraph.execute(Graph2) == 36).isTrue();
    }
    @Test
    public void Graph3() throws FileNotFoundException {
        MatchingInGraph matchingInGraph = new MatchingInGraph();
        var Graph3 = GraphFactory.loadGraphFromFile(new File("src/test/resources/Graph3.txt"));
        assertThat(matchingInGraph.execute(Graph3) == 0).isTrue();
    }
    @Test
    public void Graph4() throws FileNotFoundException {
        MatchingInGraph matchingInGraph = new MatchingInGraph();
        var Graph4 = GraphFactory.loadGraphFromFile(new File("src/test/resources/Graph4.txt"));
        assertThat(matchingInGraph.execute(Graph4) == 4).isTrue();
    }
    @Test
    public void Graph5() throws FileNotFoundException {
        MatchingInGraph matchingInGraph = new MatchingInGraph();
        var Graph5 = GraphFactory.loadGraphFromFile(new File("src/test/resources/Graph5.txt"));
        assertThat(matchingInGraph.execute(Graph5) == 1).isTrue();
    }

}