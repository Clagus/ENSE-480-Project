package sudokuSolver;
/* ENSE 480 Final Project
 * Backtracker.java
 * Colin Lagus
 * 200315561 
*/

public class Backtracker {
	
    // Global Variables
	public static final int SUBSIZE = 3;
    public static final int SIZE = 9;
    public static final int CELLS = 81;
    public static int attemptCounter = 1;
    
    // backtrackerSolver Function: Runs the algorithm and displays success/failure to the user.
    public static void backtrackerSolver(int puzzle[]) {
        if (backtrackerAlgorithm(puzzle)) {
        	System.out.println("--- Puzzle Solved! ---\n"
        			           + "Iteration Count: " + AISudokuSolver.backtrackerIterations 
        			           + "\nSolution:\n");
        	AISudokuSolver.printPuzzle(puzzle);
        } else {
        	System.out.println("The puzzle could not be solved.");
        }
    }
    
    // backtrackerAlgorithm Function: Uses a depth-first backtracking algorithm to "brute force" a solution for a Sudoku puzzle.
	public static boolean backtrackerAlgorithm(int puzzle[]) {
		
		// Variables
		int row = 0;
		int col = 0;
		int subRow;
		int subCol;
		boolean checkEmpty = true;
		boolean isValid;
		boolean isSolved;

		// Finds empty (0 filled) cells.
		for (int i = 0; i < CELLS; i++) {
			if (puzzle[i] == 0) {
				row = i / SIZE;
				col = i % SIZE;
				checkEmpty = false; // Puzzle contains empty cells, return false.
				break;
			}
		}
		
		// If no empty cells, return true, indicating a solved puzzle.
		if (checkEmpty) {
			return true;
		}
		
		for (int i = 1; i <= SIZE; i++) {
			subRow = row / SUBSIZE;
			subCol = col / SUBSIZE;
			isValid = true;
			
			// Duplicate check for 3x3 sub-square.
			for (int r = SUBSIZE * subRow; r < SUBSIZE * subRow + SUBSIZE; r++) {
				for (int c = SUBSIZE * subCol; c < SUBSIZE * subCol + SUBSIZE; c++) {
					if (puzzle[r * SIZE + c] == i) {
						isValid = false;
					}
				}
			}
			
			// Row and Column checks.
			for (int j = 0; j < SIZE; j++) {
				if (puzzle[row * SIZE + j] == i || puzzle[SIZE * j + col] == i) {
					isValid = false;
					break;
				}
			}
			
			if (isValid) {
				puzzle[row * SIZE + col] = i;
				isSolved = backtrackerAlgorithm(puzzle); // Recursive Call
				
				if (isSolved) {
					return true;
				} else {
					puzzle[row * SIZE + col] = 0;
				}
			}
			
			AISudokuSolver.backtrackerIterations++; // Increment iteration counter for comparison.
			progressPrint(puzzle); // Prints progress.
		}
		
		return false;
	}
	
	// progressPrint Function: Prints out the steps of the backtracking algorithm. 
	public static void progressPrint(int[] puzzle) {
		System.out.println("Attempt " + attemptCounter + ":");
		attemptCounter++;
		AISudokuSolver.printPuzzle(puzzle);
		System.out.println(""); // Spacer
		
		// Test lines.
	    System.out.print("\033[H\033[2J");  
	    System.out.flush(); 
	}
}
