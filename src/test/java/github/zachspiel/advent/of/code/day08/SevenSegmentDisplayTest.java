/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package github.zachspiel.advent.of.code.day08;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author zach1
 */
public class SevenSegmentDisplayTest {
     @Test 
     public void testPartA() {
        int actualResult = SevenSegmentDisplay.calculateTotalUniqueSignalPatterns();
        int expectedResult = 255;
        assertEquals(expectedResult, actualResult);
    }
     
     @Test 
     public void testPartB() {
        long actualResult = SevenSegmentDisplay.calculateOutputValuesTotal();
        long expectedResult = 982158;
        assertEquals(expectedResult, actualResult);
    }
}
