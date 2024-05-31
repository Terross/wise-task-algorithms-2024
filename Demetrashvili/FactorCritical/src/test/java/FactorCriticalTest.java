import com.mathsystem.api.graph.GraphFactory;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.FileNotFoundException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;



class FactorCriticalTest {

    @Test
    public void activateSimpleTest() throws FileNotFoundException {
        FactorCritical test = new FactorCritical();
        var vertex5_edge5 = GraphFactory.loadGraphFromFile(new File("src/test/resources/vertex5_edge5.txt"));
        var triangle = GraphFactory.loadGraphFromFile(new File("src/test/resources/triangle.txt"));
        var vertex7_edge9 = GraphFactory.loadGraphFromFile(new File("src/test/resources/vertex7_edge9.txt"));
        var hourglass = GraphFactory.loadGraphFromFile(new File("src/test/resources/hourglass.txt"));
        var vertex8_edge9 = GraphFactory.loadGraphFromFile(new File("src/test/resources/vertex8_edge9.txt"));
        assertThat(test.execute(vertex5_edge5)).isEqualTo(true);
        assertThat(test.execute(triangle)).isEqualTo(true);
        assertThat(test.execute(vertex7_edge9)).isEqualTo(true);
        assertThat(test.execute(hourglass)).isEqualTo(true);
        assertThat(test.execute(vertex8_edge9)).isEqualTo(false);
    }
    void execute() {
    }
}