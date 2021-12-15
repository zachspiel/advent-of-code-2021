package github.zachspiel.advent.of.code.day05;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class HydrothermalVentsTest {

    @Test
    public void testPartA() {
        int actualResult = HydrothermalVents.calculateOverlappingLines(false);
        int expectedResult = 7436;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testPartB() {
        int actualResult = HydrothermalVents.calculateOverlappingLines(true);
        int expectedResult = 21104;
        assertEquals(expectedResult, actualResult);
    }
}
