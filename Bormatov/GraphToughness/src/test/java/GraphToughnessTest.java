package ru.leti;

import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GraphToughnessTest{

    @Test
    public void testFirst() throws FileNotFoundException
    {
        GraphToughness graphToughness = new GraphToughness();
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph1.txt"));
        Integer result = graphToughness.execute(testGraph);
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void testSecond() throws FileNotFoundException
    {
        GraphToughness graphToughness = new GraphToughness();
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph2.txt"));
        Integer result = graphToughness.execute(testGraph);
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void testThird() throws FileNotFoundException
    {
        GraphToughness graphToughness = new GraphToughness();
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph3.txt"));
        Integer result = graphToughness.execute(testGraph);
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void testFourth() throws FileNotFoundException
    {
        GraphToughness graphToughness = new GraphToughness();
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph4.txt"));
        Integer result = graphToughness.execute(testGraph);
        assertThat(result).isEqualTo(2);
    }
}