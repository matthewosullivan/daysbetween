package daysbetween;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;



public class DaysBetweenCalculatorTest {
	
	@Test
	public void testIsLeapYear() {
		int yearFrom = 1901;
		int yearTo = 2999;
		
		for (; yearFrom <= yearTo; yearFrom++) {
			boolean leapYear = DaysBetweenCalculator.isLeapYear(yearFrom);
			
			if (leapYear) {
				LocalDate.of(yearFrom, 2, 29);// will throw exception if not a leap year
			} else {
				// verify that every year which is not a leap year, definitely is not a leap year by verifying that an exception will be thrown saying so
				try {
					LocalDate.of(yearFrom, 2, 29);
					throw new RuntimeException("This is in fact a leap year");// we have missed a leap year, fail the test
				} catch (DateTimeException e) {
					assertThat(e.getMessage(), containsString("Invalid date 'February 29' as '" + yearFrom + "' is not a leap year"));
				}
			}
		}
	}
	
	
	@Test
	public void testDaysBetween() {
		LocalDate startDate = LocalDate.of(1901, 1, 1);
		LocalDate endDate = LocalDate.of(1950, 12, 31);
		
		while (!startDate.isAfter(endDate)) {
			long expected = Math.max(ChronoUnit.DAYS.between(startDate, endDate) - 1, 0);
			
			long actual = DaysBetweenCalculator.calculateDaysBetweenDates(startDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), 
															endDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			
			assertEquals(expected, actual);
			
			// move start and end date 1 day closer to each other
			startDate = startDate.plusDays(1);
			endDate = endDate.minusDays(1);
			//System.out.println(startDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " " + endDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		}
		
	}
	
}
