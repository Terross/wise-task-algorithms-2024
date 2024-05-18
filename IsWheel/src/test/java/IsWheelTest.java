import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class IsWheelTest {
    @Test
    public void testSmallGraphReturnsTrue() throws FileNotFoundException {
        IsWheel isWheel = new IsWheel();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/smallCorrectGraph4.txt"));
        assertThat(isWheel.execute(graph)).isTrue();
    }

    @Test
    public void testSmallGraphReturnsFalse() throws FileNotFoundException {
        IsWheel isWheel = new IsWheel();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/smallIncorrectGraph4.txt"));
        assertThat(isWheel.execute(graph)).isFalse();
    }

    @Test
    public void testNotEnoughVerReturnsFalse() throws FileNotFoundException {
        IsWheel isWheel = new IsWheel();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/notEnoughVerGraph.txt"));
        assertThat(isWheel.execute(graph)).isFalse();
    }

    @Test
    public void testIsEmptyReturnsFalse() throws FileNotFoundException {
        IsWheel isWheel = new IsWheel();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/isEmpty.txt"));
        assertThat(isWheel.execute(graph)).isFalse();
    }

    @Test
    public void testIsStarReturnsFalse() throws FileNotFoundException {
        IsWheel isWheel = new IsWheel();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/isStar.txt"));
        assertThat(isWheel.execute(graph)).isFalse();
    }

}