import java.util.ArrayList;

public class Trip 
{
	ArrayList<Transfer> transfers;
	private int iD;
	
	public Trip(int iD)
	{
		transfers = new ArrayList<Transfer>();
		this.iD = iD;
	}
	
	public void add(Transfer transfer) 
	{
		transfers.add(transfer);
	}
	
	public int getID() 
	{
		return iD;
	}
	public void printTrip() 
	{
		System.out.print("\n Trip ID: " + getID() + "\n");
		for(Transfer transfer:transfers) 
		{
			System.out.print(transfer.toString());
		}
		System.out.println("");
	}

}
