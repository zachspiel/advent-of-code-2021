package github.zachspiel.advent.of.code.day01;

import java.nio.file.Path;
import java.util.ArrayList;
import util.Util;

public class Sonar {
  private static final Path INPUT_FILE = Util.getFile("day01Input.txt");

  public static int countAllIncreases() {
    ArrayList<Integer> values = Util.getAllNumbersFromFile(INPUT_FILE);
    int previousValue = 0;
    int total = 0;

    for (int index = 0; index < values.size(); index++) {
      if (values.get(index) > previousValue && index != 0) {
        total += 1;
      }
      previousValue = values.get(index);
    }
    
    return total;
  }

  public static int countEveryThreeIncreases() {
    ArrayList<Integer> values = Util.getAllNumbersFromFile(INPUT_FILE);
    int previousSum = 0;
    int WINDOW_SIZE = 3;
    int total = 0;

    for (int index = 0; index < WINDOW_SIZE; index++)
      previousSum += values.get(index);

    int windowSum = previousSum;
    for (int index = WINDOW_SIZE; index < values.size(); index++) {
      windowSum += values.get(index) - values.get(index - WINDOW_SIZE);

      if (windowSum > previousSum) {
        total += 1;
      }

      previousSum = windowSum;
    }
    
    return total;
  }
}
