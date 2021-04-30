import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class TripSearch 
{

	static DateTimeFormatter parseFormat = DateTimeFormatter.ofPattern("H:mm:ss");
	
	public static boolean isValid(String time) 
	{
		try {
            parseFormat.parse(time);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
	
	
	public static void getUserInput(ArrayList<ArrivalTime> arrivals, ArrayList<Trip> trips, LocalTime time) 
	{
		ArrayList<Trip> match = new ArrayList<Trip>();
		for(ArrivalTime arrival:arrivals) 
		{
			if(arrival.getArrivalTime().compareTo(time) == 0) 
			{
				match.add(trips.get(arrival.getTripIndex()));
			}
		}
		
		if(match.size() == 0) 
		{
			System.err.println("There are no trips that match that arrival time. Please try again!");
		}
		else 
		{
			System.out.println("There " + ((match.size() == 1)? "is one trip " : "are " +  match.size() + " trips") 
			+ " that match that arrival time."); 
			
			match.sort(Comparator.comparingInt(Trip::getID));
			
			for(Trip trip:match) 
			{
				trip.printTrip();
			}
		}
		
		
	}
	
	


		
		
	}


