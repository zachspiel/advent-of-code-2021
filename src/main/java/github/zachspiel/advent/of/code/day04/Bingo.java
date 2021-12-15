package github.zachspiel.advent.of.code.day04;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import util.Util;

public class Bingo {

    private static final Path INPUT_FILE = Util.getFile("day04Input.txt");
    private static final int BOARD_SIZE = 5;

    private static String getIndexOf(String[][] board, String number) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col].equals(number)) {
                    return row + "," + col;
                }
            }
        }

        return "";
    }

    public static int getWinningBoard(boolean getLastWinningBoard) {
        ArrayList<String> values = Util.getAllLinesFromFile(INPUT_FILE);

        String[] allMoves = getAllMoves(values.get(0));

        ArrayList<Boolean> hasWon = new ArrayList<>();
        values.remove(0);
        values.remove(0);
        ArrayList<String[][]> boards = getAllBoards(values);
        
        int totalBoards = boards.size();
        ArrayList<String[][]> scoreBoards = createEmptyBoards(totalBoards);
        ArrayList<String> remainingMoves = new ArrayList<String>();
        Collections.addAll(remainingMoves, allMoves);
        
        boards.stream().forEach(board -> hasWon.add(false));

        while (totalWinningBoards(hasWon) < totalBoards) {
            ArrayList<String> moves = getNextMoves(remainingMoves);
            moves.stream().forEach(move ->  remainingMoves.remove(0));
            
            for (int moveIndex = 0; moveIndex < moves.size(); moveIndex++) {
                for (int boardIndex = 0; boardIndex <totalBoards; boardIndex++) {
                    String[][] currentBoard = boards.get(boardIndex);
                    String position = getIndexOf(currentBoard, moves.get(moveIndex));
                    
                    if (position.length() > 0 && totalWinningBoards(hasWon) < totalBoards) {
                        int row = Integer.parseInt(position.split(",")[0]);
                        int col = Integer.parseInt(position.split(",")[1]);

                        String[][] currentScoreboard = scoreBoards.get(boardIndex);
                        currentScoreboard[row][col] = "X";
                        scoreBoards.set(boardIndex, currentScoreboard);

                        if (hasHorizontalBingo(currentScoreboard) || hasVerticalBingo(currentScoreboard)) {
                            int total = calculateTotal(currentScoreboard, boards.get(boardIndex));
                            
                            if (!getLastWinningBoard
                                    || (getLastWinningBoard && isLastBoardToWin(hasWon, totalBoards, boardIndex))) {

                                return total * Integer.parseInt(moves.get(moveIndex));
                            } else {
                                hasWon.set(boardIndex, true);
                            }
                        }

                    }
                }
            }
        }

        return -1;
    }

    private static boolean isLastBoardToWin(ArrayList<Boolean> boards, int totalBoards, int index) {
        return totalWinningBoards(boards) == totalBoards - 1 && boards.get(index) == false;
    }

    private static int totalWinningBoards(ArrayList<Boolean> boards) {
        return boards.stream().filter(board -> board == true)
                            .collect(Collectors.toList()).size();
    }
    private static boolean hasHorizontalBingo(String[][] board) {
        int total = 0;
        for (int row = 0; row < BOARD_SIZE; row++) {
            total = 0;
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col] == "X") {
                    total += 1;
                    if (total == 5) {
                        return true;
                    }
                } else {
                    total = 0;
                }
            }
        }

        return false;
    }

    private static boolean hasVerticalBingo(String[][] board) {
        int total = 0;
        for (int row = 0; row < BOARD_SIZE; row++) {
            total = 0;
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[col][row] == "X") {
                    total += 1;
                    if (total == 5) {
                        return true;
                    }
                } else {
                    total = 0;
                }
            }
        }

        return false;
    }

    private static ArrayList<String[][]> createEmptyBoards(int totalBoards) {
        ArrayList<String[][]> boards = new ArrayList<>();
        for (int index = 0; index < totalBoards; index++) {
            String[][] board = new String[BOARD_SIZE][BOARD_SIZE];

            for (int rowIndex = 0; rowIndex < BOARD_SIZE; rowIndex++) {
                for (int columnIndex = 0; columnIndex < BOARD_SIZE; columnIndex++) {
                    board[rowIndex][columnIndex] = "";
                }
            }
            boards.add(board);
        }

        return boards;
    }

    private static ArrayList<String> getNextMoves(ArrayList<String> allMoves) {
        ArrayList<String> moves = new ArrayList<>();

        int lastIndex = allMoves.size() < BOARD_SIZE ? allMoves.size() : BOARD_SIZE;

        for (int index = 0; index < lastIndex; index++) {
            moves.add(allMoves.get(index));
        }

        return moves;
    }

    private static String[] getAllMoves(String moves) {
        String[] allMoves = moves.split(",", 0);
        return allMoves;
    }

    private static ArrayList<String[][]> getAllBoards(ArrayList<String> allBoards) {
        ArrayList<String[][]> boards = new ArrayList<>();

        for (int index = 0; index < allBoards.size(); index += 6) {
            String[][] board = new String[BOARD_SIZE][BOARD_SIZE];

            for (int rowIndex = 0; rowIndex < BOARD_SIZE; rowIndex++) {
                ArrayList<String> numbers = getNumbersInRow(allBoards.get(index + rowIndex));
                for (int columnIndex = 0; columnIndex < BOARD_SIZE; columnIndex++) {
                    board[rowIndex][columnIndex] = numbers.get(columnIndex);
                }
            }
            boards.add(board);
        }

        return boards;
    }

    private static ArrayList<String> getNumbersInRow(String row) {
        String[] values = row.split(" ");
        ArrayList<String> filteredNumbers = new ArrayList<>();

        for (String value : values) {
            if (value.trim().length() != 0) {
                filteredNumbers.add(value);
            }
        }

        return filteredNumbers;
    }

    private static int calculateTotal(String[][] scoreBoard, String[][] currentBoard) {
        int total = 0;
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (scoreBoard[row][col].trim().isEmpty()) {
                    total += Integer.parseInt(currentBoard[row][col]);
                }
            }

        }
        return total;
    }
}
