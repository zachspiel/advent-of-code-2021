package github.zachspiel.advent.of.code.day09;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import util.Util;

public class LavaTubes {

    private static final Path INPUT_FILE = Util.getFile("day09Input.txt");

    public static int calculateRiskLevels() {
        ArrayList<String> lines = Util.getAllLinesFromFile(INPUT_FILE);
        int totalRows = lines.size();
        int totalColumns = lines.get(0).split("").length;

        int[][] heightmap = createHeightMap(lines, totalRows, totalColumns);
        int total = 0;

        for (int row = 0; row < totalRows; row++) {
            for (int col = 0; col < totalColumns; col++) {
                int currentValue = heightmap[row][col];

                ArrayList<Integer> neighbors = getNeighbors(heightmap, row, col);
                int min = Collections.min(neighbors);

                if (currentValue < min) {
                    total += currentValue + 1;
                }
            }
        }

        return total;
    }

    public static int calculateBasins() {
        ArrayList<String> lines = Util.getAllLinesFromFile(INPUT_FILE);
        ArrayList<Coordinate> lowPoints = new ArrayList<>();
        int totalRows = lines.size();
        int totalColumns = lines.get(0).split("").length;
        int total = 1;

        int[][] heightmap = createHeightMap(lines, totalRows, totalColumns);

        for (int row = 0; row < totalRows; row++) {
            for (int col = 0; col < totalColumns; col++) {
                int currentValue = heightmap[row][col];

                ArrayList<Integer> neighbors = getNeighbors(heightmap, row, col);
                int min = Collections.min(neighbors);

                if (currentValue < min) {
                    lowPoints.add(new Coordinate(row, col));
                }
            }
        }

        boolean[][] vis = new boolean[totalRows][totalColumns];

        ArrayList<ArrayList<Integer>> basins = new ArrayList<>();

        for (Coordinate lowPoint : lowPoints) {
            ArrayList<Integer> basin = new ArrayList<>();
            searchBasins(heightmap, vis, lowPoint, basin);
            basins.add(basin);
        }

        sortBasins(basins);

        for (int index = basins.size() - 1; index > basins.size() - 4; index--) {
            total = total * basins.get(index).size();
        }

        return total;
    }

    private static int[][] createHeightMap(ArrayList<String> lines, int totalRows, int totalColumns) {
        int[][] heightmap = new int[totalRows][totalColumns];

        int row = 0;
        for (String line : lines) {
            String[] values = line.split("");
            int[] integerValues = new int[values.length];

            for (int index = 0; index < values.length; index++) {
                integerValues[index] = Integer.parseInt(values[index]);
            }

            heightmap[row] = integerValues;
            row += 1;
        }

        return heightmap;
    }

    private static ArrayList<Integer> getNeighbors(int[][] heightmap, int row, int col) {
        ArrayList<Integer> neighbors = new ArrayList<>();

        if (row != 0) {
            neighbors.add(heightmap[row - 1][col]);
        }
        if (row < heightmap.length - 1) {
            neighbors.add(heightmap[row + 1][col]);
        }
        if (col != 0) {
            neighbors.add(heightmap[row][col - 1]);
        }
        if (col != heightmap[0].length - 1) {
            neighbors.add(heightmap[row][col + 1]);
        }

        return neighbors;
    }

    private static void sortBasins(ArrayList<ArrayList<Integer>> basins) {
        Comparator<ArrayList<Integer>> arrayListLengthComparator = new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                return Integer.compare(o1.size(), o2.size());
            }
        };

        Collections.sort(basins, arrayListLengthComparator);
    }

    public static void searchBasins(int grid[][], boolean visited[][],
            Coordinate startingCoordinate, ArrayList<Integer> basin) 
    {
        Queue<Coordinate> queue = new LinkedList<>();

        queue.add(startingCoordinate);

        int dRow[] = {-1, 0, 1, 0};
        int dCol[] = {0, 1, 0, -1};

        while (!queue.isEmpty()) {
            Coordinate coordinate = queue.poll();
            int x = coordinate.row;
            int y = coordinate.col;

            if (visited[x][y]) {
                continue;
            }

            visited[x][y] = true;
            basin.add(grid[x][y]);

            for (int index = 0; index < 4; index++) {
                int adjx = x + dRow[index];
                int adjy = y + dCol[index];

                if (isValid(visited, adjx, adjy) && grid[adjx][adjy] != 9) {
                    queue.add(new Coordinate(adjx, adjy));
                }
            }
        }
    }

    static boolean isValid(boolean visited[][], int row, int col) {
        if (row < 0 || col < 0
                || row >= visited.length || col >= visited[0].length) {
            return false;
        }

        if (visited[row][col]) {
            return false;
        }

        return true;
    }

    public static class Coordinate {

        int row;
        int col;

        public Coordinate(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
