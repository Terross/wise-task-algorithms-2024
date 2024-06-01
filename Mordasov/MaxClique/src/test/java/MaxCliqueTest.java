import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class MaxCliqueTest {

    @Test
    public void activateSimpleTest() throws FileNotFoundException {
        MaxClique max_clique = new MaxClique();
        var graph_1 = GraphFactory.loadGraphFromFile(new File("D:\\work\\java\\MaxClique\\MaxClique\\src\\test\\resources\\graph1.txt"));
        var graph_2 = GraphFactory.loadGraphFromFile(new File("D:\\work\\java\\MaxClique\\MaxClique\\src\\test\\resources\\graph2.txt"));
        var graph_3 = GraphFactory.loadGraphFromFile(new File("D:\\work\\java\\MaxClique\\MaxClique\\src\\test\\resources\\graph3.txt"));
        var graph_4 = GraphFactory.loadGraphFromFile(new File("D:\\work\\java\\MaxClique\\MaxClique\\src\\test\\resources\\graph4.txt"));
        var graph_5 = GraphFactory.loadGraphFromFile(new File("D:\\work\\java\\MaxClique\\MaxClique\\src\\test\\resources\\graph5.txt"));
        var graph_6 = GraphFactory.loadGraphFromFile(new File("D:\\work\\java\\MaxClique\\MaxClique\\src\\test\\resources\\graph6.txt"));
        var graph_7 = GraphFactory.loadGraphFromFile(new File("D:\\work\\java\\MaxClique\\MaxClique\\src\\test\\resources\\graph7.txt"));
        var graph_8 = GraphFactory.loadGraphFromFile(new File("D:\\work\\java\\MaxClique\\MaxClique\\src\\test\\resources\\graph8.txt"));
        var graph_9 = GraphFactory.loadGraphFromFile(new File("D:\\work\\java\\MaxClique\\MaxClique\\src\\test\\resources\\graph9.txt"));
        var graph_10 = GraphFactory.loadGraphFromFile(new File("D:\\work\\java\\MaxClique\\MaxClique\\src\\test\\resources\\graph10.txt"));
        var graph_11 = GraphFactory.loadGraphFromFile(new File("D:\\work\\java\\MaxClique\\MaxClique\\src\\test\\resources\\graph11.txt"));
        var graph_12 = GraphFactory.loadGraphFromFile(new File("D:\\work\\java\\MaxClique\\MaxClique\\src\\test\\resources\\graph12.txt"));
        var graph_13 = GraphFactory.loadGraphFromFile(new File("D:\\work\\java\\MaxClique\\MaxClique\\src\\test\\resources\\graph13.txt"));

        assertThat(max_clique.execute(graph_1)).isEqualTo(6);
        assertThat(max_clique.execute(graph_2)).isEqualTo(5);
        assertThat(max_clique.execute(graph_3)).isEqualTo(4);
        assertThat(max_clique.execute(graph_4)).isEqualTo(3);
        assertThat(max_clique.execute(graph_5)).isEqualTo(2);
        assertThat(max_clique.execute(graph_6)).isEqualTo(4);
        assertThat(max_clique.execute(graph_7)).isEqualTo(4);
        assertThat(max_clique.execute(graph_8)).isEqualTo(3);
        assertThat(max_clique.execute(graph_9)).isEqualTo(3);
        assertThat(max_clique.execute(graph_10)).isEqualTo(2);
        assertThat(max_clique.execute(graph_11)).isEqualTo(2);
        assertThat(max_clique.execute(graph_12)).isEqualTo(2);
        assertThat(max_clique.execute(graph_13)).isEqualTo(2);
        
    }
}
