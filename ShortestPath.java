import java.io.*;
import java.util.*;

public class ShortestPath {
	
	String stopTimes, transfers;
	
	ShortestPath(String stopTimes, String transfers) {
		
		this.stopTimes = stopTimes;
		this.transfers = transfers;		
	
	}
	
	//implements Dijkstra's shortest path algorithm for a network represented by an adjacency matrix
	public String Dijkstra(int source, int destination){
		
		if(source == destination) {
			
			return Costs[source][destination] + ". You have entered your current stop ("+source+") as your destination.";
		
		}
		
		double dist[] = new double[Costs.length];
		int edge[] = new int[Costs.length];
		int visit[] = new int[Costs.length];
   
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
    		
    		for(int i = 0; i < Costs[currentStop].length; i ++) { //before setting a stop as having been visited, we relax its edges
    			
    			if(!Double.isInfinite(Costs[currentStop][i]) && visit[i] == 0) {
    				
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
		System.out.println(testVal);
		
		System.out.println("Shortest distance from Stop(122) to Stop(654): " + network.Dijkstra(122, 654));//returns a valid path between the two stops
		System.out.println("Shortest distance from Stop(5191) to Stop(5191): " + network.Dijkstra(5191, 5191));//returns 0 as the source is the same as the destination
		System.out.println("Shortest distance from Stop(94) to Stop(8325): " + network.Dijkstra(94, 8325));//these stops are not connected so no path exists
	}
}

