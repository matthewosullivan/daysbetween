package daysbetween;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {

	static int[] monthDays = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	public static void main(String[] args) {
		//String start = "01/01/1901";
		//String end = "31/12/2999";

		String start = "31/12/2999";
		String end = "30/12/2999";

		int[] startArray = Stream.of(start.split("/")).mapToInt(Integer::parseInt).toArray();
		int[] endArray = Stream.of(end.split("/")).mapToInt(Integer::parseInt).toArray();

		System.out.println(Arrays.toString(startArray));
		System.out.println(Arrays.toString(endArray));

		int dayCount = -1;
		while (!(startArray[0] >= endArray[0] && 
				startArray[1] >= endArray[1] && 
				startArray[2] >= endArray[2])) {

			monthDays[1] = isLeapYear(startArray[2]) ? 29 : 28;// days in february

			if (startArray[0] < monthDays[startArray[1] - 1]) {// less than total days in month
				startArray[0]++;
			} else if (startArray[1] < 12) {// less than total months in year
				startArray[0] = 1;
				startArray[1]++;
			} else {// roll into the next year
				startArray[0] = 1;
				startArray[1] = 1;
				startArray[2]++;
			}
			dayCount++;
		}

		System.out.println(Math.max(dayCount, 0));
		System.out.println(Arrays.toString(startArray));
		System.out.println(Arrays.toString(endArray));
		// calculate

		// incrementor

		// try to increment day, keep incrementing given month, year keep count of days

		// leap year calcuator

		// System.out.println(isLeapYear(1900));
		// System.out.println(isLeapYear(2300));
		// System.out.println(isLeapYear(2400));

		// 28 or 29
		/*
		 * test is leap year for (int i = 1901; i < 3000; i++) { if (isLeapYear(i)) {
		 * assert LocalDate.parse(i + "-02-29").toString().contains("29"); } else {
		 * assert LocalDate.parse(i + "-02-28").toString().contains("28"); }
		 * System.out.println(i + " " + isLeapYear(i) + " " + LocalDate.parse("29/02/" +
		 * i, DateTimeFormatter.ofPattern("d/MM/yyyy"))); }
		 */
	}

	public static boolean isLeapYear(int year) {
		if (year % 4 == 0 && year % 100 != 0)
			return true;
		return year % 400 == 0;
	}

}
