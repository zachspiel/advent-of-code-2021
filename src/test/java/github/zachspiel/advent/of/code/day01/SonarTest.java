package github.zachspiel.advent.of.code.day01;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class SonarTest {

    @Test
    public void testPartA() {
        int actualResult = Sonar.countAllIncreases();
        int expectedResult = 1791;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testPartB() {
        int actualResult = Sonar.countEveryThreeIncreases();
        int expectedResult = 1822;
        assertEquals(expectedResult, actualResult);
    }
}
