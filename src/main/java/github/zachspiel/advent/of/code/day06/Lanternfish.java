package github.zachspiel.advent.of.code.day06;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import util.Util;

public class Lanternfish {
    private static final Path INPUT_FILE = Util.getFile("day06Input.txt");

    public static long calculateLaternfishGrowth(int totalDays) {
        ArrayList<Long> lanternfish = getStartingLanternfish();
        
        for (int day = 0; day < totalDays; day++) {
            lanternfish.add(lanternfish.get(0));
            lanternfish.remove(0);
            lanternfish.set(6, lanternfish.get(6) + lanternfish.get(8));
        }

        return lanternfish.stream().mapToLong(fish -> fish).sum();
    }
    
    
    private static ArrayList<Long> createEmptyList() {
        return new ArrayList<Long>(Arrays.asList(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L));
    }
    
    private static ArrayList<Long> getStartingLanternfish() {
        ArrayList<Long> lanternfishAges = createEmptyList();

        try {
            File myObj = INPUT_FILE.toFile();
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String nextLine = myReader.nextLine();

                String[] startingFish = nextLine.split(",");

                for (int index = 0; index < startingFish.length; index++) {
                    int ageIndex = Integer.parseInt(startingFish[index]);
                    lanternfishAges.set(ageIndex, lanternfishAges.get(ageIndex) + 1);
                }
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading input file.");
            e.printStackTrace();
        }
        return lanternfishAges;
    }
}
