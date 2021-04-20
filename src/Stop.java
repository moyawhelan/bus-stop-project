
public class Stop {
	
	private String stopId;
	private String stopCode;
	public String stopName;
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
	
	
	


	public String stopToString (Stop stop) {
		return "Stop ID: " + stopId + '\n' + "Stop Code: " + stopCode + '\n' + "Stop Name: " + stopName + '\n' + "Stop Name Abbreviated: " + stopNameAbbrev + '\n' + "Stop Latitude: " + stopLat + '\n' +  "Stop Longitude: " + stopLong + '\n' + "Zone ID: " + zoneId;
	}



}
