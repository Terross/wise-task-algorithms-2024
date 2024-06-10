import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;
//import ru.leti.Valent;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class OneValentTest {
    @Test
    public void Test_not_val() throws FileNotFoundException{
        OneValent val1 = new OneValent();
        var graph1 = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph1.txt"));
        Boolean result = val1.execute(graph1);
        assertThat(result).isEqualTo(false);
    }

    @Test
    public void Test_not_full() throws FileNotFoundException{
        OneValent val2 = new OneValent();
        var graph2= GraphFactory.loadGraphFromFile(new File("src/test/resources/graph2.txt"));
        Boolean result = val2.execute(graph2);
        assertThat(result).isEqualTo(true);
    }

    @Test
    public void Test_true() throws FileNotFoundException{
        OneValent val3 = new OneValent();
        var graph3 = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph3.txt"));
        Boolean result = val3.execute(graph3);
        assertThat(result).isEqualTo(true);
    }

    @Test
    public void Test_more_edge() throws FileNotFoundException{
        OneValent val4 = new OneValent();
        var graph4 = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph4.txt"));
        Boolean result = val4.execute(graph4);
        assertThat(result).isEqualTo(false);
    }

    @Test
    public void Test_more_vertix() throws FileNotFoundException{
        OneValent val5 = new OneValent();
        var graph5 = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph5.txt"));
        Boolean result = val5.execute(graph5);
        assertThat(result).isEqualTo(true);
    }

}
