/* ENSE 480 Final Project
 * Backtracker.java
 * Colin Lagus
 * 200315561 
*/

public class Backtracker {
	
    // Global Variables
    public static final int SIZE = 9;
    public static final int CELLS = 36;

    // backtrackerSolver Function: Uses a depth-first backtracking algorithm to "brute force" a solution for a Sudoku puzzle.
	public static boolean backtrackerSolver(int puzzle[]) {
		
		// Variables
		int row = 0;
		int col = 0;
		int subRow;
		int subCol;
		int n;
		boolean checkEmpty = true;
		boolean isValid;
		boolean isSolved;
		
		n = (int)Math.round(Math.pow(puzzle.length, 0.25d));
		
		for (int i = 0; i < CELLS; i++) {
			if (puzzle[i] == 0) {
				row = i / SIZE;
				col = i % SIZE;
				checkEmpty = false;
				break;
			}
		}
		
		if (checkEmpty) {
			return true;
		}
		
		for (int i = 1; i <= SIZE; i++) {
			subRow = row / n;
			subCol = col / n;
			isValid = true;
			
			// Duplicate check for 3x3 sub-square.
			for (int r = n * subRow; r < n * subRow + n; r++) {
				for (int c = n * subCol; c < n * subCol + n; c++) {
					if (puzzle[r * SIZE + col] == i) {
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
				isSolved = backtrackerSolver(puzzle); // Recursive Call
				
				if (isSolved) {
					return true;
				} else {
					puzzle[row * SIZE + col] = 0;
				}
			}
			
			//AISudokuSolver.printPuzzle(puzzle);
			System.out.println(""); // Spacer
		}
		
		return false;
	}
}
