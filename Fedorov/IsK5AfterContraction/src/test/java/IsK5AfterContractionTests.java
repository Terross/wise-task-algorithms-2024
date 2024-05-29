import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class IsK5AfterContractionTests {

    @Test
    public void testK4() throws FileNotFoundException {
        IsK5AfterContraction isK5AfterContraction = new IsK5AfterContraction();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/k4.txt"));
        assertThat(isK5AfterContraction.execute(testGraph)).isFalse();
    }

    @Test
    public void testK5() throws FileNotFoundException {
        IsK5AfterContraction isK5AfterContraction = new IsK5AfterContraction();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/k5.txt"));
        assertThat(isK5AfterContraction.execute(testGraph)).isTrue();
    }

    @Test
    public void testTrue() throws FileNotFoundException {
        IsK5AfterContraction isK5AfterContraction = new IsK5AfterContraction();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/true.txt"));
        assertThat(isK5AfterContraction.execute(testGraph)).isTrue();
    }

    @Test
    public void testK5_1() throws FileNotFoundException {
        IsK5AfterContraction isK5AfterContraction = new IsK5AfterContraction();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/k5+1.txt"));
        assertThat(isK5AfterContraction.execute(testGraph)).isFalse();
    }

    @Test
    public void testBigTrue() throws FileNotFoundException {
        IsK5AfterContraction isK5AfterContraction = new IsK5AfterContraction();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/big_true.txt"));
        assertThat(isK5AfterContraction.execute(testGraph)).isTrue();
    }
}
