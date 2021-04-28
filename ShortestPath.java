//similar to https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/
import java.io.*;
import java.util.*;

public class ShortestPath {
	
	double adjMatrix[][] = new double[12479][12479];
	String stopTimes, transfers;
	
	ShortestPath(String stopTimes, String transfers) {
		
		this.stopTimes = stopTimes;
		this.transfers = transfers;		
	
	}
	
	
	
	public void AdjMatrix() throws FileNotFoundException {//creates adjacency matrix
		
		for(int i = 0; i < adjMatrix.length; i++) {//here we initialize our matrix to positive infinity
			
			for(int j = 0; j < adjMatrix[i].length; j++) {
				
				if(i != j) {
					
					adjMatrix[i][j] = Double.POSITIVE_INFINITY; 
				
				}
				else {
					
					adjMatrix[i][j] = 0;
				
				}
			
			}
		
		}
		
		File stopTimesText = new File(stopTimes);
		Scanner scanner1 = new Scanner(stopTimesText);//used to scan and parse lines in the text file
		Scanner scanner2 = null;
		scanner1.nextLine();//skips first line of text file
		
		int source = 0;
		int destination = 0;
		int tripId = 0;
		int tripId2 = 0;
		double cost = 1;//all edges from stop times have a cost of 1
		String line;
		
		while(scanner1.hasNextLine()) {
			
			line = scanner1.nextLine();
			scanner2 = new Scanner(line);
			scanner2.useDelimiter(",");//specifies the comma as the boundary between strings when scanning
			tripId = tripId2;
			tripId2 = scanner2.nextInt();
			scanner2.next();
			scanner2.next();
			source = destination;
			destination = scanner2.nextInt();
			
			if(tripId == tripId2) {
				
				adjMatrix[source][destination] = cost;
			
			}
			scanner2.close();
		
		}
		scanner1.close();
		
		double minimumTime;
		int transferType; 
		File transfersText = new File(transfers);
		scanner1 = new Scanner(transfersText);
		scanner1.nextLine();//need to skip first line of text file again
		
		//we scan through the transfer text file and define connections between stops
		while(scanner1.hasNextLine()) {
			
			line = scanner1.nextLine();
			scanner2 = new Scanner(line);
			scanner2.useDelimiter(",");//again used to define boundary between strings
			
			source = scanner2.nextInt();
			destination = scanner2.nextInt();
			transferType = scanner2.nextInt();
			
			if(transferType == 0) { //transfers of type 0 have a cost of 2
				
				adjMatrix[source][destination] = 2;
			
			}
			else if(transferType == 2) {//transfers of type 2 have a cost of the minimum time of the transfer divided by 100
				
				minimumTime = scanner2.nextDouble();
				adjMatrix[source][destination] = minimumTime / 100;
			
			}
			scanner2.close();
		
		}
		scanner1.close();
	
	}
	
	
	
	
	
	//implements Dijkstra's shortest path algorithm for a network represented by an adjacency matrix
	public String Dijkstra(int source, int destination){
		
		if(source == destination) {
			
			return adjMatrix[source][destination] + ". You have entered your current stop ("+source+") as your destination.";
		
		}
		
		double dist[] = new double[adjMatrix.length];
		int edge[] = new int[adjMatrix.length];
		int visit[] = new int[adjMatrix.length];
   
    	for(int i = 0; i < dist.length; i++) {//initialises all stops to positive infinity except for source vertex
    		
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
    		
    		for(int i = 0; i < adjMatrix[currentStop].length; i ++) { //before setting a stop as having been visited, we relax its edges
    			
    			if(!Double.isInfinite(adjMatrix[currentStop][i]) && visit[i] == 0) {
    				
        			relaxEdge(currentStop, i, dist, edge);
        		
    			}
    		
    		}
    		visit[currentStop] = 1;
    		 
    		double closest = Integer.MAX_VALUE;
    		for(int i = 0; i < dist.length; i++) {
    			
    			if(visit[i] != 1 && closest > dist[i]) {
    				
    				currentStop = i;
    				closest = dist[i];
    			
    			}
    		
    		}
    		
    		numberStopsVisited++;
    	}
   
    	if(dist[destination] == Double.POSITIVE_INFINITY) {//if the distance to the destination is still at its initial value of positive infinity, a path doesn't exist
    		
    		return "A path between these stops does not exist.";
    	
    	}
   
    	int src = source;
    	int dest = destination;
    	String path = "";
    	
    	while(dest != src) {//here we list the stops enroute to our destination if a path exists
    		
    		path = edge[dest] + " " + path;
    		dest = edge[dest];
    	
    	}
    	
    	path = path + " " + destination;
    	return dist[destination] + "\nShortest Path: " + path;
    }
	
	
	
	
	
    public void relaxEdge(int source, int destination, double[] dist, int[] edge) {
    	
    	if(dist[destination] > dist[source] + adjMatrix[source][destination]) {
    		
    		dist[destination] = dist[source] + adjMatrix[source][destination];
    		edge[destination] = source;
    	
    	}
    }
    
    
    
    
	public static void main(String[] args) {
		
		ShortestPath network = new ShortestPath("C:\\Users\\ciara\\eclipse-workspace\\Dijkstra\\stop_times.txt", "C:\\Users\\ciara\\eclipse-workspace\\Dijkstra\\transfers.txt");
		
		try {
			network.AdjMatrix();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println("Shortest distance from Stop(122) to Stop(654): " + network.Dijkstra(122, 654));//returns a valid path between the two stops
		System.out.println("Shortest distance from Stop(5191) to Stop(5191): " + network.Dijkstra(5191, 5191));//returns 0 as the source is the same as the destination
		System.out.println("Shortest distance from Stop(94) to Stop(8325): " + network.Dijkstra(94, 8325));//these stops are not connected so no path exists
	}
}

