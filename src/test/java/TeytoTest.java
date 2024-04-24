import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;


public class TeytoTest {

    @Test
    public void activateSimpleTest() throws FileNotFoundException {
        var teytoTest = new Teyto();

        File trueDirectory = new File("src/test/resources/teyto/trues");
        File falseDirectory = new File("src/test/resources/teyto/falses");

        File[] trueFiles = trueDirectory.listFiles();
        File[] falseFiles = falseDirectory.listFiles();

        System.out.println(trueFiles.length);
        System.out.println(falseFiles.length);

        assert trueFiles != null;
        assert falseFiles != null;
        for (var tF : trueFiles)
            assertThat(teytoTest.execute(GraphFactory.loadGraphFromFile(tF))).isTrue();
        for (var fF : falseFiles)
            assertThat(teytoTest.execute(GraphFactory.loadGraphFromFile(fF))).isFalse();
    }
}
