package exercise2;

import java.util.Arrays;

public class State {
	int[] positions;
	int heuristic;
	int cost;
	State previous;
	
	public State() {}
	
	public State(int[] positions, int heuristic, int cost, State previous){
		this.positions = positions;
		this.heuristic = heuristic;
		this.cost = cost;
		this.previous = previous;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean superEquals =  super.equals(obj);
		if(superEquals)
			return Arrays.equals(positions, ((State)obj).positions);
		return false;
	}
	
	@Override
	public String toString() {
		char[][] board = new char[positions.length][positions.length];
		Arrays.stream(board).forEach(array -> Arrays.fill(array, ' '));
		for(int i = 0; i < positions.length; i++){
			board[positions[i]][i] = 'R';
		}
		StringBuilder builder = new StringBuilder();
		for(int i = 0;i < board.length; i++){
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
