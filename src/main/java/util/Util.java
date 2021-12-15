package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Util {

    public static Path getFile(String fileName) {
        return Paths.get("./src/inputFiles/" + fileName);
    }

    public static ArrayList<Integer> getAllNumbersFromFile(Path path) {
        ArrayList<String> lines = getAllLinesFromFile(path);
        ArrayList<Integer> integerValues = new ArrayList<>();
        
        lines.stream().forEach(line -> integerValues.add(Integer.parseInt(line)));

        return integerValues;
    }

    public static ArrayList<String> getAllLinesFromFile(Path path) {
        ArrayList<String> lines = new ArrayList<>();
        try {
            File myObj = path.toFile();
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String nextLine = myReader.nextLine();
                lines.add(nextLine);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading file.");
            e.printStackTrace();
        }

        return lines;
    }
    
    public static ArrayList<Integer> getCommaSeperatedValuesFromFile(Path path) {
        ArrayList<Integer> values = new ArrayList<>();

        try {
            File myObj = path.toFile();
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String nextLine = myReader.nextLine();

                String[] stringValues = nextLine.split(",");
                
                for (int index = 0; index < stringValues.length; index++) {
                    values.add(Integer.parseInt(stringValues[index]));
                }
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading input file.");
            e.printStackTrace();
        }
        return values;
    }
}
