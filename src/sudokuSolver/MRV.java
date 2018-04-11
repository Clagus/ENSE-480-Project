/* ENSE 480 Final Project
 * MRV.java
 * Colin Lagus
 * 200315561 
*/

package sudokuSolver;

// Libraries
import java.util.HashMap;
import java.util.TreeSet;

public class MRV {

    // Global Variables
	public static final int SUBSIZE = 3;
    public static final int SIZE = 9;
    public static final int CELLS = 81;
    boolean[][] r;
    boolean[][] c;
    boolean[][][] b;
    int[][] puzzle;
    int fill;

    // Constructor
    public MRV(int[][] input) {
        r = new boolean[SIZE][SIZE + 1];
        c = new boolean[SIZE][SIZE + 1];
        b = new boolean[SIZE][SUBSIZE][SIZE + 1];

        puzzle = input;
        
        for (int i = 0; i < SIZE; i++) {
          for (int j = 0; j < SIZE; j++) {
            int elem = puzzle[i][j];
            
            for (int x = 1; x <= SIZE; x++) {
              if (elem == x) {
                r[i][x] = true;
                c[j][x] = true;
                b[i / SUBSIZE][j / SUBSIZE][x] = true;
                fill++;
              }
            }
          }
        }
      }
    
    // MRVAlgorithm Function: Core recursive algorithm for filling the puzzle with values. 
	public void MRVAlgorithm() {
		
		// Variable(s)
		TreeSet<Integer> remainingPossibleVal = null;
		int minimum = Integer.MAX_VALUE;
		int minimumCol = -1;
		int minimumRow = -1;

		
		AISudokuSolver.mrvIterations++; // Increment iteration counter for comparison.
		
		if (AISudokuSolver.viewSolve == true) {
			progressPrint(puzzle); // Prints progress.
		}

		if (fill == CELLS) { // Puzzle solved, exit loop condition.
			System.out.println("\n--- MRV Puzzle Solved! ---\n"
			           + "Iteration Count: " 
			           + AISudokuSolver.mrvIterations 
			           + "\nSolution:");
			print2D(puzzle);

			AISudokuSolver.compareResults();
		}
		
		// Find empty cell with MRV.
		for (int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				if (puzzle[i][j] != 0) {
					continue;
				}
				
				TreeSet<Integer> temp = findRemainingVal(i, j);
				
				if (minimum > temp.size()) {
					minimum = temp.size();
					remainingPossibleVal = temp;
					minimumRow = i;
					minimumCol = j;
				}
			}
		}
				
		// Try possible values to fill puzzle.
		for (Integer x:remainingPossibleVal) {
			
			// Set
			puzzle[minimumRow][minimumCol] = x;
			r[minimumRow][x] = true;
			c[minimumCol][x] = true;
			b[minimumRow / SUBSIZE][minimumCol / SUBSIZE][x] = true;
			
			fill++;
			MRVAlgorithm();
			fill--;
			
			// Reset
			b[minimumRow / SUBSIZE][minimumCol / SUBSIZE][x] = false;
			c[minimumCol][x] = false;
			r[minimumRow][x] = false;
			puzzle[minimumRow][minimumCol] = 0;
		}
		
	}
	
	// findRemainingVal Function: Finds possible remaining values for the puzzle.
	private TreeSet<Integer> findRemainingVal(int i, int j){
		
		// Variable(a)
		int bRow = i / SUBSIZE;
		int bCol =  j / SUBSIZE;
		TreeSet<Integer> remVal = new TreeSet<Integer>();
		TreeSet<Integer> possibleVals = new TreeSet<Integer>();
		HashMap<Integer, Integer> rMap = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> cMap = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> bMap = new HashMap<Integer, Integer>();

		for(int x = 1; x <= SIZE; x++) {
			if(!r[i][x]) {
				rMap.put(x, x);
		        remVal.add(x);
		    }

		    if(!c[j][x]) {
		    	cMap.put(x, x);
		        remVal.add(x);
		    }

		    if(!b[bRow][bCol][x]) {
		        bMap.put(x, x);
		        remVal.add(x);
		    }
		}

		for(Integer val:remVal) {
			if(rMap.get(val)!= null && cMap.get(val)!= null && bMap.get(val)!= null) {
				possibleVals.add(val);
		    }
		}

		return possibleVals;
	}

	// convertPuzzle2D Function: Takes the 1D arrays from the test puzzles and converts to 2D.
	public static int[][] convertPuzzle2D(int[] puzzle) {
		
		// Variable(s)
		int temp[][] = new int[SIZE][SIZE];
		
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				temp[j][i] = puzzle[(j * SIZE) + i];
			}
		}
		
		return temp;
	}
	
	// print2D Function: Prints out a 2D array in the Sudoku grid format.
	public static void print2D(int[][] puzzle){
		for(int i = 0; i < SIZE; i++){
			System.out.print("| "); // Left line border.
		    for(int j = 0; j < SIZE; j++){
		    	System.out.print(puzzle[i][j] + " | "); // Right border.
		    }
		    System.out.println();
		}
	}
	
	// progressPrint Function: Prints out the steps of the MRV algorithm. 
	public static void progressPrint(int[][] puzzle) {
		System.out.println("Attempt " 
							+ AISudokuSolver.mrvIterations 
							+ ":");
		print2D(puzzle);
	}
}
