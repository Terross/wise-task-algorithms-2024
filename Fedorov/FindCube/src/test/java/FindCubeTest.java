import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class FindCubeTest {

    @Test
    public void squareWithDiagonals() throws FileNotFoundException {
        FindCube FindCube = new FindCube();
        var squareWithDiagonals = GraphFactory.loadGraphFromFile(new File("src/test/resources/squareWithDiagonals.txt"));
        assertThat(FindCube.execute(squareWithDiagonals)).isTrue();
    }

    @Test
    public void squareWithDiagonalsAndTwoPoints() throws FileNotFoundException {
        FindCube FindCube = new FindCube();
        var squareWithDiagonalsAndTwoPoints = GraphFactory.loadGraphFromFile(new File("src/test/resources/squareWithDiagonalsAndTwoPoints.txt"));
        assertThat(FindCube.execute(squareWithDiagonalsAndTwoPoints)).isTrue();
    }

    @Test
    public void squareWithOneDiagonal() throws FileNotFoundException {
        FindCube FindCube = new FindCube();
        var squareWithOneDiagonal = GraphFactory.loadGraphFromFile(new File("src/test/resources/squareWithOneDiagonal.txt"));
        assertThat(FindCube.execute(squareWithOneDiagonal)).isFalse();
    }

    @Test
    public void falseGraph() throws FileNotFoundException {
        FindCube FindCube = new FindCube();
        var falseGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/falseGraph.txt"));
        assertThat(FindCube.execute(falseGraph)).isFalse();
    }

    @Test
    public void trueGraph() throws FileNotFoundException {
        FindCube FindCube = new FindCube();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/trueGraph.txt"));
        assertThat(FindCube.execute(trueGraph)).isFalse();
    }

    @Test
    public void twoVertThreeEdges() throws FileNotFoundException {
        FindCube FindCube = new FindCube();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/twoVertThreeEdges.txt"));
        assertThat(FindCube.execute(trueGraph)).isTrue();
    }

    @Test
    public void orientedTest() throws FileNotFoundException {
        FindCube FindCube = new FindCube();
        var orientedSquareWithDiagonals = GraphFactory.loadGraphFromFile(new File("src/test/resources/orientedSquareWithDiagonals.txt"));
        var orientedTwoVertThreeEdges = GraphFactory.loadGraphFromFile(new File("src/test/resources/orientedTwoVertThreeEdges.txt"));
        var orientedTrueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/orientedTrueGraph.txt"));
        var orientedFalseGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/orientedFalseGraph.txt"));

        assertThat(FindCube.execute(orientedSquareWithDiagonals)).isTrue();
        assertThat(FindCube.execute(orientedTwoVertThreeEdges)).isTrue();
        assertThat(FindCube.execute(orientedTrueGraph)).isTrue();
        assertThat(FindCube.execute(orientedFalseGraph)).isFalse();
    }
}