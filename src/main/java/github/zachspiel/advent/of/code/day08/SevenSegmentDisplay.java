package github.zachspiel.advent.of.code.day08;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;
import util.Util;

public class SevenSegmentDisplay {

    private static final Path INPUT_FILE = Util.getFile("day08Input.txt");

    public static int calculateTotalUniqueSignalPatterns() {
        ArrayList<String> signals = Util.getAllLinesFromFile(INPUT_FILE);
        int total = 0;

        for (int index = 0; index < signals.size(); index++) {
            ArrayList<String> outputValues = getOutputValues(signals.get(index));

            total += outputValues.stream().filter(SevenSegmentDisplay::isUniqueSegment)
                    .collect(Collectors.toList()).size();
        }

        return total;
    }

    public static Long calculateOutputValuesTotal() {
        ArrayList<String> lines = Util.getAllLinesFromFile(INPUT_FILE);

        long total = 0L;

        for (String line : lines) {
            ArrayList<String> signalPatterns = getSignalPatterns(line);
            ArrayList<String> decodedPatterns = decodeStaticPatterns(signalPatterns);
            ArrayList<String> outputValues = getOutputValues(line);
            String outputValueTotal = "";
            signalPatterns.sort(Comparator.comparingInt(String::length));

            for (int index = signalPatterns.size() - 1; index >= 0; index--) {
                String signalPatten = signalPatterns.get(index);

                switch (signalPatten.length()) {
                    case 6:
                        if (countOverlappingCharacters(decodedPatterns.get(1), signalPatten) == 1) {
                            decodedPatterns.set(6, signalPatten);
                        } else if (countOverlappingCharacters(decodedPatterns.get(4), signalPatten) == 4) {
                            decodedPatterns.set(9, signalPatten);
                        } else {
                            decodedPatterns.set(0, signalPatten);
                        }
                        break;
                    case 5:
                        if (countOverlappingCharacters(decodedPatterns.get(1), signalPatten) == 2) {
                            decodedPatterns.set(3, signalPatten);
                        } else if (countOverlappingCharacters(decodedPatterns.get(6), signalPatten) == 5) {
                            decodedPatterns.set(5, signalPatten);
                        } else {
                            decodedPatterns.set(2, signalPatten);
                        }
                        break;
                    default:
                        break;
                }
            }

            for (String outputValue : outputValues) {
                String decodedPattern = decodedPatterns.stream()
                        .filter(pattern -> Util.stringsAreEqual(pattern, outputValue))
                        .findFirst().get();
                int index = decodedPatterns.indexOf(decodedPattern);
                outputValueTotal += String.valueOf(index);
            }

            total += Long.parseLong(outputValueTotal);
        }

        return total;
    }
    
    private static ArrayList<String> getSignalPatterns(String line) {
        String[] outputValues = line.split("\\|")[0].trim().split(" ");
        ArrayList<String> result = new ArrayList<>();

        Collections.addAll(result, outputValues);
        return result;
    }

    private static ArrayList<String> getOutputValues(String line) {
        String[] outputValues = line.split("\\|")[1].trim().split(" ");
        ArrayList<String> result = new ArrayList<>();

        Collections.addAll(result, outputValues);
        return result;
    }

    private static int countOverlappingCharacters(String pattern, String signal) {
        char[] patternCharacters = pattern.toCharArray();
        char[] signalCharacters = signal.toCharArray();

        int total = 0;

        for (char signalCharacter : signalCharacters) {
            for (char patternCharacter : patternCharacters) {
                if (patternCharacter == signalCharacter) {
                    total += 1;
                }
            }
        }

        return total;
    }

    private static String getPattern(ArrayList<String> patterns, int length) {
        return patterns.stream()
                .filter(pattern -> pattern.length() == length)
                .findFirst().get();
    }

    private static ArrayList<String> decodeStaticPatterns(ArrayList<String> patterns) {
        ArrayList<String> decodedPatterns = new ArrayList<>(Arrays.asList(new String[10]));

        decodedPatterns.set(1, getPattern(patterns, 2));
        decodedPatterns.set(4, getPattern(patterns, 4));
        decodedPatterns.set(7, getPattern(patterns, 3));
        decodedPatterns.set(8, getPattern(patterns, 7));

        return decodedPatterns;
    }

    private static boolean isUniqueSegment(String segment) {
        return segment.length() < 5 || segment.length() == 7;
    }

}
