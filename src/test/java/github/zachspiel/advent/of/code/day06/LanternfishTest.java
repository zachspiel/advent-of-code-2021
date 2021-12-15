package github.zachspiel.advent.of.code.day06;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class LanternfishTest {
    
    @Test 
     public void testPartA() {
        long actualResult = Lanternfish.calculateLaternfishGrowth(80);
        long expectedResult = 360268L;
        assertEquals(expectedResult, actualResult);
    }
     
     @Test 
     public void testPartB() {
        long actualResult = Lanternfish.calculateLaternfishGrowth(256);
        long expectedResult = 1632146183902L;
        assertEquals(expectedResult, actualResult);
    }
}
