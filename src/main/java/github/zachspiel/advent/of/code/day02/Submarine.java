package github.zachspiel.advent.of.code.day02;

import java.nio.file.Path;
import java.util.ArrayList;
import util.Util;

public class Submarine {
    private static final Path INPUT_FILE = Util.getFile("day02Input.txt");

    public static int calculatePosition() {
        ArrayList<String> commands = Util.getAllLinesFromFile(INPUT_FILE);
        int horizontalPosition = 0;
        int depth = 0;

        for (int index = 0; index < commands.size(); index++) {
            String[] command = commands.get(index).split("\\s+");
            String commandName = command[0];
            int positionChange = Integer.parseInt(command[1]);

            switch (commandName) {
                case "forward":
                    horizontalPosition += positionChange;
                    break;
                case "down":
                    depth += positionChange;
                    break;
                default:
                    depth -= positionChange;
                    break;
            }
        }

        return horizontalPosition * depth;
    }

    public static int calculatePositionWithAim() {
        ArrayList<String> commands = Util.getAllLinesFromFile(INPUT_FILE);
        int horizontalPosition = 0;
        int depth = 0;
        int aim = 0;

        for (int index = 0; index < commands.size(); index++) {
            String[] command = commands.get(index).split("\\s+");
            String commandName = command[0];
            int positionChange = Integer.parseInt(command[1]);

            switch (commandName) {
                case "forward":
                    horizontalPosition += positionChange;
                    depth += aim * positionChange;
                    break;
                case "down":
                    aim += positionChange;
                    break;
                case "up":
                    aim -= positionChange;
                    break;
            }
        }

        return horizontalPosition * depth;
    }
}
