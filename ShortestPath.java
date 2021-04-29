import java.io.*;
import java.util.*;

public class ShortestPath {
	
	//double adjMatrix[][] = new double[12479][12479];
	static double Costs[][] = new double[12479][12479];
	
	static boolean[] DoesStopExist = new boolean[12479];
	
	public static double Distance;

	
	
	String stopTimes, transfers;
	
	ShortestPath(String stopTimes, String transfers) {
		this.stopTimes = stopTimes;
		this.transfers = transfers;		
	}
	
	
	
	// Run this Method and It will run the rest of the methods in this class
	// Then call the costs array, and copy it across to a new array in the project (like i have shown in the Main Method at the bottom )
	public static void CostsArray(String filename, String stops, String transfers)
    {
		int dim = MaxStopNumber(stops);
		//Costs = new double [dim+1][dim+1];
		
		
		for(int a = 0; a < 12479; a++)
    	{
    		for(int b = 0; b < 12479; b++)
    		{
    			if(a != b) {
    			Costs[a][b] = Double.MAX_VALUE;
    			}
    			 
    		}
    	}
    	
		transfers_Costs(filename,stops,transfers);
		
		
		
    }
	
	public static int MaxStopNumber(String filename)
	{
		
		
		try 
		{
			File StringInput = new File(filename);
    		Scanner scanner = new Scanner(StringInput);
    		
    		String line = null;
    		int Max = 0;
    		int tempMax = 0;
    		scanner.nextLine();
    		String kept = null;
    		
			while (scanner.hasNextLine()) {
				
			   line = scanner.nextLine();
			   kept = line.substring( 0, line.indexOf(","));
			    
			   tempMax = Integer.parseInt(kept);
			   
			   DoesStopExist[tempMax] = true;
			   
			   if(tempMax > Max) {
				   Max = tempMax;
			   }
			    
			}
			
			return Max;
		}
		catch(Exception e) 
		{
			return 1;
    	}
		
	}
	// Run this first as it initializes the Costs array's dimension.
	public static void Stop_Times_Costs(String filename, String stops)
	{
		
		int dim = MaxStopNumber(stops);
		
		//Costs = new double [dim+1][dim+1];
		
		try 
		{
			File StringInput = new File(filename);
    		Scanner scanner = new Scanner(StringInput);
    		Scanner scanner2 = new Scanner(StringInput);
    		
    		String line = null;
    		int Max = 0;
    		int tempMax = 0;
    		scanner.nextLine();
    		scanner2.nextLine();
    		scanner2.nextLine();
    		
    		String tripID = null;
    		int tripIDInt = 0;
    		
    		String tripID2 = null;
    		int tripIDInt2 = 0;
    		
    		String remainder = null;
    		
    		String line2 = null;
    		
    		int count = 0;
    		
    		String stopID = null;
    		String stopID2 = null;
    		int stopIDInt = 0;
    		int stopID2Int = 0;
    		
    		while (scanner2.hasNextLine()) 
    		{
    		
    		line = scanner.nextLine();
    		tripID = line.substring(0, line.indexOf(","));
    		line2 = scanner2.nextLine();
    		tripID2 = line2.substring(0, line2.indexOf(","));
    		
    		tripIDInt = Integer.parseInt(tripID);
    		tripIDInt2 = Integer.parseInt(tripID2);
    		
    		if(tripIDInt == tripIDInt2) 
    		{
    			stopID = line.substring(line.indexOf(",")+1, line.length());
    			stopID = stopID.substring(stopID.indexOf(",")+1, stopID.length());
    			stopID = stopID.substring(stopID.indexOf(",")+1, stopID.length());
    			stopID = stopID.substring(0, stopID.indexOf(","));
    			stopIDInt = Integer.parseInt(stopID);
    			
        		
    			stopID2 = line2.substring(line2.indexOf(",")+1, line2.length());
    			stopID2 = stopID2.substring(stopID2.indexOf(",")+1, stopID2.length());
    			stopID2 = stopID2.substring(stopID2.indexOf(",")+1, stopID2.length());
    			stopID2 = stopID2.substring(0, stopID2.indexOf(","));
    			stopID2Int = Integer.parseInt(stopID2);
    			
    			Costs[stopIDInt][stopID2Int] = 1;
    			
    		}
    		
    		
    		
    		/*
    		System.out.println(tripID);
    		remainder = line.substring(line.indexOf(",")+1, line.length());
    		remainder = remainder.substring(remainder.indexOf(",")+1, remainder.length());
    		remainder = remainder.substring(remainder.indexOf(",")+1, remainder.length());
    		remainder = remainder.substring(0, remainder.indexOf(","));
    		System.out.println(remainder);
    		tempMax = Integer.parseInt(tripID);
    		*/
    		
    		
    		}
    		
			
		}
		catch(Exception e) 
		{
			
			
    	}
	}
	
