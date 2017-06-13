package calandar;

import java.util.ArrayList;
import java.util.Scanner;

public class TextCommands {


	//command based variables
	private static Scanner inputText = new Scanner(System.in);

	public static void main(String[] args) {

		boolean end = false;
		boolean removed = false;
		boolean wasRemoved = true;

		int strtDteFstI = 0;
		int strtDtsSndI = 0;
		String startDate = "";

		int startIndexOfEndDate = 0;
		int endIndexOfEndDate = 0;
		String endDate = "";

		int startIndexOfTime = 0;
		int endIndexOfTime = 0;
		String eventTime = "";

		int startIndexOfEventname = 0;
		String eventName = "";

		String input = "";
		ArrayList<String> event = new ArrayList<String> ();

		System.out.println("Hello and welcome to CalendBot. Type '>help' for the current command list, or >help 'command' \nfor specific command syntax. Please note that many more commands are on the way!");

		while (end == false) {

			System.out.println("input command: ");
			input = inputText.nextLine();


			//methods checking for commands
			if (input.length() < 1  || ! input.substring(0,1).equals(">")) {

				System.out.println("Sorry, either input syntax is wrong, or you entered an invalid command. Please type '>help' for more information");
			}
			else if (input.equals(">help")) {

				HelpCommands.help();
			}
			else if (input.equals(">show")) {

				RealCalendar.currentCalendar();
			}
			else if (input.equals(">events")) {
				System.out.println(RealCalendar.events.size());
				System.out.println(RealCalendar.events.get(0).get(0));
			}
			else if (input.equals(">end")) {

				System.out.print("\nGoodBye.");
				inputText.close();
				end = true; 
			} else {

				try {


					//not in separate methods because they access each others variables and i dont wanna make them all class variables :/
					
					//get start date
					//take index from first space to second space
					strtDteFstI = input.indexOf(" ");
					strtDtsSndI = input.indexOf(" ", strtDteFstI + 1);
					startDate = (input.substring(strtDteFstI + 1, strtDtsSndI));
					
					if (input.substring(0,strtDteFstI).equals(">remove") && RealCalendar.events.size() > 0) {

						eventName = startDate;
						for (int i = 0; i < RealCalendar.events.size(); i++) {

							if (removed == false) {
								for (String e : RealCalendar.events.get(i)) {

									if (eventName.equals(e)) {

										RealCalendar.events.remove(i);
										System.out.println("Removed " + eventName + " from the calendar.");
										removed = true;
										wasRemoved = true;
										break;
									}
								}
							}
							else {break;}
						}
						if (wasRemoved == false) {System.out.println("Sorry, that event could not be removed. This is probably becaues it either never existed, or you typed it in wrong.");}
						removed = false;
						input = input + " 0 0 0 0";
					}

					//get end date
					//index from second space to 3rd space
					startIndexOfEndDate = strtDtsSndI;
					endIndexOfEndDate = input.indexOf(" ", startIndexOfEndDate + 1);
					endDate = (input.substring(startIndexOfEndDate + 1, endIndexOfEndDate));

					//get event time
					//index from 3rd space to 4th space
					startIndexOfTime = strtDtsSndI;
					endIndexOfTime = input.indexOf(" ", startIndexOfTime + 1);
					eventTime = (input.substring(startIndexOfTime+ 1, endIndexOfTime));

					//get event name
					//index from 4th space to the end
					startIndexOfEventname = endIndexOfTime;
					eventName = input.substring(startIndexOfEventname, input.length());
				}
				catch (StringIndexOutOfBoundsException e) {

					System.out.println("Sorry, either your parameters are wrong, or you've entered an invalid command.\n Type '>help' for information oon parameters or see error: \n" + e);
				}

				try {
					if (input.substring(0,strtDteFstI).equals(">add")) {

						endDate = null;
						event.add(eventName);
						event.add(startDate );
						//no end date for adding a single event
						event.add("0");
						//uses endDate instead of eventTime because eventTime is in endDates spot for >add
						event.add(endDate);
						RealCalendar.events.add(event);
						System.out.println("Added " + eventName + " to the calendar on " + startDate + " at " + eventTime + ".");
						//event.clear();
					}
					else if (input.substring(0,strtDteFstI).equals(">addDaily")) {

						//addDaily(startDate, endDate, eventTime, event);
					}
					else if (input.substring(0,strtDteFstI).equals(">addWeekly")) {

						//addWeekly(startDate, endDate, eventTime, event);
					}
					else {System.out.println("Sorry, that is an invalid command. check >help for a list of usable commands.");}

				} catch (StringIndexOutOfBoundsException e) {}


			}
		}
	}
}
