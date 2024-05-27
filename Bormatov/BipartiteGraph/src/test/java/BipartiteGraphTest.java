package ru.leti;

import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BipartiteGraphTest{

    @Test
    public void firstTest() throws FileNotFoundException {
        BipartiteGraph bipartiteGraphCharacteristic = new BipartiteGraph();
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph1.txt"));
        boolean result = bipartiteGraphCharacteristic.execute(testGraph);
        assertThat(result).isEqualTo(true);
    }

    @Test
    public void secondTest() throws FileNotFoundException {
        BipartiteGraph bipartiteGraphCharacteristic = new BipartiteGraph();
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph2.txt"));
        boolean result = bipartiteGraphCharacteristic.execute(testGraph);
        assertThat(result).isEqualTo(false);
    }

    @Test
    public void thirdTest() throws FileNotFoundException {
        BipartiteGraph bipartiteGraphCharacteristic = new BipartiteGraph();
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph3.txt"));
        boolean result = bipartiteGraphCharacteristic.execute(testGraph);
        assertThat(result).isEqualTo(false);
    }

    @Test
    public void FourthTest() throws FileNotFoundException {
        BipartiteGraph bipartiteGraphCharacteristic = new BipartiteGraph();
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph4.txt"));
        boolean result = bipartiteGraphCharacteristic.execute(testGraph);
        assertThat(result).isEqualTo(false);
    }

    @Test
    public void FifthTest() throws FileNotFoundException {
        BipartiteGraph bipartiteGraphCharacteristic = new BipartiteGraph();
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph5.txt"));
        boolean result = bipartiteGraphCharacteristic.execute(testGraph);
        assertThat(result).isEqualTo(true);
    }

}