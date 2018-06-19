package daysbetween;

import java.util.Arrays;
import java.util.stream.Stream;

public class DaysBetweenCalculator {
	
	private static int[] monthDays = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	
	public static boolean isLeapYear(int year) {
		if (year % 4 == 0 && year % 100 != 0)
			return true;
		return year % 400 == 0;
	}
	
	private static int[] dateToArray(String strDate) {
		return Stream.of(strDate.split("/")).mapToInt(Integer::parseInt).toArray();
	}
	
	public static int calculateDaysBetweenDates(String start, String end) {
		// convert dates to arrays
		int[] startArray = dateToArray(start);
		int[] endArray = dateToArray(end);

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

		return Math.max(dayCount, 0);
	}
}
