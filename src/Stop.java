
public class Stop {

	private int stopId;
	private long stopCode;
	private String stopName;
	private String stopNameAbbrev;
	private double stopLat;
	private double stopLong;
	private String zoneId;
	private int locationType;
	private int parentStation;

	public Stop (int stopId, long stopCode, String stopName, String stopNameAbbrev, double stopLat, double stopLong, String zoneId, int locationType, int parentStation)
	{
		this.stopId = stopId;
		this.stopCode = stopCode;
		this.stopName = stopName;
		this.stopNameAbbrev = stopNameAbbrev;
		this.stopLat = stopLat;
		this.stopLong = stopLong;
		this.zoneId = zoneId;
		this.locationType = locationType;
		this.parentStation = parentStation;

	}


	public String toString()
	{
		return "Stop ID: " + stopId + "\n" + "Stop Code: " + stopCode + "/n" + "Stop Name: " + stopName + "\n" + "Stop Name Abbreviated: " + stopNameAbbrev+ "Stop Latitude: " + stopLat + "/n" + "Stop Longitude: " + stopLong + "/n" + "Stop Zone ID "+ zoneId + "/n" + "Stop Location Type: " + locationType + "/n" + "Stop Parent Station: " + parentStation + "/n" ;
}
	
	
}
