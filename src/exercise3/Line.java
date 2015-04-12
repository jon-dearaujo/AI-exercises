package exercise3;

public enum Line {
	BLUE("blue"),
	GREEN("green"),
	RED("red"),
	YELLOW("yellow");
	
	String value;
	
	Line(String value){
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
}
