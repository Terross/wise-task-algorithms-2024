import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;
import ru.leti.IsGraphSquared;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class IsGraphSquaredTest {

    @Test
    public void activateSimpleTest() throws FileNotFoundException {
        IsGraphSquared dfs = new IsGraphSquared();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/small_false_graphs.txt"));
        //граф состоит из нетранзитивного треугольника, отдельно одна вершина и две вершины, связанные ребром.
        assertThat(dfs.execute(trueGraph)).isFalse();
    }

    @Test
    public void fakeTriangleTest() throws FileNotFoundException {
        IsGraphSquared dfs = new IsGraphSquared();
        var falseGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/fake_triangle.txt"));
        // 2 треугольника с отсутствием транзитивности + в остальном графе отсутствуют необходимые для квадрата рёбра.
        assertThat(dfs.execute(falseGraph)).isFalse();
    }

    @Test
    public void notAllTrianglesButTrueTest() throws FileNotFoundException {
        IsGraphSquared dfs = new IsGraphSquared();
        var falseGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/triangle_and_edge.txt"));
        // Квадрат графа, являющегося треугольником с ребром, выходящим из треугольника.
        assertThat(dfs.execute(falseGraph)).isTrue();
    }

    @Test
    public void twoWaysGraph() throws FileNotFoundException {
        IsGraphSquared dfs = new IsGraphSquared();
        var falseGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/False_graph_1.txt"));
        // нетранзитивный треугольник и недостаток одного ребра для того чтобы граф был квадратом
        assertThat(dfs.execute(falseGraph)).isFalse();
    }

    @Test
    public void easyFalse() throws FileNotFoundException {
        IsGraphSquared dfs = new IsGraphSquared();
        var falseGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/triangle_without_closure.txt"));
        // Треугольник, в котором 3 ребра, нет транзитивности (1->2->3->1)
        assertThat(dfs.execute(falseGraph)).isFalse();
    }

    @Test
    public void twoWaysChain() throws FileNotFoundException {
        IsGraphSquared dfs = new IsGraphSquared();
        var falseGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/bidirectional_line.txt"));
        // граф - линия из 4 вершин, все вершины связаны в в обе стороны с соседями.
        assertThat(dfs.execute(falseGraph)).isFalse();
    }

    @Test
    public void true_square_with_diagonals() throws FileNotFoundException {
        IsGraphSquared dfs = new IsGraphSquared();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/small_true.txt"));
        //граф - квадрат с двумя диагоналями
        assertThat(dfs.execute(trueGraph)).isTrue();
    }

    @Test
    public void false_square_withot_one_diagonal() throws FileNotFoundException {
        IsGraphSquared dfs = new IsGraphSquared();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/square_without_one_diagonal.txt"));
        //граф - квадрат с одной диагональю
        assertThat(dfs.execute(trueGraph)).isFalse();
    }

    @Test
    public void false_extra_edge() throws FileNotFoundException {
        IsGraphSquared dfs = new IsGraphSquared();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/false_extra_edge.txt"));
        // у графа лишнее ребро
        assertThat(dfs.execute(trueGraph)).isFalse();
    }

    @Test
    public void true_colored_graph() throws FileNotFoundException {
        IsGraphSquared dfs = new IsGraphSquared();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/true_colored_graph.txt"));
        // граф как в false_extra_edge но не без лишнего ребра
        assertThat(dfs.execute(trueGraph)).isTrue();
    }

    @Test
    public void true_graph_8() throws FileNotFoundException {
        IsGraphSquared dfs = new IsGraphSquared();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph_8.txt"));
        assertThat(dfs.execute(trueGraph)).isTrue();
    }

    @Test
    public void true_graph_9() throws FileNotFoundException {
        IsGraphSquared dfs = new IsGraphSquared();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph_9.txt"));
        assertThat(dfs.execute(trueGraph)).isTrue();
    }

    @Test
    public void true_graph_10() throws FileNotFoundException {
        IsGraphSquared dfs = new IsGraphSquared();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph_10.txt"));
        assertThat(dfs.execute(trueGraph)).isFalse();
    }
}
