
public class Stop {
	
	private String stopId;
	private String stopCode;
	private String stopName;
	private String stopNameAbbrev;
	private String stopLat;
	private String stopLong;
	private String zoneId;
	
	
	

	public Stop (String stopId, String stopCode, String stopName, String stopNameAbbrev, String stopLat, String stopLong, String zoneId)
	{
		this.stopId = stopId;
		this.stopCode = stopCode;
		this.stopName = stopName;
		this.stopNameAbbrev = stopNameAbbrev;
		this.stopLat = stopLat;
		this.stopLong = stopLong;
		this.zoneId = zoneId;


	}
	
	
	public String getStopId () {
		return stopId;
	}
	
	public String getStopCode () {
		return stopCode;
	}
	

	
	public String getStopName () {
		return stopName;
	}
	
	public String getStopNameAbbrev () {
		return stopNameAbbrev;
	}
	public String getStopLat () {
		return stopLat;
	}
	public String getStopLong() {
		return stopLong;
	}
	public String getZoneId () {
		return zoneId;
	}
	
	
	
	
	


	public String toString () {
		return "Stop ID: " + stopId + '\n' + "Stop Code: " + stopCode + '\n' + "Stop Name: " + stopName + '\n' + "Stop Name Abbreviated: " + stopNameAbbrev + '\n' + "Stop Latitude: " + stopLat + '\n' +  "Stop Longitude: " + stopLong + '\n' + "Zone ID: " + zoneId;
	}



}
