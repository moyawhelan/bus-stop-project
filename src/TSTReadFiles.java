import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;


import java.util.List;

public class TSTReadFiles {



	public static String moveKeywords (String stopName) {

		char [] letter = stopName.toCharArray();

		char firstLetter = letter[0];
		char secondLetter = letter[1];
		char thirdLetter = letter[2];
		char fourthLetter = letter[3];
		char fifthLetter = letter[4];
		char sixthLetter = letter[5];
		char seventhLetter = letter[6];
		char eighthLetter = letter[7];

		if (stopName!=null) {

			if (firstLetter=='W' && secondLetter =='B') {
				stopName = stopName.substring(3,stopName.length()) + " " + stopName.substring(0,2);
			}
			else if (firstLetter == 'N' && secondLetter == 'B') {
				stopName = stopName.substring(3, stopName.length()) + " " + stopName.substring(0,2);

			}
			else if (firstLetter == 'S' && secondLetter == 'B') {
				stopName = stopName.substring(3, stopName.length()) + " " + stopName.substring(0,2);

			}
			else if (firstLetter == 'E' && secondLetter == 'B') {
				stopName = stopName.substring(3, stopName.length()) +  " " + stopName.substring(0,2);
			}
			else if (firstLetter == 'F' && secondLetter == 'L' && thirdLetter == 'A' && fourthLetter == 'G' && fifthLetter == 'S' && sixthLetter == 'T' && seventhLetter == 'O' && eighthLetter == 'P') {
				stopName = stopName.substring(9, stopName.length()) +  " " + stopName.substring(0,8);
			}
		}
		return stopName;


	}

	public static Stop searchForStop (LinkedList<Stop> stops, String stopName) {
		Stop stop = null;
		for (Stop myStop:stops) {
			if (myStop.stopName==stopName) {
				stop=myStop;
				break;
			}
		}
		return stop;
	}








	public static void main (String[] args) throws IOException {
		// create ArrayList to store the "Stops" objects
		TST<Integer> trie = new TST<>();


		LinkedList <Stop> stopsList = new LinkedList<Stop>();



		try {
			FileReader fr = new FileReader ("src//stops.txt");
			BufferedReader br = new BufferedReader(fr);

			String stopInfo = br.readLine(); 


			while ((stopInfo !=null)) {

				String tokenize[] = stopInfo.split(",");


				String stopId = tokenize[0];
				String stopCode = tokenize[1];
				String stopName = tokenize[2];
				String stopNameAbbrev = tokenize[3];
				String stopLatitude = tokenize[4];
				String stopLongitude = tokenize[5];
				String stopZoneId = tokenize[6];

				String newStopName = moveKeywords(stopName);

				Stop newStop =  new Stop (stopId, stopCode, newStopName ,stopNameAbbrev, stopLatitude, stopLongitude, stopZoneId);
				stopsList.add(newStop);

				if (!trie.contains(newStopName))
					trie.put(newStopName , 1);
				else 
					trie.put(newStopName, (trie.get(newStopName) + 1)); 



				stopInfo = br.readLine();


			}

		}
		catch (FileNotFoundException fnfe) {
			System.out.println("File Not Found");
		}
		String string = "FLAGSTOP has Covid";
		String newString = moveKeywords(string);
		System.out.println(newString);


		System.out.println(trie.size());





		String myStopName = "KING GEORGE BLVD FS 16 AVE SB";

		if(trie.get(myStopName)!= null) {
			Stop stopInformation = searchForStop(stopsList,myStopName);
			stopInformation.stopToString(stopInformation);
		}





		LinkedList<String> linkedList = trie.keysWithPrefix("SPRING");
		int numberWithGivenPrefix = 0; 
		for (String temp : linkedList) 
		{
			numberWithGivenPrefix = numberWithGivenPrefix + trie.get(temp);
		}

		System.out.println(numberWithGivenPrefix);



	}
}
















