package github.zachspiel.advent.of.code.day09;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class LavaTubesTest {

    @Test
    public void testPartA() {
        int actualResult = LavaTubes.calculateRiskLevels();
        int expectedResult = 498;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testPartB() {
        int actualResult = LavaTubes.calculateBasins();
        int expectedResult = 1071000;
        assertEquals(expectedResult, actualResult);
    }
}
