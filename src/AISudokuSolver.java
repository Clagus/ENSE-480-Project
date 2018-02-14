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
    public static final int CELLS = 36;
    
    // main Function: 
    public static void main( String[] args ) {

        // Variable(s)
        int puzzle[];
        
        System.out.println( "START" );

        //puzzle = manualPuzzleInput();
        puzzle = testPuzzles();

        printPuzzle(puzzle);

        System.exit( 0 ); //success
    }

    // testPuzzles Function:
    public static int[] testPuzzles() {
        
        // Variable(s)
        int puzzleEasy[];
        int puzzleMedium[] = new int[CELLS];
        int puzzleHard[] = new int[CELLS];

        // Known Puzzles //
        // Easy Puzzle
        puzzleEasy = new int[]{0, 0, 0, 2, 6, 0, 7, 0, 1,
                               6, 8, 0, 0, 7, 0, 0, 9, 0,
                               1, 9, 0, 0, 0, 4, 5, 0, 0,
                               8, 2, 0, 1, 0, 0, 0, 4, 0,
                               0, 0, 4, 6, 0, 2, 9, 0, 0,
                               0, 5, 0, 0, 0, 3, 0, 2, 8,
                               0, 0, 9, 3, 0, 0, 0, 7, 4,
                               0, 4, 0, 0, 5, 0, 0, 3, 6,
                               7, 0, 3, 0, 1, 8, 0, 0, 0};
                               
    
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