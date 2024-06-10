package ru.leti;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;

import com.mathsystem.api.graph.GraphFactory;
import com.mathsystem.api.graph.model.Graph;

public class TransitiveReductionsTest {
    @Test
    public void minimal_spanning_tree() throws RuntimeException {
        TransitiveReductions tr = new TransitiveReductions();

        for (File f : new File("src/test/resources/mst").listFiles()) {
            Graph graph = null;

            try {
                graph = GraphFactory.loadGraphFromFile(f);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            assertTrue(tr.execute(graph) == 1);
        }
    }

    @Test
    public void fully_connected_graph() throws RuntimeException {
        TransitiveReductions tr = new TransitiveReductions();
        Graph graph = null;

        try {
            graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/fc4.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        assertTrue(tr.execute(graph) == 19);
    }

    @Test
    public void partially_connected_graph() throws RuntimeException {
        TransitiveReductions tr = new TransitiveReductions();
        Graph graph = null;

        try {
            graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/pc4.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        assertTrue(tr.execute(graph) == 9);
    }

    @Test
    public void cyclic_graph() throws RuntimeException {
        TransitiveReductions tr = new TransitiveReductions();
        Graph graph = null;

        try {
            graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/nac4.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        assertTrue(tr.execute(graph) == 9);
    }

    @Test
    public void linear_graph() throws RuntimeException {
        TransitiveReductions tr = new TransitiveReductions();
        Graph graph = null;

        try {
            graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/linear3.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        assertTrue(tr.execute(graph) == 1);
    }

    @Test
    public void triangular_graph() throws RuntimeException {
        TransitiveReductions tr = new TransitiveReductions();
        Graph graph = null;

        try {
            graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/triangular3.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        assertTrue(tr.execute(graph) == 3);
    }
}
