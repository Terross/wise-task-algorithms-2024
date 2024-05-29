import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class deg_v_evenTest {
    @Test
    public void testUndirTrue() throws FileNotFoundException {
        deg_v_even deg = new deg_v_even();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graphUndirTrue.txt"));
        assertThat(deg.execute(graph)).isTrue();
    }
    @Test
    public void testUndirFalse() throws FileNotFoundException {
        deg_v_even deg = new deg_v_even();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graphUndirFalse.txt"));
        assertThat(deg.execute(graph)).isFalse();
    }
    @Test
    public void testDirTrue() throws FileNotFoundException {
        deg_v_even deg = new deg_v_even();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graphDirTrue.txt"));
        assertThat(deg.execute(graph)).isTrue();
    }
    @Test
    public void testDirFalse() throws FileNotFoundException {
        deg_v_even deg = new deg_v_even();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graphDirFalse.txt"));
        assertThat(deg.execute(graph)).isFalse();
    }
    @Test
    public void testEmpty() throws FileNotFoundException {
        deg_v_even deg = new deg_v_even();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graphEmpty.txt"));
        assertThat(deg.execute(graph)).isTrue();
    }
    @Test
    public void testOneVertex() throws FileNotFoundException {
        deg_v_even deg = new deg_v_even();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graphOneVertex.txt"));
        assertThat(deg.execute(graph)).isTrue();
    }
    @Test
    public void testMixedFalse() throws FileNotFoundException {
        deg_v_even deg = new deg_v_even();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graphMixedFalse.txt"));
        assertThat(deg.execute(graph)).isFalse();
    }
    @Test
    public void testMixedTrue() throws FileNotFoundException {
        deg_v_even deg = new deg_v_even();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graphMixedTrue.txt"));
        assertThat(deg.execute(graph)).isTrue();
    }
}

