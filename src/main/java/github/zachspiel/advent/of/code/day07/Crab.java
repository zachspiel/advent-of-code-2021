package github.zachspiel.advent.of.code.day07;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import util.Util;

public class Crab {

    private static final Path INPUT_FILE = Util.getFile("day07Input.txt");

    public static int calculateConstantHorizontalPositions() {
        ArrayList<Integer> positions = Util.getCommaSeperatedValuesFromFile(INPUT_FILE);
        int max = Collections.max(positions);

        int total = max * max;

        for (int index = 0; index < positions.size(); index++) {
            int totalChange = 0;
            for (int positonIndex = 0; positonIndex < positions.size(); positonIndex++) {
                totalChange += Math.abs(positions.get(index) - positions.get(positonIndex));
            }

            if (totalChange < total) {
                total = totalChange;
            }
        }

        return total;
    }

    public static int calculateHorizontalPositions() {
        ArrayList<Integer> positions = Util.getCommaSeperatedValuesFromFile(INPUT_FILE);
        Double average = positions.stream().mapToInt(i -> i).average().getAsDouble();
        int roundedAverage = average.intValue();

        int totalChange = 0;

        for (Integer position : positions) {
            int difference = Math.abs(position - roundedAverage);

            totalChange += (difference * (difference + 1)) / 2;
        }

        return totalChange;
    }
}
