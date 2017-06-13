package calandar;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RealCalendar extends TextCommands{

	//calendar related variables
			private static int monthsDisplayed = 2;
			private static String date = "" + LocalDate.now();
			private static Calendar calendar = Calendar.getInstance();
			private static int year = year(date);
			private static boolean isLeapYear = ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0));
			// https://stackoverflow.com/questions/16956720/how-to-create-an-2d-arraylist-in-java
			public static List<List<String>> events = new ArrayList<List<String>>();
			
			public static void currentCalendar() {
				
				calendar((day(date) - 1), month(date));
				monthsDisplayed = 2;
			}
			
			//method creates an array of the next 56 days in the year from the day passed in
			public static void calendar(int day, int month) {
				
				//top bar
				topbar(calendar.get(Calendar.DAY_OF_WEEK), month);
						
				
				String[][] cal = new String [5] [7];
				boolean monthNum = false;
				for (String[] i : cal) {
					
					System.out.print("|\t");
					for (String e : i) {
						
						if (day < 1) {day = daysInAMonth(month); monthsDisplayed++;}
						
						if (day > daysInAMonth(month - 1) && monthNum == false) {day = 1; monthNum = true;}
						else if (day > daysInAMonth(month)) {day = 1; month++;}
						
						if (day >= 10) {
							e = "   | " + day + " |    ";
						}
						else {
						e = "   |  " + day + " |    ";
						}
						day++;
						System.out.print(e);
					}
					System.out.println("\t|");
				}
				monthNum = false;
				
				//bottom bar
				bar();
			}
			
			//method that returns the day of the month as an integer
			public static int day(String date) {
				
				String day = "";
				if (date.substring(date.length() - 2).equals("-")) {
					day  = date.substring(date.length());
				}
				else {
					day  = date.substring(date.length() - 2, date.length());
				}
				return Integer.parseInt(day);
				
			}
			
			public static int month(String date) {
				
			String month = "";
			if (date.substring(date.length() - 5).equals("-")) {
				month  = date.substring(date.length() - 4);
			}
			else {
				month  = date.substring(date.length() - 5, date.length() - 3);
			}
			return Integer.parseInt(month);
				
			}
			
			public static int year (String date) {
				
				String year = "";
				year = date.substring(0, 4);
				return Integer.parseInt(year);
			}
			
			public static void topbar(int dayOfWeek, int month) {
				
				bar();
				System.out.println("|\t\t\t\t\t\t  " + year + "\t\t\t\t\t\t\t|");
				bar();
				
				//displays current & upcoming month(s)
				if (monthsDisplayed == 2) {
					System.out.println("|\t\t\t\t\t\t" + monthOfYear(month) +" / "+ monthOfYear(month + 1) + "\t\t\t\t\t\t|");
				}
				else {
					
					System.out.println("|\t\t\t\t\t" + monthOfYear(month - 1) + " / " + monthOfYear(month) +" / "+ monthOfYear(month + 1) + "\t\t\t\t\t\t|");
				}
				
				bar();
				
				//line of  7 days, starting with previous day
				System.out.print("|\t");
				int[] days = new int[7];
				int a = 0;
				for (int i : days) {
					if ((dayOfWeek + a) > 7) {dayOfWeek = 1; a = 0;}
					i = dayOfWeek + a;
					a++;
					System.out.print("   ~ " + dayOfWeek(i) + " ~   ");
				}
				System.out.println("\t|");
				bar();
			}
			
			public static String dayOfWeek(int day) {
				
				String[] days = { "Sat", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri"};
				return days[day-1];
			}
			
			public static String monthOfYear(int month) {
				String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
				return months[month - 1];
				
			}
			
			public static int daysInAMonth(int month) {
				
				int numdays = 0;
				int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
				if (isLeapYear) { days[1] = 29;}
				numdays = days[month];
				return numdays;
			}

			public static void bar() {
				
				System.out.print("+");
				int a = 0;
				while (a < (13*8)-1) {System.out.print("="); a++;}
				System.out.println("+");
			}
}
