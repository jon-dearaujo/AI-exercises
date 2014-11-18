package exercise2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class EightQueensGreedySearch {
	private LinkedList<State> states;
	private List<State> visited;
	private State initial;
	
	public EightQueensGreedySearch(int[] initialPositions) {
		initial = new State(initialPositions, 42, 0, null);
		visited = new ArrayList<>();
		states = new LinkedList<>();
	}
	
	public List<State> execute(){
		states.offer(initial);
		boolean found = false;
		State current = null;
		while(!states.isEmpty() && !found){
			current = states.poll();
			if(!visited.contains(current)){
				if(current.heuristic == 0)
					found = true;
				else{
					expandState(current);
					Collections.sort(states,
						(s1, s2) ->{
							if(s1.heuristic < s2.heuristic)
								return -1;
							if(s1.heuristic == s2.heuristic)
								return 0;
							return 1;
						}
					);
				}
			}
			visited.add(current);
		}
		if(found){
			List<State> steps = new ArrayList<>();
			getSolutionSteps(steps, current);
			return steps;
		}
		return new ArrayList<State>();
	}
	
	private void getSolutionSteps(List<State>steps, State state){
		if(state.previous != null)
			getSolutionSteps(steps, state.previous);
		steps.add(state);
	}
	
	private void expandState(State state){
		for(int i = 0; i < state.positions.length; i++){
			createPossiblePositions(state, i);
		}
	}
	
	private void createPossiblePositions(State state, int index){
		for(int i = 0; i < state.positions.length; i++){
			if(i != state.positions[index]){
				int[] newPositions = Arrays.copyOf(state.positions, state.positions.length);
				newPositions[index] = i;
				int heuristic = calculateHeuristic(newPositions);
				State newState = new State(newPositions, heuristic, state.cost + 1, state);
				states.offer(newState);
			}
		}
	}
	
	private int calculateHeuristic(int[] positions){
		int attacks = 0;
		for(int i = 0; i < positions.length; i++){
			if(attackInSameLine(positions, i))
				attacks++;
			if(attackInRightBottonDiagonal(positions, i))
				attacks++;
			if(attacksInRightTopDiagonal(positions, i))
				attacks++;
		}
		return attacks;
	}
	
	private boolean attackInSameLine(int[]positions, int index){
		int[] positionsAtRight = Arrays.copyOfRange(positions, index + 1, positions.length);
		return Arrays.stream(positionsAtRight).anyMatch(line -> line == positions[index]);
	}
	
	private boolean attackInRightBottonDiagonal(int[] positions, int index){
		int[] positionsAtRight = Arrays.copyOfRange(positions, index + 1, positions.length);
		int linePositionValue = positions[index];
		for(int i = 0; i < positionsAtRight.length; i++){
			if(linePositionValue < positions.length && positionsAtRight[i] == ++linePositionValue)
				return true;
		}
		return false;
	}
	
	private boolean attacksInRightTopDiagonal(int[]positions, int index){
		int[] positionsAtRight = Arrays.copyOfRange(positions, index + 1, positions.length);
		int linePositionValue = positions[index];
		for(int i = 0; i < positionsAtRight.length; i++){
			if(linePositionValue > 0 && positionsAtRight[i] == --linePositionValue)
				return true;
		}
		return false;
	}
}
