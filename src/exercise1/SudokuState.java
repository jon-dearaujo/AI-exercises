package exercise1;

import java.util.Arrays;

public class SudokuState {
	
	public SudokuState() {	}
	
	public SudokuState(int[][]board , int cost, SudokuState previous){
		this.board = board;
		this.cost = cost;
		this.previous = previous;
	}
	
	int[][] board;
	int cost;
	SudokuState previous = null;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SudokuState other = (SudokuState) obj;
		if (!Arrays.deepEquals(board, other.board))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for( int i = 0; i < board.length; i++){
			for(int j = 0; j < board.length; j++){
				builder.append('|');
				builder.append(board[i][j]);
			}
			builder.append('|');
			builder.append("\n");
		}
		builder.append("\n");
		return builder.toString();
	}
}
