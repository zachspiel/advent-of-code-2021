package github.zachspiel.advent.of.code.day08;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import util.Util;

public class SevenSegmentDisplay {

    private static final Path INPUT_FILE = Util.getFile("day08Input.txt");

    public static int calculateTotalUniqueSignalPatterns() {
        ArrayList<String> signals = Util.getAllLinesFromFile(INPUT_FILE);
        int total = 0;

        for (int index = 0; index < signals.size(); index++) {
            ArrayList<String> outputValues = getOutputValues(signals.get(index));

            total += outputValues.stream().filter(segment -> isUniqueSegment(segment))
                    .collect(Collectors.toList()).size();
        }

        return total;
    }

    public static int calculateOutputValuesTotal() {
        ArrayList<String> signals = Util.getAllLinesFromFile(INPUT_FILE);
        ArrayList<String> signalPatterns = getSignalPatterns();
        String result = "";
        long total = 0L;

        for (int index = 0; index < signals.size(); index++) {
            ArrayList<String> outputValues = getOutputValues(signals.get(index));

            if (result.length() != 0) {
                total += Long.parseLong(result);
                result = "";
            }

            for (String signal : outputValues) {
                List<String> filteredValues = outputValues.stream()
                        .filter(segment -> segment.length() == signal.length())
                        .collect(Collectors.toList());

                char[] characters = signal.toCharArray();

                switch (signal.length()) {
                    case 2:
                        result += "1";
                        signalPatterns.set(1, signal);
                        break;
                    case 4:
                        result += "4";
                        signalPatterns.set(4, signal);
                        break;
                    case 3:
                        result += "7";
                        signalPatterns.set(3, signal);
                        break;
                    case 7:
                        result += "8";
                        signalPatterns.set(7, signal);
                        break;
                    case 5:
                        if (!containsCharacter(characters, 'a')) {
                            result += "5";
                        } else if (!containsCharacter(characters, 'g')) {
                            result += "2";
                        } else {
                            result += "3";
                        }
                        break;
                    case 6:
                        if (!containsCharacter(characters, 'a')) {
                            result += "6";
                        } else if (!containsCharacter(characters, 'f')) {
                            result += "9";
                        } else {
                            result += "0";
                        }
                        break;
                    default:
                        break;
                }
            }
        }

        System.out.println(result);
        System.out.println(total);
        return 0;
    }

    private static ArrayList<String> getOutputValues(String line) {
        String[] outputValues = line.split("\\|")[1].trim().split(" ");
        ArrayList<String> result = new ArrayList<>();

        Collections.addAll(result, outputValues);
        return result;
    }

    private static int countOverlappingCharacter(String pattern, String signal) {
        char[] patternCharacters = pattern.toCharArray();
        char[] signalCharacters = signal.toCharArray();

        int total = 0;
        
        return total;
    }

    private static boolean containsCharacter(char[] characters, char character) {
        for (char value : characters) {
            if (value == character) {
                return true;
            }
        }

        return false;
    }

    private static ArrayList<String> getSignalPatterns() {
        ArrayList<String> patterns = new ArrayList<>();

        patterns.add("");
        patterns.add("");
        patterns.add("");
        patterns.add("");
        patterns.add("");
        patterns.add("");
        patterns.add("");
        patterns.add("");
        patterns.add("");
        patterns.add("");

        return patterns;
    }

    private static boolean isUniqueSegment(String segment) {
        return segment.length() < 5 || segment.length() == 7;
    }

    public static void main(String[] args) {
        System.out.println(calculateOutputValuesTotal());
    }

}
