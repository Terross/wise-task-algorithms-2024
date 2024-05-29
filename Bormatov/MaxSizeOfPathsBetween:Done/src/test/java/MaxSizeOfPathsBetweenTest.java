import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;
import ru.leti.MaxSizeOfPathsBetween;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
public class MaxSizeOfPathsBetweenTest {
    @Test
    public void One_Path() throws FileNotFoundException{
        MaxSizeOfPathsBetween path1 = new MaxSizeOfPathsBetween();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph1.txt"));
        Integer result = path1.execute(graph);
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void Near_not_task() throws FileNotFoundException{
        MaxSizeOfPathsBetween path2 = new MaxSizeOfPathsBetween();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph2.txt"));
        Integer result = path2.execute(graph);
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void Test3() throws FileNotFoundException{
        MaxSizeOfPathsBetween path3 = new MaxSizeOfPathsBetween();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph3.txt"));
        Integer result = path3.execute(graph);
        assertThat(result).isEqualTo(3);
    }

    @Test
    public void Test3_hard() throws FileNotFoundException{
        MaxSizeOfPathsBetween path4 = new MaxSizeOfPathsBetween();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph4.txt"));
        Integer result = path4.execute(graph);
        assertThat(result).isEqualTo(3);
    }

    @Test
    public void All_paths_from_one() throws FileNotFoundException{
        MaxSizeOfPathsBetween path5 = new MaxSizeOfPathsBetween();
        var graph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph5.txt"));
        Integer result = path5.execute(graph);
        assertThat(result).isEqualTo(1);
    }


}
