package daysbetween;

import java.util.Arrays;
import java.util.stream.Stream;

public class DaysBetweenCalculator {
	
	private static final int DAY = 0;
	private static final int MONTH = 1;
	private static final int YEAR = 2;
	
	private static int[] monthDays = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	
	public static boolean isLeapYear(int year) {
		if (year % 4 == 0 && year % 100 != 0) {
			return true;
		}
		return year % 400 == 0;
	}
	
	private static int[] dateToArray(String strDate) {
		return Stream.of(strDate.split("/")).mapToInt(Integer::parseInt).toArray();
	}
	
	public static int calculateDaysBetweenDates(String start, String end) {
		int[] startArray = dateToArray(start);
		int[] endArray = dateToArray(end);
		
		if (startArray[YEAR] > endArray[YEAR] || 
				(startArray[YEAR] == endArray[YEAR] && startArray[MONTH] > endArray[MONTH]) || 
				(startArray[YEAR] == endArray[YEAR] && startArray[MONTH] == endArray[MONTH] && startArray[DAY] > endArray[DAY])) {// is start after end, in which case switch
			int[] temp = endArray;
			endArray = startArray;
			startArray = temp;
		}
		
		int dayCount = -1;
		while (!(startArray[DAY] >= endArray[DAY] && 
				startArray[MONTH] >= endArray[MONTH] && 
				startArray[YEAR] >= endArray[YEAR])) {

			monthDays[MONTH] = isLeapYear(startArray[YEAR]) ? 29 : 28;// days in february

			if (startArray[DAY] < monthDays[startArray[MONTH] - 1]) {// less than total days in month
				startArray[DAY]++;
			} else if (startArray[MONTH] < 12) {// less than total months in year
				startArray[DAY] = 1;
				startArray[MONTH]++;
			} else {// roll into the next year
				startArray[DAY] = 1;
				startArray[MONTH] = 1;
				startArray[YEAR]++;
			}
			dayCount++;
		}

		return Math.max(dayCount, 0);
	}
}
