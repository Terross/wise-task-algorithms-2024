package ru.leti;

import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class EdgeCriticalConnectedTest {

    @Test
    public void wikiTest() throws FileNotFoundException {
        EdgeCriticalConnected EdgeCriticalConnected = new EdgeCriticalConnected();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/" +
                "EdgeCriticalConnectedTestFiles/trueWikiGraph.txt"));
        var falseGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources" +
                "/EdgeCriticalConnectedTestFiles/falseWikiGraph.txt"));

        assertThat(EdgeCriticalConnected.execute(trueGraph)).isTrue();
        assertThat(EdgeCriticalConnected.execute(falseGraph)).isFalse();
    }

    @Test
    public void twoNodesTest() throws FileNotFoundException {
        EdgeCriticalConnected EdgeCriticalConnected = new EdgeCriticalConnected();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/" +
                "EdgeCriticalConnectedTestFiles/twoNodes.txt"));

        assertThat(EdgeCriticalConnected.execute(trueGraph)).isTrue();
    }

    @Test
    public void noEdgesTest() throws FileNotFoundException {
        EdgeCriticalConnected EdgeCriticalConnected = new EdgeCriticalConnected();
        var falseGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/EdgeCriticalConnectedTestFiles/noEdges.txt"));

        assertThat(EdgeCriticalConnected.execute(falseGraph)).isFalse();
    }

    @Test
    public void noConnectionTest() throws FileNotFoundException {
        EdgeCriticalConnected EdgeCriticalConnected = new EdgeCriticalConnected();
        var falseGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/" +
                "EdgeCriticalConnectedTestFiles/noConnection.txt"));

        assertThat(EdgeCriticalConnected.execute(falseGraph)).isFalse();
    }

    @Test
    public void eTest() throws FileNotFoundException {
        EdgeCriticalConnected EdgeCriticalConnected = new EdgeCriticalConnected();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/" +
                "EdgeCriticalConnectedTestFiles/eGraph.txt"));

        assertThat(EdgeCriticalConnected.execute(trueGraph)).isTrue();
    }

    @Test
    public void powerLineTest() throws FileNotFoundException {
        EdgeCriticalConnected EdgeCriticalConnected = new EdgeCriticalConnected();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/" +
                "EdgeCriticalConnectedTestFiles/powerLine.txt"));

        assertThat(EdgeCriticalConnected.execute(trueGraph)).isTrue();
    }

    @Test
    public void sanFranciscoTest() throws FileNotFoundException {
        EdgeCriticalConnected EdgeCriticalConnected = new EdgeCriticalConnected();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/" +
                "EdgeCriticalConnectedTestFiles/sanFrancisco.txt"));

        assertThat(EdgeCriticalConnected.execute(trueGraph)).isTrue();
    }

    @Test
    public void pyramidTest() throws FileNotFoundException {
        EdgeCriticalConnected EdgeCriticalConnected = new EdgeCriticalConnected();
        var falseGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/" +
                "EdgeCriticalConnectedTestFiles/pyramidGraph.txt"));

        assertThat(EdgeCriticalConnected.execute(falseGraph)).isFalse();
    }

    @Test
    public void unmarkedTest() throws FileNotFoundException {
        EdgeCriticalConnected EdgeCriticalConnected = new EdgeCriticalConnected();
        var falseGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/" +
                "EdgeCriticalConnectedTestFiles/unmarked.txt"));

        assertThat(EdgeCriticalConnected.execute(falseGraph)).isFalse();
    }
}