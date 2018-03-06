package sudokuSolver;
/* ENSE 480 Final Project
 * AISudokuSolver.java
 * Colin Lagus
 * 200315561 
*/

// Libraries
import java.util.Scanner;

public class AISudokuSolver {

    // Global Variables
    public static final int SIZE = 9;
    public static final int CELLS = 81;
    public static int backtrackerIterations;
    
    // main Function: 
    public static void main( String[] args ) {

        // Variable(s)
        int puzzle[];

        //puzzle = manualPuzzleInput();
        puzzle = testPuzzles();

        printPuzzle(puzzle);
        System.out.println("-------------------------------------\n"); // Spacer
        
        // Backtracker Algorithm // 
        Backtracker.backtrackerSolver(puzzle);

        
        System.exit(0); // End Program
    }

    // testPuzzles Function:
    public static int[] testPuzzles() {
        
        // Variable(s)
        Scanner input = new Scanner(System.in);
        int choice;
        int puzzleEasy[];
        int puzzleIntermediate[] = new int[CELLS];
        int puzzleDifficult[] = new int[CELLS];


        // Known Puzzles //
        // Easy Puzzle //
        // Puzzle                                    -->     Solution
        // | 0 | 0 | 0 | 2 | 6 | 0 | 7 | 0 | 1 |     -->     | 4 | 3 | 5 | 2 | 6 | 9 | 7 | 8 | 1 |     
        // | 6 | 8 | 0 | 0 | 7 | 0 | 0 | 9 | 0 |     -->     | 6 | 8 | 2 | 5 | 7 | 1 | 4 | 9 | 3 |  
        // | 1 | 9 | 0 | 0 | 0 | 4 | 5 | 0 | 0 |     -->     | 1 | 9 | 7 | 8 | 3 | 4 | 5 | 6 | 2 |
        // | 8 | 2 | 0 | 1 | 0 | 0 | 0 | 4 | 0 |     -->     | 8 | 2 | 6 | 1 | 9 | 5 | 3 | 4 | 7 | 
        // | 0 | 0 | 4 | 6 | 0 | 2 | 9 | 0 | 0 |     -->     | 3 | 7 | 4 | 6 | 8 | 2 | 9 | 1 | 5 | 
        // | 0 | 5 | 0 | 0 | 0 | 3 | 0 | 2 | 8 |     -->     | 9 | 5 | 1 | 7 | 4 | 3 | 6 | 2 | 8 |
        // | 0 | 0 | 9 | 3 | 0 | 0 | 0 | 7 | 4 |     -->     | 5 | 1 | 9 | 3 | 2 | 6 | 8 | 7 | 4 | 
        // | 0 | 4 | 0 | 0 | 5 | 0 | 0 | 3 | 6 |     -->     | 2 | 4 | 8 | 9 | 5 | 7 | 1 | 3 | 6 | 
        // | 7 | 0 | 3 | 0 | 1 | 8 | 0 | 0 | 0 |     -->     | 7 | 6 | 3 | 4 | 1 | 8 | 2 | 5 | 9 |

        puzzleEasy = new int[]{0, 0, 0, 2, 6, 0, 7, 0, 1,
                               6, 8, 0, 0, 7, 0, 0, 9, 0,
                               1, 9, 0, 0, 0, 4, 5, 0, 0,
                               8, 2, 0, 1, 0, 0, 0, 4, 0,
                               0, 0, 4, 6, 0, 2, 9, 0, 0,
                               0, 5, 0, 0, 0, 3, 0, 2, 8,
                               0, 0, 9, 3, 0, 0, 0, 7, 4,
                               0, 4, 0, 0, 5, 0, 0, 3, 6,
                               7, 0, 3, 0, 1, 8, 0, 0, 0};

    
        // Intermediate Puzzle //
        // Puzzle                                    -->     Solution
        // | 0 | 2 | 0 | 6 | 0 | 8 | 0 | 0 | 0 |     -->     | 1 | 2 | 3 | 6 | 7 | 8 | 9 | 4 | 5 |     
        // | 5 | 8 | 0 | 0 | 0 | 9 | 7 | 0 | 0 |     -->     | 5 | 8 | 4 | 2 | 3 | 9 | 7 | 6 | 1 |  
        // | 0 | 0 | 0 | 0 | 4 | 0 | 0 | 0 | 0 |     -->     | 9 | 6 | 7 | 1 | 4 | 5 | 3 | 2 | 8 |
        // | 3 | 7 | 0 | 0 | 0 | 0 | 5 | 0 | 0 |     -->     | 3 | 7 | 2 | 4 | 6 | 1 | 5 | 8 | 9 | 
        // | 6 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 4 |     -->     | 6 | 9 | 1 | 5 | 8 | 3 | 2 | 7 | 4 | 
        // | 0 | 0 | 8 | 0 | 0 | 0 | 0 | 1 | 3 |     -->     | 4 | 5 | 8 | 7 | 9 | 2 | 6 | 1 | 3 |
        // | 0 | 0 | 0 | 0 | 2 | 0 | 0 | 0 | 0 |     -->     | 8 | 3 | 6 | 9 | 2 | 4 | 1 | 5 | 7 | 
        // | 0 | 0 | 9 | 8 | 0 | 0 | 0 | 3 | 6 |     -->     | 2 | 1 | 9 | 8 | 5 | 7 | 4 | 3 | 6 | 
        // | 0 | 0 | 0 | 3 | 0 | 6 | 0 | 9 | 0 |     -->     | 7 | 4 | 5 | 3 | 1 | 6 | 8 | 9 | 2 |
        
        puzzleIntermediate = new int[]{0, 2, 0, 6, 0, 8, 0, 0, 0,
                                       5, 8, 0, 0, 0, 9, 7, 0, 0,
                                       0, 0, 0, 0, 4, 0, 0, 0, 0,
                                       3, 7, 0, 0, 0, 0, 5, 0, 0,
                                       6, 0, 0, 0, 0, 0, 0, 0, 4,
                                       0, 0, 8, 0, 0, 0, 0, 1, 3,
                                       0, 0, 0, 0, 2, 0, 0, 0, 0,
                                       0, 0, 9, 8, 0, 0, 0, 3, 6,
                                       0, 0, 0, 3, 0, 6, 0, 9, 0};

        
        // Difficult Puzzle //
        // Puzzle                                    -->     Solution
        // | 0 | 0 | 0 | 6 | 0 | 0 | 4 | 0 | 0 |     -->     | 5 | 8 | 1 | 6 | 7 | 2 | 4 | 3 | 9 |     
        // | 7 | 0 | 0 | 0 | 0 | 3 | 6 | 0 | 0 |     -->     | 7 | 9 | 2 | 8 | 4 | 3 | 6 | 5 | 1 |  
        // | 0 | 0 | 0 | 0 | 0 | 1 | 0 | 8 | 0 |     -->     | 3 | 6 | 4 | 5 | 9 | 1 | 7 | 8 | 2 |
        // | 0 | 0 | 0 | 0 | 9 | 0 | 0 | 0 | 0 |     -->     | 4 | 3 | 8 | 9 | 5 | 7 | 2 | 1 | 6 | 
        // | 0 | 5 | 0 | 1 | 9 | 0 | 0 | 0 | 3 |     -->     | 2 | 5 | 6 | 1 | 8 | 4 | 9 | 7 | 3 | 
        // | 0 | 0 | 0 | 3 | 8 | 6 | 0 | 4 | 5 |     -->     | 1 | 7 | 9 | 3 | 2 | 6 | 8 | 4 | 5 |
        // | 0 | 4 | 0 | 2 | 0 | 0 | 0 | 6 | 0 |     -->     | 8 | 4 | 5 | 2 | 1 | 9 | 3 | 6 | 7 | 
        // | 9 | 0 | 3 | 0 | 0 | 0 | 0 | 0 | 0 |     -->     | 9 | 1 | 3 | 7 | 6 | 8 | 5 | 2 | 4 | 
        // | 0 | 2 | 0 | 0 | 0 | 0 | 1 | 0 | 0 |     -->     | 6 | 2 | 7 | 4 | 3 | 5 | 1 | 9 | 8 |
        
        puzzleDifficult = new int[]{0, 0, 0, 6, 0, 0, 4, 0, 0,
                                    7, 0, 0, 0, 0, 3, 6, 0, 0,
                                    0, 0, 0, 0, 9, 1, 0, 8, 0,
                                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                                    0, 5, 0, 1, 8, 0, 0, 0, 3,
                                    0, 0, 0, 3, 0, 6, 0, 4, 5,
                                    0, 4, 0, 2, 0, 0, 0, 6, 0,
                                    9, 0, 3, 0, 0, 0, 0, 0, 0,
                                    0, 2, 0, 0, 0, 0, 1, 0, 0};

        System.out.println("Which puzzle would you like to test?\n   1. Easy\n   2. Intermediate\n   3. Difficult\n"); // Prompt
        
        // Choice input with invalid input checking.
        do {
            System.out.print("Choice: "); // Input Prompt
            while (!input.hasNextInt()) {
                    System.out.println("Please enter one of the three choice numbers."); // Invalid input prompt.
                    System.out.print("Choice: ");
                    input.next();
            }
            choice = input.nextInt();

        } while (choice <= 0); 

        switch (choice) {
            case 1: System.out.println("\n--- EASY PUZZLE ---");         input.close(); return puzzleEasy;
            case 2: System.out.println("\n--- INTERMEDIATE PUZZLE ---"); input.close(); return puzzleIntermediate;
            case 3: System.out.println("\n--- DIFFICULT PUZZLE ---");    input.close(); return puzzleDifficult;
        }
        
        input.close();
        return puzzleEasy;
    }

    // manualPuzzleInput Function: Accepts user input for a manual puzzle entry.
    public static int[] manualPuzzleInput() {

        // Variable(s)
        int puzzle[] = new int[CELLS];
        Scanner input = new Scanner(System.in);

        System.out.println( "Enter the values for your puzzle, 0 represents empty cells that need to be solved.\n" ); // Prompt

        // Loop input for 9x9 positions.
        for (int i = 0; i < CELLS; i++) {
            puzzle[i] = input.nextInt();
        }

        input.close();

        return puzzle;
    } 

    // printPuzzle Function: Prints out input puzzle in a readable Sudoku format.
    public static void printPuzzle(int[] puzzle) {

        // Variable(s)
        int position = 0;

        // Loops in a 9x9 fashion.
        for (int i = 0; i < SIZE; i++) { // Row Loop
            System.out.print("| "); // Left line border.
            for (int j = 0; j < SIZE; j++) { // Column Loop
                System.out.print(puzzle[position] + " | "); // Right border.
                position++;
            }
            System.out.println(""); // Line spacer.
        }
    }
}