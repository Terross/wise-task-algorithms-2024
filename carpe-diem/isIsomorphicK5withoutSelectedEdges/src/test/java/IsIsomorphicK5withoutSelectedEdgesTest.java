import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class IsIsomorphicK5withoutSelectedEdgesTest {

    @Test
    public void false1Test() throws FileNotFoundException {
        IsIsomorphicK5withoutSelectedEdges runObj = new IsIsomorphicK5withoutSelectedEdges();
        var falseGraph1 = GraphFactory.loadGraphFromFile(new File("src/test/resources/1_false.txt"));
        assertThat(runObj.execute(falseGraph1)).isFalse();
    }

    @Test
    public void false2Test() throws FileNotFoundException {
        IsIsomorphicK5withoutSelectedEdges runObj = new IsIsomorphicK5withoutSelectedEdges();
        var falseGraph2 = GraphFactory.loadGraphFromFile(new File("src/test/resources/2_false.txt"));
        assertThat(runObj.execute(falseGraph2)).isFalse();
    }

    @Test
    public void false3Test() throws FileNotFoundException {
        IsIsomorphicK5withoutSelectedEdges runObj = new IsIsomorphicK5withoutSelectedEdges();
        var falseGraph3 = GraphFactory.loadGraphFromFile(new File("src/test/resources/3_false.txt"));
        assertThat(runObj.execute(falseGraph3)).isFalse();
    }

    @Test
    public void false4Test() throws FileNotFoundException {
        IsIsomorphicK5withoutSelectedEdges runObj = new IsIsomorphicK5withoutSelectedEdges();
        var falseGraph4 = GraphFactory.loadGraphFromFile(new File("src/test/resources/4_false.txt"));
        assertThat(runObj.execute(falseGraph4)).isFalse();
    }

    @Test
    public void false5Test() throws FileNotFoundException {
        IsIsomorphicK5withoutSelectedEdges runObj = new IsIsomorphicK5withoutSelectedEdges();
        var falseGraph5 = GraphFactory.loadGraphFromFile(new File("src/test/resources/5_false.txt"));
        assertThat(runObj.execute(falseGraph5)).isFalse();
    }

    @Test
    public void false6Test() throws FileNotFoundException {
        IsIsomorphicK5withoutSelectedEdges runObj = new IsIsomorphicK5withoutSelectedEdges();
        var falseGraph6 = GraphFactory.loadGraphFromFile(new File("src/test/resources/6_false.txt"));
        assertThat(runObj.execute(falseGraph6)).isFalse();
    }

    @Test
    public void true1Test() throws FileNotFoundException {
        IsIsomorphicK5withoutSelectedEdges runObj = new IsIsomorphicK5withoutSelectedEdges();
        var trueGraph1 = GraphFactory.loadGraphFromFile(new File("src/test/resources/1_true.txt"));
        assertThat(runObj.execute(trueGraph1)).isTrue();
    }

    @Test
    public void true2Test() throws FileNotFoundException {
        IsIsomorphicK5withoutSelectedEdges runObj = new IsIsomorphicK5withoutSelectedEdges();
        var trueGraph2 = GraphFactory.loadGraphFromFile(new File("src/test/resources/2_true.txt"));
        assertThat(runObj.execute(trueGraph2)).isTrue();
    }
    @Test
    public void trueStar() throws FileNotFoundException {
        IsIsomorphicK5withoutSelectedEdges runObj = new IsIsomorphicK5withoutSelectedEdges();
        var trueGraph2 = GraphFactory.loadGraphFromFile(new File("src/test/resources/star.txt"));
        assertThat(runObj.execute(trueGraph2)).isTrue();
    }
    @Test
    public void falseStarWithoutEdge() throws FileNotFoundException {
        IsIsomorphicK5withoutSelectedEdges runObj = new IsIsomorphicK5withoutSelectedEdges();
        var trueGraph2 = GraphFactory.loadGraphFromFile(new File("src/test/resources/star_without_one_edge.txt"));
        assertThat(runObj.execute(trueGraph2)).isFalse();
    }
    @Test
    public void trueStarWithExtraVertex() throws FileNotFoundException {
        IsIsomorphicK5withoutSelectedEdges runObj = new IsIsomorphicK5withoutSelectedEdges();
        var trueGraph2 = GraphFactory.loadGraphFromFile(new File("src/test/resources/star_with_one_extra_vertex.txt"));
        assertThat(runObj.execute(trueGraph2)).isTrue();
    }
    @Test
    public void trueExtraVertexAndEdge() throws FileNotFoundException {
        IsIsomorphicK5withoutSelectedEdges runObj = new IsIsomorphicK5withoutSelectedEdges();
        var trueGraph2 = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph_with_extra_vertex_and_edge.txt"));
        assertThat(runObj.execute(trueGraph2)).isFalse();
    }
    @Test
    public void falseBigGraph() throws FileNotFoundException {
        IsIsomorphicK5withoutSelectedEdges runObj = new IsIsomorphicK5withoutSelectedEdges();
        var trueGraph2 = GraphFactory.loadGraphFromFile(new File("src/test/resources/false_big_graph.txt"));
        assertThat(runObj.execute(trueGraph2)).isFalse();
    }
    @Test
    public void trueBigGraph() throws FileNotFoundException {
        IsIsomorphicK5withoutSelectedEdges runObj = new IsIsomorphicK5withoutSelectedEdges();
        var trueGraph2 = GraphFactory.loadGraphFromFile(new File("src/test/resources/very_big_true_graph.txt"));
        assertThat(runObj.execute(trueGraph2)).isTrue();
    }
}
