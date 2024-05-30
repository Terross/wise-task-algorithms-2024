import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class ChromaticNumberEqualSixTest {

    @Test
    public void activateSimpleTest() throws FileNotFoundException {
        ChromaticNumberEqualSix ChromaticNumber = new ChromaticNumberEqualSix();
        var graph = GraphFactory.loadGraphFromFile(new File("C:\\Users\\Наталья\\IdeaProjects\\wise-task-algorithms-2024-main\\ChromaticNumberEqualSix\\src\\test\\resourses\\simple_test.txt"));
        assertThat(ChromaticNumber.execute(graph)).isTrue();
    }

    @Test
    public void emptyTest() throws FileNotFoundException {
        ChromaticNumberEqualSix ChromaticNumber = new ChromaticNumberEqualSix();
        var graph = GraphFactory.loadGraphFromFile(new File("C:\\Users\\Наталья\\IdeaProjects\\wise-task-algorithms-2024-main\\ChromaticNumberEqualSix\\src\\test\\resourses\\empty_test.txt"));
        assertThat(ChromaticNumber.execute(graph)).isFalse();
    }

    @Test
    public void randomTest() throws FileNotFoundException {
        ChromaticNumberEqualSix ChromaticNumber = new ChromaticNumberEqualSix();
        var graph = GraphFactory.loadGraphFromFile(new File("C:\\Users\\Наталья\\IdeaProjects\\wise-task-algorithms-2024-main\\ChromaticNumberEqualSix\\src\\test\\resourses\\random_test.txt"));
        assertThat(ChromaticNumber.execute(graph)).isFalse();
    }

    @Test
    public void disconnectedTest() throws FileNotFoundException {
        ChromaticNumberEqualSix ChromaticNumber = new ChromaticNumberEqualSix();
        var graph = GraphFactory.loadGraphFromFile(new File("C:\\Users\\Наталья\\IdeaProjects\\wise-task-algorithms-2024-main\\ChromaticNumberEqualSix\\src\\test\\resourses\\disconnected_test.txt"));
        assertThat(ChromaticNumber.execute(graph)).isTrue();
    }

    @Test
    public void petersenTest() throws FileNotFoundException {
        ChromaticNumberEqualSix ChromaticNumber = new ChromaticNumberEqualSix();
        var graph = GraphFactory.loadGraphFromFile(new File("C:\\Users\\Наталья\\IdeaProjects\\wise-task-algorithms-2024-main\\ChromaticNumberEqualSix\\src\\test\\resourses\\petersen_test.txt"));
        assertThat(ChromaticNumber.execute(graph)).isFalse();
    }

    @Test
    public void bigNegativeTest() throws FileNotFoundException {
        ChromaticNumberEqualSix ChromaticNumber = new ChromaticNumberEqualSix();
        var graph = GraphFactory.loadGraphFromFile(new File("C:\\Users\\Наталья\\IdeaProjects\\wise-task-algorithms-2024-main\\ChromaticNumberEqualSix\\src\\test\\resourses\\big_false_test.txt"));
        assertThat(ChromaticNumber.execute(graph)).isFalse();
    }

    @Test
    public void bigPositiveTest() throws FileNotFoundException {
        ChromaticNumberEqualSix ChromaticNumber = new ChromaticNumberEqualSix();
        var graph = GraphFactory.loadGraphFromFile(new File("C:\\Users\\Наталья\\IdeaProjects\\wise-task-algorithms-2024-main\\ChromaticNumberEqualSix\\src\\test\\resourses\\big_true_test.txt"));
        assertThat(ChromaticNumber.execute(graph)).isTrue();
    }

    @Test
    public void randomPositiveTest() throws FileNotFoundException {
        ChromaticNumberEqualSix ChromaticNumber = new ChromaticNumberEqualSix();
        var graph = GraphFactory.loadGraphFromFile(new File("C:\\Users\\Наталья\\IdeaProjects\\wise-task-algorithms-2024-main\\ChromaticNumberEqualSix\\src\\test\\resourses\\random_positive_test.txt"));
        assertThat(ChromaticNumber.execute(graph)).isTrue();
    }
}
