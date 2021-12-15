package github.zachspiel.advent.of.code.day05;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;
import java.util.Hashtable;
import java.util.stream.Collectors;
import util.Util;

public class HydrothermalVents {

    // part one should be 5 for test and 7436 for input
    // part two should be 21104
    private static final Path INPUT_FILE = Util.getFile("day05Input.txt");

    public static int calculateOverlappingLines(boolean countDiagonal) {
        ArrayList<String> lines = Util.getAllLinesFromFile(INPUT_FILE);
        Map<String, Integer> coordinates = new Hashtable<>();

        for (int index = 0; index < lines.size(); index++) {
            String[] line = lines.get(index).split("->");
            String[] coordinate1 = line[0].trim().split(",");
            String[] coordinate2 = line[1].trim().split(",");

            int x1 = Integer.parseInt(coordinate1[0]);
            int y1 = Integer.parseInt(coordinate1[1]);
            int x2 = Integer.parseInt(coordinate2[0]);
            int y2 = Integer.parseInt(coordinate2[1]);

            if (x1 > x2 || y1 > y2) {
                int temp = x2;
                x2 = x1;
                x1 = temp;

                temp = y2;
                y2 = y1;
                y1 = temp;
            }

            if (x1 == x2 || y1 == y2) {
                for (int x = x1; x <= x2; x++) {
                    for (int y = y1; y <= y2; y++) {
                        String key = String.valueOf(x) + "," + String.valueOf(y);

                        int value = coordinates.get(key) != null ? coordinates.get(key) + 1 : 1;
                        coordinates.put(key, value);
                    }
                }
            } else if (countDiagonal) {
                int curr_x = x1;
                int curr_y = y1;

                int x_delta = x1 < x2 ? 1 : -1;
                int y_delta = y1 < y2 ? 1 : -1;

                Map<String, String> iterator = new Hashtable<>();

                while (curr_x != x2) {
                    iterator.put(String.valueOf(curr_x), String.valueOf(curr_y));
                    curr_x += x_delta;
                    curr_y += y_delta;
                }

                iterator.put(String.valueOf(curr_x), String.valueOf(curr_y));

                for (String iteratorKey : iterator.keySet()) {
                    String key = iteratorKey + "," + iterator.get(iteratorKey);

                    int value = coordinates.get(key) != null ? coordinates.get(key) + 1 : 1;
                    coordinates.put(key, value);
                }
            }
        }

        int total = coordinates.keySet().stream()
                .filter(key -> coordinates.get(key) > 1)
                .collect(Collectors.toList()).size();

        return total;
    }
}
