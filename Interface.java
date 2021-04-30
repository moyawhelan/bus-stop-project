import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.Scanner;

public class Interface {

	public static final int optionOne = 1;
	public static final int optionTwo = 2;
	public static final int optionThree = 3;


	public static void main(String[] args) throws IOException 
	{


		Scanner firstUserInput = new Scanner (System.in);
		boolean finished = false;

		System.out.println("Welcome to the Vancouver Bus System Database!");



		while (!finished) {

			System.out.println("\nDo you wish to exit the Bus Management System?\nEnter \"yes\" to exit the program or enter \"no\" to continue." );

			String quitInput = firstUserInput.next();

			if(quitInput.equalsIgnoreCase("Yes")) {
				finished = true;
			}

			else if (quitInput.equalsIgnoreCase("No")) {

				boolean errorChoice = true;

				while(errorChoice) {


					System.out.println("If you wish to find the shortest path between two bus stops, please enter '1'");
					System.out.println("If you wish to search for a stop by name, please enter '2'");
					System.out.println("If you wish to search for trips with a given arrival time, please enter '3'");





					if (firstUserInput.hasNextInt()) {



						int userChoiceInput = firstUserInput.nextInt();

						if(userChoiceInput==optionOne)
						{
							System.out.println ("Find the shortest path between two bus stops!");
							System.out.println("Please wait while the system loads...");

							boolean finishedStopOne = false;



							ShortestPath network = new ShortestPath("stop_times.txt", "transfers.txt");
							String fileInput = "stops.txt";
							String filename = "stop_times.txt";
							String transfers2 = "transfers.txt";


							network.CostsArray(filename, fileInput, transfers2);
							
							boolean [] stopExistArray =  network.DoesStopExist;



							while(finishedStopOne==false) 
							{

								System.out.println("\nPlease enter the Stop ID from which you would like to start your journey from.\nOr if you would like to quit type 'Quit' ");

								Scanner originStopInput = new Scanner(System.in);

								String originStopUserInput = originStopInput.next();

								if (originStopUserInput.equalsIgnoreCase("Quit")) {
									finishedStopOne = true;
									finished = true;
									firstUserInput.close();
									originStopInput.close();
									System.out.println("Goodbye. Application is now closing...");
									return;
								}



								try
								{

									
									int start = Integer.parseInt(originStopUserInput);
									boolean doesStartExist = false;
									if(start < 12479) 
									{
										doesStartExist = stopExistArray[start];
									}
									

									if(doesStartExist == true) 
									{

										boolean finishedStopTwo = false;

										while(finishedStopTwo == false) 
										{

											System.out.println("Please enter the Stop ID from which you would like to end your journey at?\nOr if you would like to quit type 'Quit' ");
											Scanner destinationInput = new Scanner(System.in);
											String destinationStopUserInput = destinationInput.next();


											if(destinationStopUserInput.equalsIgnoreCase("Quit"))
											{
												finishedStopOne = true;
												finished = true;
												originStopInput.close();
												destinationInput.close();
												firstUserInput.close();
												System.out.println("Application closing ");
												return;
											}



											try
											{

												int end = Integer.parseInt(destinationStopUserInput);
												
												boolean doesEndExist = false;
												
												if(end < 12479) 
												{
													doesEndExist = stopExistArray[end];
												}
												 

												if(doesEndExist == true) 
												{
												//	destinationInput.close();
												//	double [][] tester = network.Costs;

												//	double testVal = tester[378][379];

												//	System.out.println(testVal);

												/*
													String result = network.Dijkstra(start, end);

													double resultD = Double.parseDouble(result);

													if(resultD == Double.MAX_VALUE)
													{
														System.out.println("There exists no path between your selected stops");//returns a valid path between the two stops
													}
													else {
												 */

												System.out.println("Shortest distance from Stop(" + start + ") to Stop(" + end + "): " + network.Dijkstra(start, end));//returns a valid path between the two stops
												finishedStopTwo= true;
												//}
												}

												else {
												System.err.println("That stop does not exist! Please try again!");


												}
											}
											catch(NumberFormatException b)
											{
												System.err.println("Please enter a valid input with a valid Stop ID in number format. Type 'Quit' to exit the program!");
											}
											/*
											else
											{
												System.out.println("Please enter a valid input, or type 'Quit' to exit the program");
											}
											*/

										}
									}


									else 
									{
										System.err.println("That stop does not exist!");
									}
								}

								catch(NumberFormatException e)
								{
									System.err.println("Please enter a valid input with a valid Stop ID in number format. Type 'Quit' to exit the program!");
								}
								//else {
								//	System.out.println("Please enter a valid input, or type 'Quit' to exit the program");
								//}


							}
							errorChoice = false;
						}

						else if(userChoiceInput==optionTwo) // Loop that enters the code for Searching for stops
						{
							TST<Integer> trie = new TST<>();


							LinkedList <Stop> stopsList = new LinkedList<Stop>();



							try {
								FileReader fr = new FileReader ("stops.txt");
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

									String newStopName = TSTReadFiles.moveKeywords(stopName);

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
								System.err.println("File Not Found");
							}



							try {
								boolean finishedSearchByName = false;

								System.out.println("Please type the name or partial name of the stop you wish to search for. \n Or if you would like to quit type 'Quit'");

								while (!finishedSearchByName) {



									Scanner inputSearchName = new Scanner(System.in);
									String userInputSearchName = inputSearchName.nextLine();


									if(userInputSearchName.equalsIgnoreCase("Quit"))
									{
										System.out.println("Goodbye.");
										finishedSearchByName = true;
										inputSearchName.close();
										finished = true;
										firstUserInput.close();
									}
									else if(userInputSearchName!= null)
									{

										TSTReadFiles.getStopFromUserInput(userInputSearchName, trie, stopsList);
										System.out.println ("\n");


									}


									else
									{
										System.err.println("This is not a valid input. Please enter a valid stop name or type 'Quit' to exit the program!");
									}
								}
							}

							catch( NullPointerException exception )
							{
								System.err.println("There is no name entered!");
							}
							catch(java.util.NoSuchElementException exception)
							{
								System.err.println("This is not a valid input. Please enter a valid stop name or type 'Quit' to exit the program!");
							}
							errorChoice = false;
						}



						else if(userChoiceInput==optionThree) // Code that enters loop for Arrival time searching
						{
							System.out.println("Please wait while the system loads...");

							ReadStopTimes object = new ReadStopTimes();

							try {
								boolean finishedArrivalTimeSearch = false;
								System.out.println("\nPlease enter the arrival time or 'Quit' to exit: ");
								while (!finishedArrivalTimeSearch) {



									Scanner arrivalTimeInput = new Scanner(System.in);
									String arrivalTimeUserInput = arrivalTimeInput.next();

									if(arrivalTimeUserInput.equalsIgnoreCase("Quit"))
									{
										System.out.println("Goodbye.");
										finishedArrivalTimeSearch = true;
										arrivalTimeInput.close();
										finished = true;
										firstUserInput.close();
									}

									else if(arrivalTimeUserInput!=null)
									{

										if(TripSearch.isValid(arrivalTimeUserInput)) 
										{
											LocalTime time = LocalTime.parse(arrivalTimeUserInput, TripSearch.parseFormat);
											TripSearch.getUserInput(object.getArrivalsList(), object.getTripsList(), time);
										}
										else 
										{
											System.err.println("This is not a valid input, please enter the time in HH:MM:SS format (e.g. 12:04:45) or type 'Quit' to exit the program!");
										}
									}

								}
							}

							catch( NullPointerException exception )

							{
								System.err.println("There is no name entered!");
							}
							catch(java.util.NoSuchElementException exception)
							{
								System.err.println("This is not a valid input, please enter the time in HH:MM:SS format (e.g. 12:04:45) or type 'Quit' to exit the program!.");
							}
							errorChoice=false;
						}
						else {
							System.err.println("This is not a valid input, please enter the number '1' or '2' or '3'!");
							
						}
					}

					else {
						System.err.println ("This is not a valid choice. Please type in a valid input!");
						firstUserInput.next();
					}

				}

			}
			else {
				System.err.println("This is not a valid input. Please enter 'Yes' or 'No'!");
			
			}
		}
		System.out.println("You have exited the Vancouver Bus Management System. Application is now closing...");
		firstUserInput.close();

	}

}
