import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class SumDegVerGeqNumVer1Test {
    @Test
    public void testSmallUnDirGraphReturnsTrue() throws FileNotFoundException {
        SumDegVerGeqNumVer1 sdvgnv = new SumDegVerGeqNumVer1();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/smallUnDirCorrectGraph.txt"));
        assertThat(sdvgnv.execute(graph)).isTrue();
    }

    @Test
    public void testSmallUnDirGraphReturnsFalse() throws FileNotFoundException {
        SumDegVerGeqNumVer1 sdvgnv = new SumDegVerGeqNumVer1();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/smallUnDirIncorrectGraph.txt"));
        assertThat(sdvgnv.execute(graph)).isFalse();
    }

    @Test
    public void testSmallDirGraphReturnsTrue() throws FileNotFoundException {
        SumDegVerGeqNumVer1 sdvgnv = new SumDegVerGeqNumVer1();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/smallDirCorrectGraph.txt"));
        assertThat(sdvgnv.execute(graph)).isTrue();
    }

    @Test
    public void testSmallDirGraphReturnsFalse() throws FileNotFoundException {
        SumDegVerGeqNumVer1 sdvgnv = new SumDegVerGeqNumVer1();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/smallDirIncorrectGraph.txt"));
        assertThat(sdvgnv.execute(graph)).isFalse();
    }

    @Test
    public void testIsEmptyReturnsFalse() throws FileNotFoundException {
        SumDegVerGeqNumVer1 sdvgnv = new SumDegVerGeqNumVer1();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/isEmpty.txt"));
        assertThat(sdvgnv.execute(graph)).isFalse();
    }

}