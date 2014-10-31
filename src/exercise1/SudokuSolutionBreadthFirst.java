package exercise1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SudokuSolutionBreadthFirst {
	private Queue<SudokuState> availableStates;
	private SudokuState objective;
	private SudokuState initial;
	private List<SudokuState> visited;
	
	public SudokuSolutionBreadthFirst(){
		availableStates = new LinkedList<SudokuState>();
		visited = new ArrayList<SudokuState>();
	}
	
	public SudokuSolutionBreadthFirst(SudokuState initial, SudokuState objective){
		this();
		this.initial = initial;
		this.objective = objective;
	}
	
	public List<SudokuState> execute(){
		boolean found = false;
		availableStates.offer(initial);
		SudokuState current = null;
		while(!availableStates.isEmpty() && !found ){
			current = availableStates.poll();
			if(current.equals(objective)){
				found = true;
				break;
			}else{
				expandNextStates(current);
				visited.add(current);
			}
		}
		if(found && current != null){
			List <SudokuState> steps = new ArrayList<SudokuState>();
			getPreviousStates(steps, current);
			return steps;
		}
		return null;
	}
	
	private void getPreviousStates(List<SudokuState> states, SudokuState current){
		if(current.previous != null){
			getPreviousStates(states, current.previous);
		}
		states.add(current);
	}
	private void expandNextStates(SudokuState currentState){
		int[][]board = currentState.board;
		int currentI = 0;
		int currentJ = 0;
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board.length; j++ ){
				if(board[i][j] == 0){
					currentI = i;
					currentJ = j;
					break;
				}
			}
		}
		if(currentI - 1 >= 0){
			SudokuState newState = newAvailableState(currentState, currentI,currentJ, currentI - 1, currentJ);
			if(!visited.contains(newState)){
				availableStates.offer(newState);
			}
		}
		if(currentJ + 1 < board.length){
			SudokuState newState = newAvailableState(currentState, currentI, currentJ, currentI, currentJ + 1);
			if(!visited.contains(newState)){
				availableStates.offer(newState);
			}
		}
		if(currentI + 1 < board.length){
			SudokuState newState = newAvailableState(currentState, currentI, currentJ, currentI + 1, currentJ);
			if(!visited.contains(newState)){
				availableStates.offer(newState);
			}
		}
		if(currentJ - 1 >= 0){
			SudokuState newState = newAvailableState(currentState, currentI, currentJ, currentI, currentJ - 1);
			if(!visited.contains(newState)){
				availableStates.offer(newState);
			}
		}
	}

	private SudokuState newAvailableState(SudokuState current, int currentI,int currentJ, int newI, int newJ) {
		int[][] newBoard = new int[current.board.length][];
		for(int i = 0; i < newBoard.length; i++){
			newBoard[i] = Arrays.copyOf(current.board[i], current.board[i].length);
		}
		int currentBlank = newBoard[currentI][currentJ];
		newBoard[currentI][currentJ] = newBoard[newI][newJ];
		newBoard[newI][newJ] = currentBlank;
		return new SudokuState(newBoard, current.cost + 1, current);
	}
}
