package daysbetween;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			String line = sc.nextLine();
			
//			if (line.trim().length() == 0) continue;
			
			System.out.println("Output(Days): " + DaysBetweenCalculator.calculateDaysBetweenDates(line.split(" ")[0], line.split(" ")[1]));
		}
	}

}
