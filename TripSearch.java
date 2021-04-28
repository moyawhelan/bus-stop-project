import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class TripSearch 
{

	private static DateTimeFormatter parseFormat = DateTimeFormatter.ofPattern("H:mm:ss");
	
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
			System.out.println("There are no trips that match that arrival time.");
		}
		else 
		{
			System.out.println("There " + ((match.size() == 1)? "is " : "are " +  match.size()) 
			+ " that match that arrival time."); 
			
			match.sort(Comparator.comparingInt(Trip::getID));
			
			for(Trip trip:match) 
			{
				trip.printTrip();
			}
		}
		
		
	}
	
	
	public static void main(String[] args) throws IOException 
	{
		ReadStopTimes object = new ReadStopTimes();
		
		try {
			boolean finished = false;
			while (!finished) {

				System.out.println("\nPlease enter the arrival time or 'Quit' to exit: ");

				Scanner input = new Scanner(System.in);
				
				if(input.hasNext("Quit") || input.hasNext("quit"))
				{
					System.out.println("Goodbye.");
					finished = true;
					input.close();
				}
				
				else if(input.hasNext())
				{
					String userInput = input.nextLine();
					if(isValid(userInput)) 
					{
						LocalTime time = LocalTime.parse(userInput, parseFormat);
						getUserInput(object.getArrivalsList(), object.getTripsList(), time);
					}
					else 
					{
						System.out.println("This is not a valid input. Please try again or quit.");
					}
				}
				
			}
		}

		catch( NullPointerException exception )
		{

		}
		catch(java.util.NoSuchElementException exception)
		{
			System.err.println("This is not a valid input.");
		}

		
		
	}

}
