package github.zachspiel.advent.of.code.day07;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CrabTest {
    @Test 
     public void testPartA() {
         int actualResult = Crab.calculateConstantHorizontalPositions();
         int expectedResult = 331067;
         assertEquals(expectedResult, actualResult);
     }
    
}
