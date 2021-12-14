package github.zachspiel.advent.of.code.day03;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;
import util.Util;

public class BinaryDiagnostic {
    private static final Path INPUT_FILE = Util.getFile("day03Input.txt");

    public static int calculatePowerConsumption() {
        ArrayList<String> values = Util.getAllLinesFromFile(INPUT_FILE);
        ArrayList<String> gammaValues = new ArrayList<>();
        ArrayList<String> epsilonValues = new ArrayList<>();
        ArrayList<Integer> commonValues = getCommonValues(values);

        for (int index = 0; index < commonValues.size(); index++) {
            if (commonValues.get(index) > 0) {
                gammaValues.add("1");
                epsilonValues.add("0");
            } else {
                gammaValues.add("0");
                epsilonValues.add("1");
            }
        }

        String gammaValue = gammaValues.stream().collect(Collectors.joining(""));
        String epsilonValue = epsilonValues.stream().collect(Collectors.joining(""));

        return Integer.parseInt(gammaValue, 2) * Integer.parseInt(epsilonValue, 2);
    }

    public static int calculateLifeSupport() {
        ArrayList<String> values = Util.getAllLinesFromFile(INPUT_FILE);
        ArrayList<String> oxygen = new ArrayList<>(values);
        ArrayList<String> c02 = new ArrayList<>(values);
        int iterations = 0;

        while (oxygen.size() != 1) {
            oxygen = new ArrayList<>(values);
            for (int index = iterations; index < oxygen.get(0).length(); index++) {
                ArrayList<Integer> commonValues = getCommonValues(oxygen);
                int mostCommon = commonValues.get(index) > 0 ? 1 : 0;
                if (commonValues.get(index) == 0) {
                    mostCommon = 1;
                }
                if (oxygen.size() > 1) {
                    oxygen = filterList(oxygen, String.valueOf(mostCommon).charAt(0), index);
                }
            }
            iterations += 1;
        }

        while (c02.size() != 1) {
            c02 = new ArrayList<>(values);
            for (int index = 0; index < c02.get(0).length(); index++) {
                ArrayList<Integer> commonValues = getCommonValues(c02);
                int leastCommon = commonValues.get(index) > 0 ? 0 : 1;

                if (commonValues.get(index) == 0) {
                    leastCommon = 0;
                }

                if (c02.size() > 1) {
                    c02 = filterList(c02, String.valueOf(leastCommon).charAt(0), index);
                }
            }
        }

        String oxygenResult = oxygen.get(0);
        String c02Result = c02.get(0);

        return Integer.parseInt(oxygenResult, 2) * Integer.parseInt(c02Result, 2);
    }

    private static ArrayList<String> filterList(ArrayList<String> array, char searchCharacter, int charIndex) {
        List<String> result = array.stream()
                .filter(value -> value.charAt(charIndex) == searchCharacter)
                .collect(Collectors.toList());

        return new ArrayList<String>(result);
    }

    private static ArrayList<Integer> getCommonValues(ArrayList<String> array) {
        ArrayList<Integer> commonValues = new ArrayList<Integer>(
                Collections.nCopies(array.get(0).toCharArray().length, 0));

        for (int index = 0; index < array.size(); index++) {
            char[] characters = array.get(index).toCharArray();

            for (int characterIndex = 0; characterIndex < characters.length; characterIndex++) {
                if (characters[characterIndex] == '1') {
                    commonValues.set(characterIndex, commonValues.get(characterIndex) + 1);
                } else {
                    commonValues.set(characterIndex, commonValues.get(characterIndex) - 1);
                }
            }
        }

        return commonValues;
    }
}
