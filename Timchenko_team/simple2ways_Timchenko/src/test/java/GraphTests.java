import com.mathsystem.api.graph.GraphFactory;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.FileNotFoundException;
public class GraphTests {

    @Test
    void testGraph_false() {
        // Arrange
        Simple2ways Simple2ways = new Simple2ways();
        Graph graph = null;
        try {
            graph = GraphFactory.loadGraphFromFile(new File("src/main/resources/graph_false.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Act
        boolean res = Simple2ways.execute(graph);

        // Assert
        assertFalse(res, "false");
    }

    @Test
    void testGraph_false_cycle() {
        // Arrange
        Simple2ways Simple2ways = new Simple2ways();
        Graph graph = null;
        try {
            graph = GraphFactory.loadGraphFromFile(new File("src/main/resources/graph_false_cycle.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Act
        boolean res = Simple2ways.execute(graph);
        // Assert
        assertFalse(res, "false");
    }

    @Test
    void testGraph_false_full_plus_one() {
        // Arrange
        Graph graph = null;
        Simple2ways Simple2ways = new Simple2ways();
        try {
            graph = GraphFactory.loadGraphFromFile(new File("C:/Users/gidra/IdeaProjects/untitled7/src/main/resources/graph_false_full_plus_one.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Act
        boolean res = Simple2ways.execute(graph);

        // Assert
        assertFalse(res, "false");
    }

    @Test
    void testGraph_false_incoherent() {
        // Arrange
        Simple2ways Simple2ways = new Simple2ways();
        Graph graph = null;
        try {
            graph = GraphFactory.loadGraphFromFile(new File("C:/Users/gidra/IdeaProjects/untitled7/src/main/resources/graph_false_incoherent.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Act
        boolean res = Simple2ways.execute(graph);

        // Assert
        assertFalse(res, "false");
    }

    @Test
    void testGraph_false_incoherent_plus_cycle() {
        // Arrange
        Simple2ways Simple2ways = new Simple2ways();
        Graph graph = null;
        try {
            graph = GraphFactory.loadGraphFromFile(new File("C:/Users/gidra/IdeaProjects/untitled7/src/main/resources/graph_false_incoherent_plus_cycle.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Act
        boolean res = Simple2ways.execute(graph);

        // Assert
        assertFalse(res, "false");
    }

    @Test
    void testGraph_false_two_graphs() {
        // Arrange
        Simple2ways Simple2ways = new Simple2ways();
        Graph graph = null;
        try {
            graph = GraphFactory.loadGraphFromFile(new File("C:/Users/gidra/IdeaProjects/untitled7/src/main/resources/graph_false_two_graphs.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Act
        boolean res = Simple2ways.execute(graph);

        // Assert
        assertFalse(res, "false");
    }

    @Test
    void testGraph_false_two_strong_connection() {
        // Arrange
        Simple2ways Simple2ways = new Simple2ways();
        Graph graph = null;
        try {
            graph = GraphFactory.loadGraphFromFile(new File("C:/Users/gidra/IdeaProjects/untitled7/src/main/resources/graph_false_two_strong_connection.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Act
        boolean res = Simple2ways.execute(graph);

        // Assert
        assertFalse(res, "false");
    }

    @Test
    void testGraph_true_cycle() {
        // Arrange
        Simple2ways Simple2ways = new Simple2ways();
        Graph graph = null;
        try {
            graph = GraphFactory.loadGraphFromFile(new File("C:/Users/gidra/IdeaProjects/untitled7/src/main/resources/graph_true_cycle.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Act
        boolean res = Simple2ways.execute(graph);

        // Assert
        assertTrue(res, "true");
    }

    @Test
    void testGraph_true_full_graph() {
        // Arrange
        Simple2ways Simple2ways = new Simple2ways();
        Graph graph = null;
        try {
            graph = GraphFactory.loadGraphFromFile(new File("C:/Users/gidra/IdeaProjects/untitled7/src/main/resources/graph_true_full_graph.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Act
        boolean res = Simple2ways.execute(graph);

        // Assert
        assertTrue(res, "true");
    }

}
