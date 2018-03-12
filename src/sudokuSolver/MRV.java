package sudokuSolver;

import java.util.HashMap;
import java.util.TreeSet;

public class MRV {

    // Global Variables
	public static final int SUBSIZE = 3;
    public static final int SIZE = 9;
    public static final int CELLS = 81;
    static boolean[][] r = new boolean[SIZE][SIZE + 1];
    static boolean[][] c = new boolean[SIZE][SIZE + 1];
    static boolean[][][] b = new boolean[SIZE][SUBSIZE][SIZE + 1];
    static int fill;
	
	public static void fillPuzzle(int[][] puzzle) {
		
		// Variable(s)
		//int puzzle2D[][];
		TreeSet<Integer> remainingPossibleVal = null;
		TreeSet<Integer> temp;
		int minimum = Integer.MAX_VALUE;
		int minimumCol = -1;
		int minimumRow = -1;

		
		// TEST LINE
		print2D(puzzle);
		System.out.println("");
		
		if (fill == CELLS) {
			print2D(puzzle);
			System.exit(0);
			return;
		}
		
		// Find empty cell with MRV.
		for (int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				if (puzzle[i][j] != 0) {
					continue;
				}
				
				temp = findRemainingVal(i, j);
				
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
			puzzle[minimumRow][minimumCol] = x;
			
			r[minimumRow][x] = true;
			c[minimumCol][x] = true;
			b[minimumRow / SUBSIZE][minimumCol / SUBSIZE][x] = true;
			
			fill++;
			
			fillPuzzle(puzzle);
			
			fill--;
			
			// Reset
			b[minimumRow / SUBSIZE][minimumCol / SUBSIZE][x] = false;
			c[minimumCol][x] = false;
			r[minimumRow][x] = false;
			
			puzzle[minimumRow][minimumCol] = 0;
		}
		
	}
	
	
	
	private static TreeSet<Integer> findRemainingVal(int i, int j) {
		
		// Variable(s)
		int bRow = i / SUBSIZE;
		int bCol = j / SUBSIZE;
		TreeSet<Integer> remVal = new TreeSet<Integer>();
		TreeSet<Integer> possibleVals = new TreeSet<Integer>();
		HashMap<Integer, Integer> rMap = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> cMap = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> bMap = new HashMap<Integer, Integer>();
		
		for (int x = 1; x < SIZE; x++) {
			if (!r[i][x]) {
				rMap.put(x, x);
				remVal.add(x);
			}
			
			if (!c[j][x]) {
				cMap.put(x, x);
				remVal.add(x);
			}
			
			if (!b[bRow][bCol][x]) {
				bMap.put(x, x);
				remVal.add(x);
			}
		}
		
		for (Integer val:remVal) {
			if (rMap.get(val) != null && cMap.get(val) != null && bMap.get(val) != null) {
				possibleVals.add(val);
			}
		}
		
		return possibleVals;
	}



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
	
	  public static void print2D(int[][] puzzle){
		    for(int i = 0; i < SIZE; i++){
		      for(int j = 0; j < SIZE; j++){
		        System.out.print(puzzle[i][j] + " ");
		      }
		      System.out.println();
		    }
		  }
}
