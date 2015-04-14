package exercise2;

import java.util.List;

public class Program {
	public static void main(String[] args) {
		int[] initialPositions = {0,1,2,3,4,5,6,7};
		 List<State> steps = new EightQueensGreedySearch(initialPositions).execute();
		 for(State step : steps)
			 System.out.println(step);
		 System.out.println("COST: " + steps.size());
	}
}
