package me.colingreybosh.project;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		Boolean again;
		do {
			// Initialize Variables
			double pounds, height, bmi;
			String range;

			// Pounds = user input if it is a double
			pounds = tryGetDouble("Please input a weight in pounds: ");
			// Height = user input if it is a double
			height = tryGetDouble("Please input a height in inches: ");

			// Calculate bmi with values from pounds and height
			bmi = bmi(pounds, height);
			// Print BMI
			System.out.println("\nBMI = " + bmi);

			// Range = bmiRange
			range = bmiRange(bmi);
			System.out.println("Your BMI status: " + range);

			// Ask to calculate again
			again = loop("\nCalculate another BMI?");
		} while (again);
	}

	// New method to return bmi
	private static double bmi(double weight, double height)
	{
		return ((Math.round((weight / (Math.pow(height,2))) * 7030.0)) / 10.0);
		// Weight ((Pounds) / Height^2) * 703
		// Inner equation multiplied by 10, rounded number divided by 10
		// This returns one decimal place
	}

	private static String bmiRange(double bmi)
	{
		// BMI < 18.5
		if (bmi < 18.5) return "Underweight";
		// 18.5 <= BMI <= 24.9
		if ((18.5 <= bmi) && (bmi <= 24.9 )) return "Normal/Healthy";
		// 25.0 <= BMI <= 29.9
		if ((25.0 <= bmi) && (bmi <= 29.9)) return "Overweight";
		// BMI >= 30.0
		if (bmi >= 30.0) return "Obese";

		return null;
	}

	private static boolean loop(String question)
	{
		// Initialize variable and scanner
		String response;
		Scanner input = new Scanner(System.in);

		// Ask for user input
		System.out.print(question + " y/n ");
		// Get user input
		response = input.next();

		if (response.toLowerCase().equals("y")) {
			// If user answers yes, return true
			System.out.println();
			return true;
		}

		if (response.toLowerCase().equals("n")) {
			// If user answers no, return false
			return false;
		}

		if (!(response.toLowerCase().equals("y") || response.toLowerCase().equals("n"))) {
			// If user does not input any valid response, print this line and recursively call loop(question) again
			System.out.println("Invalid response! Please try again.\n");
			loop(question);
		}

		return false;
	}

	// New tryGetDouble method
	private static double tryGetDouble(String prompt)
	{
		// Initialize scanner
		Scanner input = new Scanner(System.in);
		try { // Try to run this code
			// Print the prompt provided when the method is called
			System.out.print(prompt);
			// Return the user input
			return input.nextDouble();
		} catch(InputMismatchException e) { // Look for an InputMismatchException error
			// This error will print if the user does not input an integer
			System.out.println("Invalid response! Please try again.\n");
			// Recursively call tryGetLong() again
			return tryGetDouble(prompt);
		}
	}
}
