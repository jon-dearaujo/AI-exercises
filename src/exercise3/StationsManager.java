package exercise3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StationsManager {
	public static final int MINUTES_TO_MOVE_ONE_KILOMETER = 2;
	
	private static Map<Integer, List<Line>> stationsLines;
	
	static{
//		structure user to store the data about the lines on the stations are in
		stationsLines = new HashMap<>();
		stationsLines.put(1, Arrays.asList(Line.BLUE));
		stationsLines.put(2, Arrays.asList(Line.BLUE, Line.YELLOW));
		stationsLines.put(3, Arrays.asList(Line.BLUE, Line.RED));
		stationsLines.put(4, Arrays.asList(Line.BLUE, Line.GREEN));
		stationsLines.put(5, Arrays.asList(Line.BLUE, Line.YELLOW));
		stationsLines.put(6, Arrays.asList(Line.BLUE));
		stationsLines.put(7, Arrays.asList(Line.YELLOW));
		stationsLines.put(8, Arrays.asList(Line.GREEN, Line.YELLOW));
		stationsLines.put(9, Arrays.asList(Line.RED, Line.YELLOW));
		stationsLines.put(10, Arrays.asList(Line.YELLOW));
		stationsLines.put(11, Arrays.asList(Line.RED));
		stationsLines.put(12, Arrays.asList(Line.GREEN));
		stationsLines.put(13, Arrays.asList(Line.RED, Line.GREEN));
		stationsLines.put(14, Arrays.asList(Line.GREEN));
	}
	
	private static int[][] inLineDistances = {
		{0,11,20,27,40,43,39,28,18,10,18,30,30,32},
		{11,0,9,16,29,32,28,19,11,4,17,23,21,24},
		{20,9,0,7,20,22,19,15,10,11,21,21,13,18},
		{27,16,7,0,13,16,12,13,13,18,26,21,11,17},
		{40,29,20,13,0,3,2,21,25,31,38,27,16,20},
		{43,32,22,16,3,0,4,23,28,33,41,30,17,20},
		{39,28,19,12,2,4,0,22,25,29,38,28,13,17},
		{28,19,15,13,21,23,22,0,9,22,18,7,25,30},
		{18,11,10,13,25,28,25,9,0,13,12,12,23,28},
		{10,4,11,18,31,33,29,22,13,0,20,27,20,23},
		{18,17,21,26,38,41,38,18,12,20,0,15,35,39},
		{30,23,21,21,27,30,28,7,12,27,15,0,31,37},
		{30,21,13,11,16,17,13,25,23,20,35,31,0,5},
		{32,24,18,17,20,20,17,30,28,23,39,37,5,0}
	};
	private static String[][] directConnections = {
		{"-","11","-","-","-","-","-","-","-","-","-","-","-","-"},
		{"-","-","9","-","-","-","-","-","11","4","-","-","-","-"},
		{"-","-","-","7","-","-","-","-","10","-","-","-","19","-"},
		{"-","-","-","-","14","-","-","16","-","-","-","-","12","-"},
		{"-","-","-","-","-","3","2","33","-","-","-","-","-","-"},
		{"-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
		{"-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
		{"-","-","-","-","-","-","-","-","10","-","-","7","-","-"},
		{"-","-","-","-","-","-","-","-","-","-","14","-","-","-"},
		{"-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
		{"-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
		{"-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
		{"-","-","-","-","-","-","-","-","-","-","-","-","-","5"},
		{"-","-","-","-","-","-","-","-","-","-","-","-","-","-"},
	};
	
	/**
	 * Return a list containing the stations that are directly connected with the 
	 * station passed as parameter
	 * @param nEstation
	 * @return
	 */
	public static List<Integer> getConnectedStations(int nEstation) {
		String[] stations = directConnections[nEstation-1];
		List<Integer>connected = new ArrayList<>();
		for(int i = 0; i < stations.length; i++){
			if(!stations[i].equals("-")){
				connected.add(i+1);
			}
		}
		for(int i = 0; i < directConnections.length; i++){
			if(i != nEstation - 1){
				if(!directConnections[i][nEstation - 1].equals("-")){
					connected.add(i + 1);
				}
			}
		}
		return connected;
	}
	
	/**
	 * Return the inline estimated distance between two stations. 
	 * This stations may be not directly connected
	 * @param source
	 * @param destination
	 * @return
	 */
	public static int getInLineEstimatedDistanceBetweenStations(int source, int destination) {
		return inLineDistances[source - 1][destination - 1] * MINUTES_TO_MOVE_ONE_KILOMETER ;
	}
	
	/**
	 * Get the distance between two connected stations. 
	 * @param source
	 * @param destination
	 * @return
	 */
	public static int getDistanceOfConnectedStations(int source, int destination) {
		String distance = directConnections[source -1][destination - 1];
		if(distance.equals("-")){
			distance = directConnections[destination -1][source - 1];
		}
		return Integer.parseInt(distance) * MINUTES_TO_MOVE_ONE_KILOMETER;
	}

	public static List<Line> getLinesOfStation(int nEstation) {
		return stationsLines.get(nEstation);
	}
}
