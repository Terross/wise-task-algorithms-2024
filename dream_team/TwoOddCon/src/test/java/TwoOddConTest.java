/*
public class TwoOddConTest {

    @Test
    public void activateSimpleTest() throws FileNotFoundException {
        TwoOddCon twoOddCon = new TwoOddCon();
        // Must be true:
        var true1 = GraphFactory.loadGraphFromFile(new File("src/test/resources/true1.txt"));
        var true2 = GraphFactory.loadGraphFromFile(new File("src/test/resources/true2.txt"));
        var true3 = GraphFactory.loadGraphFromFile(new File("src/test/resources/true3.txt"));
        assertThat(twoOddCon.execute(true1)).isTrue();
        assertThat(twoOddCon.execute(true2)).isTrue();
        assertThat(twoOddCon.execute(true3)).isTrue();
        // Must be false:
        var k13_two = GraphFactory.loadGraphFromFile(new File("src/test/resources/k13_two.txt"));
        var k13 = GraphFactory.loadGraphFromFile(new File("src/test/resources/k13.txt"));
        var two_rhombs = GraphFactory.loadGraphFromFile(new File("src/test/resources/two_rhombs.txt"));
        var bridge = GraphFactory.loadGraphFromFile(new File("src/test/resources/bridge.txt"));
        assertThat(twoOddCon.execute(k13_two)).isFalse();
        assertThat(twoOddCon.execute(k13)).isFalse();
        assertThat(twoOddCon.execute(two_rhombs)).isFalse();
        assertThat(twoOddCon.execute(bridge)).isFalse();
    }
}
*/