package exercise1;

import java.util.Date;
import java.util.List;

public class Program {
	public static void main(String[] args) {
		int[][] initialBoard = {{6,4,2},{8,1,3},{7,5,0}};
		int[][] objectiveBoard = {{0,1,2},{3,4,5},{6,7,8}};
		SudokuState initial = new SudokuState(initialBoard, 0, null);
		SudokuState objective = new SudokuState(objectiveBoard, 0, null);
		
		SudokuSolutionBreadthFirst solution = new SudokuSolutionBreadthFirst(initial, objective);
		System.out.println(new Date());
		List<SudokuState> steps = solution.execute();
		for (SudokuState sudokuState : steps) {
			System.out.println(sudokuState.toString());
		}
		System.out.println("steps: " + steps.size());
		System.out.println(new Date());
	}
}
