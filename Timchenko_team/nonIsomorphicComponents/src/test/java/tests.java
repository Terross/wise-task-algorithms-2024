import com.mathsystem.api.graph.GraphFactory;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class tests {

    @Test
    public void SimpleYesTest() throws FileNotFoundException {
        NonIsomorphicComponents nonIsomorphicComponents = new NonIsomorphicComponents();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/три разных.txt"));

        assertThat(nonIsomorphicComponents.execute(trueGraph)).isTrue();
    }

    @Test
    public void VerySimpleYesTest() throws FileNotFoundException {
        NonIsomorphicComponents nonIsomorphicComponents = new NonIsomorphicComponents();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/два разных.txt"));

        assertThat(nonIsomorphicComponents.execute(trueGraph)).isTrue();
    }

    @Test
    public void SimpleNoTest() throws FileNotFoundException {
        NonIsomorphicComponents nonIsomorphicComponents = new NonIsomorphicComponents();
        var falseGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/два одинаковых один разный.txt"));

        assertThat(nonIsomorphicComponents.execute(falseGraph)).isFalse();
    }

    @Test
    public void VoidNoTest() throws FileNotFoundException {
        NonIsomorphicComponents nonIsomorphicComponents = new NonIsomorphicComponents();
        var falseGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/пустой.txt"));

        assertThat(nonIsomorphicComponents.execute(falseGraph)).isFalse();
    }


    @Test
    public void OneNoTest() throws FileNotFoundException {
        NonIsomorphicComponents nonIsomorphicComponents = new NonIsomorphicComponents();
        var falseGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/одиноные вершины.txt"));

        assertThat(nonIsomorphicComponents.execute(falseGraph)).isFalse();
    }

    @Test
    public void DifNTest() throws FileNotFoundException {
        NonIsomorphicComponents nonIsomorphicComponents = new NonIsomorphicComponents();
        var falseGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/разное количество.txt"));

        assertThat(nonIsomorphicComponents.execute(falseGraph)).isFalse();
    }
}