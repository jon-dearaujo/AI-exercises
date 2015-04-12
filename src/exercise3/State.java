package exercise3;

public class State {
	State(){
		this.nEstation = 0;
		this.line = null;
		pointCost = 0;
		estimatedRemainderCost = 0;
		previous = null;
	}
	State(int nEstation, Line line){
		this();
		this.nEstation = nEstation;
		this.line = line;
		pointCost = 0;
		estimatedRemainderCost = 0;
		previous = null;
	}
	
	public int nEstation;
	public Line line;
	public int pointCost;
	public int estimatedRemainderCost;
	public State previous;
}
