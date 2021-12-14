package github.zachspiel.advent.of.code.day03;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class BinaryDiagnosticTest {
     @Test
    public void testPartA() {
        int actualResult = BinaryDiagnostic.calculatePowerConsumption();
        int expectedResult = 3242606;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testPartB() {
        int actualResult = BinaryDiagnostic.calculateLifeSupport();
        int expectedResult = 4856080;
        assertEquals(expectedResult, actualResult);
    }
}
