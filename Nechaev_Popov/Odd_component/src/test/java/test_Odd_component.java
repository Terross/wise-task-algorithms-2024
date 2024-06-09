import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class test_Odd_component {

    @Test
    public void activateSimpleTest() throws FileNotFoundException {
        Odd_component components = new Odd_component();
        var Graph1_2 = GraphFactory.loadGraphFromFile(new File("src/test/java/org/example/testComponent/graph1_2.txt"));
        var Graph2_3 = GraphFactory.loadGraphFromFile(new File("src/test/java/org/example/testComponent/graph2_3.txt"));
        var Graph3_1 = GraphFactory.loadGraphFromFile(new File("src/test/java/org/example/testComponent/graph3_1.txt"));
        var Graph4_3 = GraphFactory.loadGraphFromFile(new File("src/test/java/org/example/testComponent/graph4_3.txt"));
        var Graph4_0 = GraphFactory.loadGraphFromFile(new File("src/test/java/org/example/testComponent/graph4_0.txt"));
        var Graph5_9 = GraphFactory.loadGraphFromFile(new File("src/test/java/org/example/testComponent/graph5_9.txt"));
        var Graph6_0 = GraphFactory.loadGraphFromFile(new File("src/test/java/org/example/testComponent/graph6_0.txt"));
        var Graph7_2 = GraphFactory.loadGraphFromFile(new File("src/test/java/org/example/testComponent/graph7_2.txt"));
        var Graph8_1 = GraphFactory.loadGraphFromFile(new File("src/test/java/org/example/testComponent/graph8_1.txt"));



        assertThat(components.execute(Graph1_2) == 2);
        assertThat(components.execute(Graph2_3) == 3);
        assertThat(components.execute(Graph3_1) == 1);
        assertThat(components.execute(Graph4_3) == 3);
        assertThat(components.execute(Graph4_0) == 0);
        assertThat(components.execute(Graph5_9) == 9);
        assertThat(components.execute(Graph6_0) == 0);
        assertThat(components.execute(Graph7_2) == 2);
        assertThat(components.execute(Graph8_1) == 1);

    }
}