package github.zachspiel.advent.of.code.day02;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class SubmarineTest {
     @Test
    public void testPartA() {
        int actualResult = Submarine.calculatePosition();
        int expectedResult = 2187380;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testPartB() {
        int actualResult = Submarine.calculatePositionWithAim();
        int expectedResult = 2086357770;
        assertEquals(expectedResult, actualResult);
    }
}
