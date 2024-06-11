import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;


public class SizeOfMinSetSeparatingVerticesTest {

    @Test
    public void undirectedGraph() throws FileNotFoundException {
        SizeOfMinSetSeparatingVertices sizeOfMinSetSeparatingVertices = new SizeOfMinSetSeparatingVertices();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/undirected_graph.txt"));
        Integer ans = 2;
        assertEquals(sizeOfMinSetSeparatingVertices.execute(graph), ans);
    }

    @Test
    public void directedGraph() throws FileNotFoundException {
        SizeOfMinSetSeparatingVertices sizeOfMinSetSeparatingVertices = new SizeOfMinSetSeparatingVertices();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/directed_graph.txt"));
        Integer ans = 1;
        assertEquals(sizeOfMinSetSeparatingVertices.execute(graph), ans);
    }

    @Test
    public void hardUndirectedGraph() throws FileNotFoundException {
        SizeOfMinSetSeparatingVertices sizeOfMinSetSeparatingVertices = new SizeOfMinSetSeparatingVertices();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/hard_undirected_graph.txt"));
        Integer ans = 4;
        assertEquals(sizeOfMinSetSeparatingVertices.execute(graph), ans);
    }

    @Test
    public void hardDirectedGraph() throws FileNotFoundException {
        SizeOfMinSetSeparatingVertices sizeOfMinSetSeparatingVertices = new SizeOfMinSetSeparatingVertices();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/hard_directed_graph.txt"));
        Integer ans = 2;
        assertEquals(sizeOfMinSetSeparatingVertices.execute(graph), ans);
    }

    @Test
    public void emptyCut() throws FileNotFoundException {
        SizeOfMinSetSeparatingVertices sizeOfMinSetSeparatingVertices = new SizeOfMinSetSeparatingVertices();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/empty_cut.txt"));
        Integer ans = 0;
        assertEquals(sizeOfMinSetSeparatingVertices.execute(graph), ans);
    }

    @Test
    public void task() throws FileNotFoundException {
        SizeOfMinSetSeparatingVertices sizeOfMinSetSeparatingVertices = new SizeOfMinSetSeparatingVertices();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/task.txt"));
        Integer ans = 2;
        assertEquals(ans, sizeOfMinSetSeparatingVertices.execute(graph));
    }

    @Test
    public void main_task() throws FileNotFoundException {
        SizeOfMinSetSeparatingVertices sizeOfMinSetSeparatingVertices = new SizeOfMinSetSeparatingVertices();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/main_task.txt"));
        Integer ans = 3;
        assertEquals(ans, sizeOfMinSetSeparatingVertices.execute(graph));
    }
}