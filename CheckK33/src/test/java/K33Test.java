import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;

public class K33Test {
    @Test
    public void activateSimpleTest() throws FileNotFoundException {
        var k33test = new CheckK33();

        File trueDirectory = new File("src/test/resources/k33/truses");
        File falseDirectory = new File("src/test/resources/k33/falses");

        File[] trueFiles = trueDirectory.listFiles();
        File[] falseFiles = falseDirectory.listFiles();

        System.out.println(trueFiles.length);
        System.out.println(falseFiles.length);

        assert trueFiles != null;
        assert falseFiles != null;
        for (var tF : trueFiles)
            assertThat(k33test.execute(GraphFactory.loadGraphFromFile(tF))).isTrue();
        for (var fF : falseFiles)
            assertThat(k33test.execute(GraphFactory.loadGraphFromFile(fF))).isFalse();
    }
}
