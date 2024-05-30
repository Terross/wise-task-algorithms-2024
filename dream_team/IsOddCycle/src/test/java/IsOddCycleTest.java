import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class IsOddCycleTest {
    //    DIRECTED
    @Test
    public void DirSimpleTest() throws FileNotFoundException {
        IsOddCycle isOddCycle = new IsOddCycle();
        var first = GraphFactory.loadGraphFromFile(new File("src/main/resources/isOddCycle/directed/graph0.txt"));
        var second = GraphFactory.loadGraphFromFile(new File("src/main/resources/isOddCycle/directed/graph1.txt"));

        assertThat(isOddCycle.execute(first)).isTrue();
        assertThat(isOddCycle.execute(second)).isTrue();
    }

    @Test
    public void DirEvenCycleTest() throws FileNotFoundException {
        IsOddCycle isOddCycle = new IsOddCycle();
        var even_first = GraphFactory.loadGraphFromFile(new File("src/main/resources/isOddCycle/directed/even1.txt"));
        var even_second = GraphFactory.loadGraphFromFile(new File("src/main/resources/isOddCycle/directed/even2.txt"));

        assertThat(isOddCycle.execute(even_first)).isFalse();
        assertThat(isOddCycle.execute(even_second)).isFalse();
    }

    @Test
    public void DirNoEdges() throws FileNotFoundException {
        IsOddCycle isOddCycle = new IsOddCycle();
        var graph = GraphFactory.loadGraphFromFile(new File("src/main/resources/isOddCycle/directed/noEdges.txt"));

        assertThat(isOddCycle.execute(graph)).isFalse();
    }

    @Test
    public void WrongDirectionTest() throws FileNotFoundException {
        IsOddCycle isOddCycle = new IsOddCycle();
        var false_3 = GraphFactory.loadGraphFromFile(new File("src/main/resources/isOddCycle/directed/wrongDir1.txt"));
        var false_5 = GraphFactory.loadGraphFromFile(new File("src/main/resources/isOddCycle/directed/wrongDir2.txt"));

        assertThat(isOddCycle.execute(false_3)).isFalse();
        assertThat(isOddCycle.execute(false_5)).isFalse();
    }

    @Test
    public void DirNotCycleTest() throws FileNotFoundException {
        IsOddCycle isOddCycle = new IsOddCycle();
        var false_1 = GraphFactory.loadGraphFromFile(new File("src/main/resources/isOddCycle/directed/notCycle1.txt"));
        var lack = GraphFactory.loadGraphFromFile(new File("src/main/resources/isOddCycle/directed/notCycle2.txt"));

        assertThat(isOddCycle.execute(false_1)).isFalse();
        assertThat(isOddCycle.execute(lack)).isFalse();
    }

    @Test
    public void DirComponentsTest() throws FileNotFoundException {
        IsOddCycle isOddCycle = new IsOddCycle();
        var one = GraphFactory.loadGraphFromFile(new File("src/main/resources/isOddCycle/directed/one_component.txt"));
        var three = GraphFactory.loadGraphFromFile(new File("src/main/resources/isOddCycle/directed/three_components.txt"));

        assertThat(isOddCycle.execute(one)).isTrue();
        assertThat(isOddCycle.execute(three)).isFalse();
    }

    @Test
    public void DirComplexTest() throws FileNotFoundException {
        IsOddCycle isOddCycle = new IsOddCycle();
        var connector = GraphFactory.loadGraphFromFile(new File("src/main/resources/isOddCycle/directed/connector.txt"));
        var complex = GraphFactory.loadGraphFromFile(new File("src/main/resources/isOddCycle/directed/complex.txt"));

        assertThat(isOddCycle.execute(connector)).isTrue();
        assertThat(isOddCycle.execute(complex)).isTrue();
    }

    //    UNDIRECTED
    @Test
    public void UndSimpleTest() throws FileNotFoundException {
        IsOddCycle isOddCycle = new IsOddCycle();
        var first = GraphFactory.loadGraphFromFile(new File("src/main/resources/isOddCycle/undirected/graph0.txt"));
        var second = GraphFactory.loadGraphFromFile(new File("src/main/resources/isOddCycle/undirected/graph1.txt"));

        assertThat(isOddCycle.execute(first)).isTrue();
        assertThat(isOddCycle.execute(second)).isTrue();
    }

    @Test
    public void UndEvenCycleTest() throws FileNotFoundException {
        IsOddCycle isOddCycle = new IsOddCycle();
        var first = GraphFactory.loadGraphFromFile(new File("src/main/resources/isOddCycle/undirected/even1.txt"));
        var second = GraphFactory.loadGraphFromFile(new File("src/main/resources/isOddCycle/undirected/even2.txt"));
        var third = GraphFactory.loadGraphFromFile(new File("src/main/resources/isOddCycle/undirected/even3.txt"));

        assertThat(isOddCycle.execute(first)).isFalse();
        assertThat(isOddCycle.execute(second)).isFalse();
        assertThat(isOddCycle.execute(third)).isFalse();

    }

    @Test
    public void UndNoEdges() throws FileNotFoundException {
        IsOddCycle isOddCycle = new IsOddCycle();
        var graph = GraphFactory.loadGraphFromFile(new File("src/main/resources/isOddCycle/undirected/noEdges.txt"));

        assertThat(isOddCycle.execute(graph)).isFalse();
    }

    @Test
    public void UndNotCycleTest() throws FileNotFoundException {
        IsOddCycle isOddCycle = new IsOddCycle();
        var first = GraphFactory.loadGraphFromFile(new File("src/main/resources/isOddCycle/undirected/notCycle1.txt"));
        var second = GraphFactory.loadGraphFromFile(new File("src/main/resources/isOddCycle/undirected/notCycle2.txt"));
        var third = GraphFactory.loadGraphFromFile(new File("src/main/resources/isOddCycle/undirected/notCycle3.txt"));
        var fourth = GraphFactory.loadGraphFromFile(new File("src/main/resources/isOddCycle/undirected/notCycle4.txt"));
        var fifth = GraphFactory.loadGraphFromFile(new File("src/main/resources/isOddCycle/undirected/notCycle5.txt"));

        assertThat(isOddCycle.execute(first)).isFalse();
        assertThat(isOddCycle.execute(second)).isFalse();
        assertThat(isOddCycle.execute(third)).isFalse();
        assertThat(isOddCycle.execute(fourth)).isFalse();
        assertThat(isOddCycle.execute(fifth)).isFalse();
    }

    @Test
    public void UndComponentsTest() throws FileNotFoundException {
        IsOddCycle isOddCycle = new IsOddCycle();
        var two_one = GraphFactory.loadGraphFromFile(new File("src/main/resources/isOddCycle/undirected/two_components_correct.txt"));
        var two_two = GraphFactory.loadGraphFromFile(new File("src/main/resources/isOddCycle/undirected/two_components_wrong.txt"));

        assertThat(isOddCycle.execute(two_one)).isTrue();
        assertThat(isOddCycle.execute(two_two)).isFalse();
    }

    @Test
    public void UndComplexTest() throws FileNotFoundException {
        IsOddCycle isOddCycle = new IsOddCycle();
        var connector = GraphFactory.loadGraphFromFile(new File("src/main/resources/isOddCycle/undirected/connector.txt"));
        var complex = GraphFactory.loadGraphFromFile(new File("src/main/resources/isOddCycle/undirected/complex.txt"));

        assertThat(isOddCycle.execute(connector)).isTrue();
        assertThat(isOddCycle.execute(complex)).isTrue();
    }

    @Test
    public void FlowerTest() throws FileNotFoundException {
        IsOddCycle isOddCycle = new IsOddCycle();
        var flower = GraphFactory.loadGraphFromFile(new File("src/main/resources/isOddCycle/undirected/big_flower_correct.txt"));
        var wrong = GraphFactory.loadGraphFromFile(new File("src/main/resources/isOddCycle/undirected/big_flower_wrong.txt"));

        assertThat(isOddCycle.execute(flower)).isTrue();
        assertThat(isOddCycle.execute(wrong)).isFalse();
    }
}