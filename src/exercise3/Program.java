package exercise3;

import java.util.List;

public class Program {
	public static void main(String[] args) {
		State initial = new State(12, Line.GREEN);
		State objective = new State(9, Line.BLUE);
		List<State> steps = new ParisMetroAStar(initial, objective).start();
		System.out.println("Solution Steps:");
		for(State step : steps){
			System.out.print("E" +step.nEstation + " ");
		}
		System.out.println("");
		System.out.println("Cost : " + steps.get(steps.size() - 1).pointCost + " minutes");
	}
}
