package github.zachspiel.advent.of.code.day04;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class BingoTest {

    @Test
    public void testPartA() {
        int actualResult = Bingo.getWinningBoard(false);
        int expectedResult = 58374;
        assertEquals(expectedResult, actualResult);
    }
}
