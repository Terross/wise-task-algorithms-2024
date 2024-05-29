import com.mathsystem.api.graph.GraphFactory;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class tests {

    @Test
    public void Yes() throws FileNotFoundException {
        k4subdivision nonIsomorphicComponents = new k4subdivision();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/subdiv.txt"));

        assertThat(nonIsomorphicComponents.execute(trueGraph)).isTrue();
    }

    @Test
    public void HardTest() throws FileNotFoundException {
        k4subdivision nonIsomorphicComponents = new k4subdivision();
        var trueGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/hardSubDiv.txt"));

        assertThat(nonIsomorphicComponents.execute(trueGraph)).isTrue();
    }

    @Test
    public void No() throws FileNotFoundException {
        k4subdivision nonIsomorphicComponents = new k4subdivision();
        var falseGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/noSubDiv.txt"));

        assertThat(nonIsomorphicComponents.execute(falseGraph)).isFalse();
    }

    @Test
    public void twoComp() throws FileNotFoundException {
        k4subdivision nonIsomorphicComponents = new k4subdivision();
        var falseGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/2comps.txt"));

        assertThat(nonIsomorphicComponents.execute(falseGraph)).isFalse();
    }

    @Test
    public void Void() throws FileNotFoundException {
        k4subdivision nonIsomorphicComponents = new k4subdivision();
        var falseGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/void.txt"));

        assertThat(nonIsomorphicComponents.execute(falseGraph)).isFalse();
    }
}