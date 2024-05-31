import com.mathsystem.api.graph.GraphFactory;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.FileNotFoundException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;



import static org.junit.jupiter.api.Assertions.*;

class NumberOfCliquesTest {

    @Test
    public void activateSimpleTest() throws FileNotFoundException {
        NumberOfCliques test = new NumberOfCliques();
        var full_4vertex = GraphFactory.loadGraphFromFile(new File("src/test/java/resources/clique/full_4vertex.txt"));
        var full_5vertex = GraphFactory.loadGraphFromFile(new File("src/test/java/resources/clique/full_5vertex.txt"));
        var vert5_edge6 = GraphFactory.loadGraphFromFile(new File("src/test/java/resources/clique/vert5_edge6.txt"));
        var empty_g = GraphFactory.loadGraphFromFile(new File("src/test/java/resources/clique/empty_g.txt"));
        var directed_graph = GraphFactory.loadGraphFromFile(new File("src/test/java/resources/clique/directed_graph.txt"));
        assertThat(test.execute(full_4vertex)).isEqualTo(15);
        assertThat(test.execute(full_5vertex)).isEqualTo(31);
        assertThat(test.execute(vert5_edge6)).isEqualTo(12);
        assertThrows(IllegalArgumentException.class, () -> test.execute(empty_g));
        assertThrows(IllegalArgumentException.class, () -> test.execute(directed_graph));
    }
    void execute() {
    }
}