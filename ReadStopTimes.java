import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException; 
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ReadStopTimes
{
	private static DateTimeFormatter parseFormat = DateTimeFormatter.ofPattern("H:mm:ss");
	private static ArrayList <ArrivalTime> arrivalsList = new ArrayList<ArrivalTime>();
	private static ArrayList <Trip> tripList = new ArrayList<Trip>();
	
	
    public ReadStopTimes() throws IOException
    {
    	try 
		{
			FileReader fr = new FileReader("stop_times.txt");
			BufferedReader br = new BufferedReader(fr);
			
			String tripInfo = br.readLine();
			tripInfo = br.readLine();
			Trip currentTrip = null;
			int tripIndex = -1;
			
			while(tripInfo != null) 
			{
				String[] tripSplit = tripInfo.trim().split(",\\s+|,");
				String arrivalTimeTemp = tripSplit[1];
				String depTimeTemp = tripSplit[2];
				
				
				if(isValid(arrivalTimeTemp, depTimeTemp)) 
				{
					int tripId = Integer.parseInt(tripSplit[0]);
					
					if(currentTrip == null || currentTrip.getID() != tripId) 
					{
						tripIndex++;
						Trip newTrip = new Trip(tripId);
						tripList.add(newTrip);
						currentTrip = newTrip;
					}
					
					LocalTime arrivalTime = LocalTime.parse(tripSplit[1], parseFormat);
					LocalTime departureTime = LocalTime.parse(tripSplit[2], parseFormat);
					int stopId = Integer.parseInt(tripSplit[3]);
					int stopSequence = Integer.parseInt(tripSplit[4]);
					int pickupType = Integer.parseInt(tripSplit[6]);
					int dropOffType = Integer.parseInt(tripSplit[7]);
					double distTravelled = (tripSplit.length == 9 ? Double.parseDouble(tripSplit[8]): 0.0); 
					
					Transfer newTransfer = new Transfer(tripIndex,tripId, arrivalTime, departureTime, stopId,
							stopSequence, pickupType, dropOffType, distTravelled);
					ArrivalTime newArrivalTime = new ArrivalTime(arrivalTime, tripIndex);
					
					arrivalsList.add(newArrivalTime);
					currentTrip.add(newTransfer);
				}
				
				tripInfo = br.readLine();
			}
			br.close();
		}catch(FileNotFoundException e) {
			System.out.print("File Not Found");
		}
    }
    
    public static boolean isValid(String time, String time2) 
	{
		try 
		{
            parseFormat.parse(time);
            parseFormat.parse(time2);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
    
    public ArrayList<ArrivalTime> getArrivalsList()
    {
    	return arrivalsList;
    }
    
    public ArrayList<Trip> getTripsList()
    {
    	return tripList;
    }
	
}
