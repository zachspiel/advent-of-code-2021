package github.zachspiel.advent.of.code.day10;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Stack;
import util.Util;

public class SyntaxScoring {

    private static final Path INPUT_FILE = Util.getFile("day10Input.txt");

    public static int getCorruptedLineScores() {
        ArrayList<String> lines = Util.getAllLinesFromFile(INPUT_FILE);
        int totalLines = lines.size();
        boolean hasFoundIncorrectCharacter = false;
        int index = 0;
        int totalScore = 0;
        int part2Score = 0;
        ArrayList<Integer> scores = new ArrayList<>(totalLines);
        
        Stack<Character> characterStack = new Stack<>();
        
        while(index < totalLines && !hasFoundIncorrectCharacter) {
            char[] characters = lines.get(index).toCharArray();
            boolean isIncomplete = false;
            for(char character: characters) {
                if(isOpeningCharacter(character)) {
                    characterStack.add(character);
                } else if(!characterStack.isEmpty()) {
                    char existingCharacter = characterStack.pop();
                   
                    if(character == ']' && existingCharacter != '[') {
                        totalScore = totalScore + 57;
                    } 
                    else if(character == ')' && existingCharacter != '(') {
                        totalScore = totalScore + 3;
                    }   
                    else if(character == '}' && existingCharacter != '{') {
                        totalScore = totalScore + 1197;
                    }   
                    else if(character == '>' && existingCharacter != '<') {
                        totalScore = totalScore + 25137;
                    } else {
                        isIncomplete = true;
                    } 
                }
            }
            
            while(!characterStack.isEmpty()) {
               char character = characterStack.pop();
                part2Score = part2Score * 5;
                switch (character) {
                    case '[':
                        part2Score = part2Score + 2;
                        break;
                    case '{':
                        part2Score = part2Score + 3;
                        break;
                    case '(':
                        part2Score = part2Score + 1;
                        break;
                    case '<':
                        part2Score = part2Score + 4;
                        break;
                    default:
                        break;
                }
            }
            
            if(isIncomplete) {
                scores.add(part2Score);
            }
            
            part2Score = 0;
            
            index++;
        }
        
    
        return totalScore;
    }
   
    private static boolean isOpeningCharacter(char character) {
        return character == '(' ||
            character == '[' || 
            character == '{' ||
            character == '<';
    }

    private static boolean isClosingCharacter(char character) {
        return !isOpeningCharacter(character);
    }
}
