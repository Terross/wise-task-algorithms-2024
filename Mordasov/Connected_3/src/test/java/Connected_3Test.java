import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class Connected_3Test {

    @Test
    public void activateSimpleTest() throws FileNotFoundException {
        // Connected_3 connected_3 = new Connected_3();
        // var trueGraph = GraphFactory.loadGraphFromFile(new File("D:\\work\\java\\ktig2\\Connected_3\\src\\test\\resources\\trueGraph.txt"));
        // var falseGraph = GraphFactory.loadGraphFromFile(new File("D:\\work\\java\\ktig2\\Connected_3\\src\\test\\resources\\falseGraph.txt"));
        // assertThat(connected_3.execute(trueGraph)).isTrue();
        // assertThat(connected_3.execute(falseGraph)).isFalse();
        
        Connected_3 connected_3 = new Connected_3();
        var graph1 = GraphFactory.loadGraphFromFile(new File("D:\\work\\java\\Connected_3\\Connected_3\\src\\test\\resources\\graph1.txt"));
        var graph2 = GraphFactory.loadGraphFromFile(new File("D:\\work\\java\\Connected_3\\Connected_3\\src\\test\\resources\\graph2.txt"));
        var graph3 = GraphFactory.loadGraphFromFile(new File("D:\\work\\java\\Connected_3\\Connected_3\\src\\test\\resources\\graph3.txt"));
        var graph4 = GraphFactory.loadGraphFromFile(new File("D:\\work\\java\\Connected_3\\Connected_3\\src\\test\\resources\\graph4.txt"));
        var graph5 = GraphFactory.loadGraphFromFile(new File("D:\\work\\java\\Connected_3\\Connected_3\\src\\test\\resources\\graph5.txt"));
        var graph6 = GraphFactory.loadGraphFromFile(new File("D:\\work\\java\\Connected_3\\Connected_3\\src\\test\\resources\\graph6.txt"));
        var graph7 = GraphFactory.loadGraphFromFile(new File("D:\\work\\java\\Connected_3\\Connected_3\\src\\test\\resources\\graph7.txt"));
        var graph8 = GraphFactory.loadGraphFromFile(new File("D:\\work\\java\\Connected_3\\Connected_3\\src\\test\\resources\\graph8.txt"));
        var graph9 = GraphFactory.loadGraphFromFile(new File("D:\\work\\java\\Connected_3\\Connected_3\\src\\test\\resources\\graph9.txt"));
        var graph10 = GraphFactory.loadGraphFromFile(new File("D:\\work\\java\\Connected_3\\Connected_3\\src\\test\\resources\\graph10.txt"));
        var graph11 = GraphFactory.loadGraphFromFile(new File("D:\\work\\java\\Connected_3\\Connected_3\\src\\test\\resources\\graph11.txt"));
        var graph12 = GraphFactory.loadGraphFromFile(new File("D:\\work\\java\\Connected_3\\Connected_3\\src\\test\\resources\\graph12.txt"));
        var graph13 = GraphFactory.loadGraphFromFile(new File("D:\\work\\java\\Connected_3\\Connected_3\\src\\test\\resources\\graph13.txt"));
        assertThat(connected_3.execute(graph1)).isTrue();
        assertThat(connected_3.execute(graph2)).isTrue();
        assertThat(connected_3.execute(graph3)).isTrue();
        assertThat(connected_3.execute(graph4)).isTrue();
        assertThat(connected_3.execute(graph5)).isFalse();
        assertThat(connected_3.execute(graph6)).isFalse();
        assertThat(connected_3.execute(graph7)).isFalse();
        assertThat(connected_3.execute(graph8)).isFalse();
        assertThat(connected_3.execute(graph9)).isFalse();
        assertThat(connected_3.execute(graph10)).isFalse();
        assertThat(connected_3.execute(graph11)).isFalse();
        assertThat(connected_3.execute(graph12)).isFalse();
        assertThat(connected_3.execute(graph13)).isFalse();

    }
}
