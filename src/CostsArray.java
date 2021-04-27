import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class CostsArray
{
	public static double Costs[][]; 
	
	// Run this Method and It will run the rest of the methods in this class
	// Then call the costs array, and copy it across to a new array in the project (like i have shown in the Main Method at the bottom )
	public static void CostsArray(String filename, String stops, String transfers)
    {
		int dim = MaxStopNumber(stops);
		Costs = new double [dim+1][dim+1];
		
		for(int a = 0; a <= dim; a++)
    	{
    		for(int b = 0; b <= dim; b++)
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
		
		
			while (count < 10) 
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
	
	
	 public static void main(String[] args)
	    {
	    	
	    	String fileInput = "src/stops.txt";
	    	String filename = "src/stop_times.txt";
	    	String transfers = "src/transfers.txt";
	    	
	    	
	    	
	    	CostsArray map = null;
	    	
	    	
	    	map.CostsArray(filename, fileInput, transfers);
	    	
	    	
	    	
	    	double [][] test = map.Costs;
	    	
	    	//Test Stop_times worked
	    	double testFirstValue = test[378][379];
	    	
	    	//Test transfer worked
	    	double testTransferCost = test[1477][1394];
	    	
	    	double infinite = test[1][2];
	    	
	    	
	    	System.out.println(testFirstValue);
	    	System.out.println(testTransferCost);
	    	System.out.println(infinite);
	    }
}
