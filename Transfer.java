import java.time.LocalTime;

public class Transfer
{
	private int tripId;
	private LocalTime arrivalTime;
	private LocalTime departureTime;
	private int stopId;
	private int stopSequence; 
	private int pickupType; 
	private int dropOffType; 
	private double distTravelled;
	private int tripIndex;
	
	public Transfer(int tripIndex, int tripId, LocalTime arrivalTime, LocalTime departureTime,int stopId,
			int stopSequence,int pickupType,int dropOffType,
			double distTravelled) 
	{
		this.tripIndex = tripIndex;
		this.tripId = tripId;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.stopId = stopId;
		this.stopSequence = stopSequence;
		this.pickupType = pickupType; 
		this.dropOffType = dropOffType; 
		this.distTravelled = distTravelled; 
	}
	
	public int getTripId() 
	{
		return tripId;
	}
	
	public LocalTime getArrivalTime() 
	{
		return arrivalTime;
	}
	
	public LocalTime getDepartureTime()
	{
		return departureTime;
	}
	
	public int getStopId() 
	{
		return stopId;
	}
	
	public int getStopSequence() 
	{
		return stopSequence;
	}
	
	public int getPickupType() 
	{
		return pickupType;
	}
	
	public int getDropOffType() 
	{
		return dropOffType;
	}
	
	public double getDistTravelled() 
	{
		return distTravelled;
	}
	
	public int getTripIndex() {
		return tripIndex;
	}
	
	public String toString() 
	{
		return "\n Stop ID: " + getStopId() +", Arrival Time: " + getArrivalTime() + 
				", Departure Time: " + getDepartureTime()  + ", Distance travelled: " 
				+ getDistTravelled() ;
	}
	
}
