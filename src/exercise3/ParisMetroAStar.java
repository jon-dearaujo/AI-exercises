package exercise3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class ParisMetroAStar {
	private static final int MINUTES_WHEN_CHANGE_LINE = 5;
	private Queue<State> states;
	private List<State> visited;
	private State initial;
	private State objective;

	private ParisMetroAStar(){
		states = new LinkedList<>();
		visited = new ArrayList<>();
	}
	
	ParisMetroAStar(State initial, State objective){
		this();
		this.initial = initial;
		this.objective = objective;
	}
	
	public List<State> start(){
		initial.estimatedRemainderCost = StationsManager
			.getInLineEstimatedDistanceBetweenStations(initial.nEstation, objective.nEstation);
		states.offer(initial);
		boolean found = false;
		State current = null;
		while(!states.isEmpty() && !found){
			current = states.poll();
			if(!visited.contains(current)){
				if(current.estimatedRemainderCost == 0){
					found = true;
				}else{
					generateNextStates(current);
				}
				visited.add(current);
			}
		}
		if(found){
			List<State> steps = new ArrayList<>();
			getStepsToSolution(current, steps);
			return steps;
		}else{
			return null;
		}
	}

	private void getStepsToSolution(State state, List<State> steps) {
		if(state.previous != null)
			getStepsToSolution(state.previous, steps);
		steps.add(state);
	}

	private void generateNextStates(State state) {
		List<Integer> connected = StationsManager.
			getConnectedStations(state.nEstation);
		State newState = null;
		for(Integer stationNumber : connected){
			newState = new State();
			newState.nEstation = stationNumber;
			newState.previous = state;
			newState.estimatedRemainderCost = StationsManager
				.getInLineEstimatedDistanceBetweenStations(newState.nEstation, objective.nEstation);
			newState.pointCost = state.pointCost + StationsManager
				.getDistanceOfConnectedStations(state.nEstation, newState.nEstation);
			List<Line> linesOfNewState = StationsManager.getLinesOfStation(newState.nEstation);
			if(linesOfNewState.contains(state.line)){
				newState.line = state.line;
			}else{
				List<Line> linesOfOldState = StationsManager.getLinesOfStation(state.nEstation);
				newState.line = linesOfNewState.stream().
					filter(line -> linesOfOldState.contains(line)).findFirst().get();
				newState.pointCost += MINUTES_WHEN_CHANGE_LINE;
			}
			states.offer(newState);
		}
//		sort the states after add new states
		sortStates();
	}

	private void sortStates() {
		states = states.stream().sorted((state1,state2)->{
			int heuristicValueLine1 = state1.pointCost + state1.estimatedRemainderCost;
			int heuristicValueLine2 = state2.pointCost + state2.estimatedRemainderCost;
			if(heuristicValueLine1 < heuristicValueLine2)
				return -1;
			else if(heuristicValueLine1 == heuristicValueLine2)
				return 0;
			return 1;
		}).collect(Collectors.toCollection(()->new LinkedList<State>()));
	}
}