	public static void transfers_Costs(String filename, String stops, String transfers)
	{
		
		Stop_Times_Costs(filename, stops);
		
		try
		{
		
		File StringInput = new File(transfers);
		Scanner scanner = new Scanner(StringInput);
		
		String line = null;
		String lineTemp = null;
		String lineTimeTemp = null;
		
		scanner.nextLine();
		
		int count = 0;
		
		int ID1 = 0;
		String ID1String = null;
		int ID2 = 0;
		String ID2String = null;
		
		int time = 0;
		String timeString = null;
		
		double timeTemp = 0;
		
		
			while (scanner.hasNextLine()) 
			{
			
			line = scanner.nextLine();
			ID1String = line.substring(0, line.indexOf(","));
			ID1 = Integer.parseInt(ID1String);
			//System.out.println(ID1);
			
			line = line.substring(line.indexOf(",")+1, line.length());
			lineTemp = line;
			lineTemp = lineTemp.substring(lineTemp.indexOf(",")+1, lineTemp.length());
			lineTimeTemp = lineTemp;
			
			line = line.substring(0, line.indexOf(","));
			lineTemp = lineTemp.substring(0, lineTemp.indexOf(","));
			ID2String = line;
			timeString = lineTemp;
			
			ID2 = Integer.parseInt(ID2String);
			time = Integer.parseInt(timeString);
			
			
			
			//System.out.println(line);
			//System.out.println(lineTemp);
			
			if(time == 0) 
			{
				Costs[ID1][ID2] = 2;
			}
			else 
			{
				lineTimeTemp = lineTimeTemp.substring(lineTimeTemp.indexOf(",")+1, lineTimeTemp.length());
				lineTimeTemp = lineTimeTemp.substring(0);
				
				timeTemp = Double.parseDouble(lineTimeTemp);
				
				timeTemp = timeTemp/100;
				Costs[ID1][ID2] = timeTemp;
			}
				
			count++;
			}
		
		}
		catch(Exception e) 
		{
			
    	}
	}
	
	
	
	
	
	
	//implements Dijkstra's shortest path algorithm for a network represented by an adjacency matrix
	public String Dijkstra(int source, int destination){
		
		if(source == destination) {
			return Costs[source][destination] + ". You have entered your current stop ("+source+") as your destination.";
		}
		double dist[] = new double[Costs.length];//initialise new 1d arrays the same size as matrix
		int edge[] = new int[Costs.length];
		int visit[] = new int[Costs.length];
   
    	for(int i = 0; i < dist.length; i++) {//initialise all stops to positive infinity except for source node
    		if(i != source)
    		{
    			dist[i] = Double.POSITIVE_INFINITY;
    		}
    	}
    	
    	dist[source] = 0;// distance of source vertex from itself is always 0
    	visit[source] = 1;
    	int currentStop = source;
    	int numberStopsVisited = 0;
    	while(numberStopsVisited < dist.length){
    		//relax the edges pointing from the current node then set it as visited
    		for(int i = 0; i < Costs[currentStop].length; i ++) {
    			if(!Double.isInfinite(Costs[currentStop][i]) && visit[i] == 0) {
        			relaxEdge(currentStop, i, dist, edge);
        		}
    		}
    		visit[currentStop] = 1;
    		
    		//pick the next node that is currently the one with the shortest distance value 
    		//and that has not been yet visited in our network to relax 
    		double shortestDist = Integer.MAX_VALUE;
    		for(int i = 0; i < dist.length; i++) {
    			if(visit[i] != 1 && shortestDist > dist[i]) {
    				currentStop = i;
    				shortestDist = dist[i];
    			}
    		}
    		numberStopsVisited++;
    	}
   
    	if(dist[destination] == Double.MAX_VALUE) {//if the distance to the destination is still at its initial value of positive infinity, a path doesn't exist
    		return "A path between these stops does not exist.";
    	}
    	//here we list the stops enroute to our destination if a path exists
    	int src = source;
    	int dest = destination;
    	String path = "";
    	while(dest != src) {
    		path = edge[dest] + " " + path;
    		dest = edge[dest];
    	}
    	path = path + " " + destination;
    	
    	Distance = dist[destination];
    	
    	return dist[destination] + "\nShortest Path: " + path;
    }
	
	
	
	
	
    public void relaxEdge(int source, int destination, double[] dist, int[] edge) {
    	if(dist[destination] > dist[source] + Costs[source][destination]) {
    		dist[destination] = dist[source] + Costs[source][destination];
    		edge[destination] = source;
    	}
    }
    
    
    
    
	public static void main(String[] args) {
		
		ShortestPath network = new ShortestPath("src/stop_times.txt", "src/transfers.txt");
		String fileInput = "src/stops.txt";
    	String filename = "src/stop_times.txt";
    	String transfers2 = "src/transfers.txt";
		
		
		network.CostsArray(filename, fileInput, transfers2);
		
		double [][] tester = network.Costs;
		
		double testVal = tester[378][379];
		
		boolean [] exist = network.DoesStopExist;
		
		boolean resultExist = exist[122];
		System.out.println(resultExist);
		
		
		System.out.println(testVal);
		
		
		System.out.println("Shortest distance from Stop(122) to Stop(654): " + network.Dijkstra(122, 654));//returns a valid path between the two stops
		System.out.println("Shortest distance from Stop(5191) to Stop(5191): " + network.Dijkstra(5191, 5191));//returns 0 as the source is the same as the destination
		System.out.println("Shortest distance from Stop(94) to Stop(8325): " + network.Dijkstra(94, 8325));//these stops are not connected so no path exists
	}
}

