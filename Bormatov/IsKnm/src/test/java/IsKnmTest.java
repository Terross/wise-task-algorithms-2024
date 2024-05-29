import com.mathsystem.api.graph.GraphFactory;
import com.mathsystem.api.graph.model.Graph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.stream.Stream;

public class IsKnmTest {
    private static final String TEST_RESOURCES_DIR = "src/test/resources/tests",
            TEST_RESULTS_DIR = "src/test/resources/results";

    private static Stream<Arguments> provideTestFiles() {
        File[] files = new File(TEST_RESOURCES_DIR).listFiles();
        if (files == null)
            return Stream.empty();
        return Stream.of(files).map(IsKnmTest::createArguments);
    }

    private static boolean getResultForTestFile(File testFile) throws FileNotFoundException {
        File file = new File(TEST_RESULTS_DIR + "/" + testFile.getName());
        Scanner scanner = new Scanner(file);
        return scanner.nextBoolean();
    }

    private static Arguments createArguments(File file) throws RuntimeException {
        boolean expectedResult;
        try {
            expectedResult = getResultForTestFile(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return Arguments.of(file, expectedResult);
    }

    @ParameterizedTest
    @MethodSource("provideTestFiles")
    public void testIsKnm(File file) throws AssertionError, FileNotFoundException {
        IsKnm isKnm = new IsKnm();
        Graph testGraph = GraphFactory.loadGraphFromFile(file);
        boolean result = isKnm.execute(testGraph);
        Assertions.assertEquals(getResultForTestFile(file), result, "Test failed for file " + file.getName());
    }
}