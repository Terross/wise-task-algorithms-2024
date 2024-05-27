package ru.leti;

import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.GraphFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class pawsNumberTest{

    @Test
    public void testTwoPaws() throws FileNotFoundException {
        clawNumber clawNumberCharacteristic = new clawNumber();
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph1.txt"));
        Integer result = clawNumberCharacteristic.execute(testGraph);
        assertThat(result).isEqualTo(2);
    }

    @Test
    public void testOnePaw() throws FileNotFoundException {
        clawNumber clawNumberCharacteristic = new clawNumber();
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph2.txt"));
        Integer result = clawNumberCharacteristic.execute(testGraph);
        assertThat(result).isEqualTo(1);
    }


    @Test
    public void testZeroPaws() throws FileNotFoundException {
        clawNumber clawNumberCharacteristic = new clawNumber();
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph3.txt"));
        Integer result = clawNumberCharacteristic.execute(testGraph);
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void testHardPaws() throws FileNotFoundException {
        clawNumber clawNumberCharacteristic = new clawNumber();
        Graph testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/graph4.txt"));
        Integer result = clawNumberCharacteristic.execute(testGraph);
        assertThat(result).isEqualTo(2);
    }

}