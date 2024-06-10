package ru.leti;

import org.junit.Test;

import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TriangleDegreeThreeCheckerTest {
    private final TriangleDegreeThreeChecker threeChecker;

    public TriangleDegreeThreeCheckerTest() {
        threeChecker = new TriangleDegreeThreeChecker();
    }

    @Test
    public void emptyGraphTest() throws FileNotFoundException {
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/TriangleDegreeThreeCheckerTestFiles/EmptyGraph.txt"));
        assertThat(threeChecker.execute(trueGraph)).isTrue();
    }

    @Test
    public void oneNodeGraphTest() throws FileNotFoundException {
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/TriangleDegreeThreeCheckerTestFiles/OneNode.txt"));
        assertThat(threeChecker.execute(trueGraph)).isTrue();
    }

    @Test
    public void twoNodeGraphTest() throws FileNotFoundException {
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/TriangleDegreeThreeCheckerTestFiles/TwoNodes.txt"));
        assertThat(threeChecker.execute(trueGraph)).isTrue();
    }

    @Test
    public void oneTriangleGraphTest() throws FileNotFoundException {
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/TriangleDegreeThreeCheckerTestFiles/OneTriangle.txt"));
        assertThat(threeChecker.execute(trueGraph)).isFalse();
    }

    @Test
    public void oneTriangle3OutputsGraphTest() throws FileNotFoundException {
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/TriangleDegreeThreeCheckerTestFiles/OneTriangle3Outputs.txt"));
        assertThat(threeChecker.execute(trueGraph)).isTrue();
    }

    @Test
    public void twoTriangle1OutputGraphTest() throws FileNotFoundException {
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/TriangleDegreeThreeCheckerTestFiles/TwoTriangle1Output.txt"));
        assertThat(threeChecker.execute(trueGraph)).isTrue();
    }

    @Test
    public void butterflyGraphTest() throws FileNotFoundException {
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/TriangleDegreeThreeCheckerTestFiles/Butterfly.txt"));
        assertThat(threeChecker.execute(trueGraph)).isFalse();
    }

    @Test
    public void tieStarfighterGraphTest() throws FileNotFoundException {
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/TriangleDegreeThreeCheckerTestFiles/TieStarfighter.txt"));
        assertThat(threeChecker.execute(trueGraph)).isTrue();
    }
    @Test
    public void Cycle4GraphTest() throws FileNotFoundException {
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/TriangleDegreeThreeCheckerTestFiles/Cycle4.txt"));
        assertThat(threeChecker.execute(trueGraph)).isTrue();
    }
}
