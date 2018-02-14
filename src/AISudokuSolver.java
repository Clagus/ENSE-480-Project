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
        


        System.out.println( "Test Memes" );

        puzzle = manualPuzzleInput();
        printPuzzle(puzzle);

        System.exit( 0 ); //success
    }

    // manualPuzzleInput Function: 
    public static int[] manualPuzzleInput() {

        // Variable(s)
        int puzzle[] = new int[CELLS];
        Scanner input = new Scanner(System.in);

        System.out.println( "Enter the values for your puzzle, 0 represents empty cells that need to be solved.\n" );

        for (int i = 0; i < SIZE; i++) {
            puzzle[i] = input.nextInt();
        }

        input.close();

        return puzzle;
    } 

    //
    public static void printPuzzle(int[] puzzle) {
        for (int i = 0; i < SIZE; i++) {

            System.out.print("| ");

            for (int j = 0; j < SIZE; j++) {
                System.out.print(puzzle[j] + " | ");
            }
            System.out.println(""); // LINE SPACER
        }
    }
}