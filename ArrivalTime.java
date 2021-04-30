import java.time.LocalTime;

public class ArrivalTime 
{
	private LocalTime arrivalTime;
	private int tripIndex;
	
	public ArrivalTime(LocalTime arrivalTime, int tripIndex) 
	{
		this.arrivalTime = arrivalTime;
		this.tripIndex = tripIndex;
	}
	
	public LocalTime getArrivalTime() 
	{
		return arrivalTime;
	}
	
	public int getTripIndex() 
	{
		return tripIndex;
	}
}
