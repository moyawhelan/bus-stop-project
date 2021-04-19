import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.List;

public class TSTReadFiles {


	
	public static String moveKeywords (String stopName) {

		char [] letter = stopName.toCharArray();
		//this is the first char what is going to be added onto the end
		char firstLetter = letter[0];
		char secondLetter = letter[1];

		if (stopName!=null) {

			if (firstLetter=='W' && secondLetter =='B') {
				stopName = stopName.substring(2,stopName.length()) + " " + stopName.substring(0,2);
			}
			else if (firstLetter == 'N' && secondLetter == 'B') {
				stopName = stopName.substring(2, stopName.length()) + " " + stopName.substring(0,2);

			}
			else if (firstLetter == 'S' && secondLetter == 'B') {
				stopName = stopName.substring(2, stopName.length()) + " " + stopName.substring(0,2);

			}
			else if (firstLetter == 'E' && secondLetter == 'B') {
				stopName = stopName.substring(2, stopName.length()) +  " " + stopName.substring(0,2);
			}
		}
		return stopName;

	}
	
	public static void main (String[] args) {
		// create ArrayList to store the "Stops" objects
		List <Stop> stop = new ArrayList<>();
		try {

			BufferedReader br = new BufferedReader(new FileReader("src//stop.txt"));

			String fileRead = br.readLine();

			while (fileRead!=null) {

				String[] tokenize = fileRead.split(",");

				int tempStopId = Integer.parseInt(tokenize[0]);
				long tempStopCode = Long.parseLong(tokenize[2]);
				String tempStopName = tokenize[2];
				String tempStopNameAbbrev = tokenize[3];
				double  tempStopLat = Double.parseDouble(tokenize[4]);
				double  tempStopLong = Double.parseDouble(tokenize[5]);
				String tempZoneId = tokenize[6];
				int tempLocationType = Integer.parseInt(tokenize[7]);
				int tempParentStation = Integer.parseInt(tokenize[8]);

				Stop tempObj = new Stop (tempStopId, tempStopCode, tempStopName, tempStopNameAbbrev, tempStopLat, tempStopLong, tempZoneId, tempLocationType, tempParentStation);

				stop.add(tempObj);

				fileRead = br.readLine();

			}

			br.close();

		}

		catch(FileNotFoundException fnfe) {
			System.out.println("File not found");
		}

		catch (IOException ioe) {
			ioe.printStackTrace();
		}



	}





	
}

