import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;


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

		if (stops!=null && stopName!=null) {
			for (Stop stop:stops) {

				if (stop.getStopName().equals(stopName)) {

					return stop;
				}
			}
		}


		return null;
	}

	public static void getStopFromUserInput (String userInputStopName, TST<Integer> trie, LinkedList <Stop> stopsList ) {

		boolean finishedSearch = true;

		while(finishedSearch) {

			if (userInputStopName!=null && trie!=null && stopsList!=null) {

				userInputStopName = userInputStopName.toUpperCase();



				if(trie.get(userInputStopName)!= null) {
					Stop stopInformation = searchForStop(stopsList,userInputStopName);
					System.out.println(stopInformation.toString());
					System.out.println("\nIf you wish to continue searching for a bus stop by name, please type the name or partial name of the stop you wish to search for. \n Or if you would like to quit type 'Quit'");
					

					finishedSearch=false;
				}

				else if (trie.keysWithPrefix(userInputStopName)!=null) { 

					for (String temp: trie.keysWithPrefix(userInputStopName))

						System.out.println(temp);

					if (trie.keysWithPrefix(userInputStopName).size()==0) {
						System.err.println("This is not a stop name on this bus route, please try again!");
					}
				
					System.out.println("\nIf the stop you entered is on this route, the full names of the stops are listed above.\nPlease enter the full name of the stop to get the stop information for your specified stop. \nOtherwise, enter 'Quit' to exit the program");
					finishedSearch=false;
				}
			}

			else {
				System.err.println("There is an error in the input");
			}
			
			finishedSearch=false;
			
		}
		

	}
}









