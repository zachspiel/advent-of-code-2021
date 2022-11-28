package github.zachspiel.advent.of.code.day10;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class SyntaxScoringTest {
    @Test
    public void testPartA() {
        int score = SyntaxScoring.getCorruptedLineScores();
        assertEquals(168417, score);
    }
}
