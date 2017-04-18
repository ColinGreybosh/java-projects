package me.colingreybosh.review;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		// Initialize Variables
		// New array of length 5
		double[] numbers = new double[5];
		double answer;
		Boolean again;

		System.out.println("Good Morning!\n" + args[0] + " " + args[1]);

		do {  // Execute this code as long as the return from loop() is true, the code executes at least once

			// Inputs are equal to the returned value from the tryGetLong method
			numbers[0] = tryGetLong("Enter a number: ");
			numbers[1] = tryGetLong("Enter a second number: ");
			numbers[2] = tryGetLong("Enter a third number: ");
			numbers[3] = tryGetLong("Enter a fourth number: ");
			numbers[4] = tryGetLong("Enter a fifth number: ");

			// The answer variable is equal to the return of the calculate method
			// Answer is equal to ((num1+num2)/(num3-num4))*num5
			answer = calculate(numbers);

			// Print the equation and the result
			System.out.printf("\n\t(%d + %d)/(%d - %d) * %d = %f\n\t%f * 10 = %f\n\n", Math.round(numbers[0]), Math.round(numbers[1]), Math.round(numbers[2]), Math.round(numbers[3]), Math.round(numbers[4]), answer, answer, (10 * answer));

			// Call loop() with an argument of a yes/no question, set the again boolean to the true/false value returned
			again = loop("Calculate more numbers?");

		} while (again); // Continue executing code if the user inputs yes
	}

	public static boolean loop(String question)
	{
		// Initialize variable and scanner
		String response;
		Scanner input = new Scanner(System.in);

		// Ask for user input
		System.out.print(question + " y/n ");
		// Get user input
		response = input.next();

		if (response.equals("y") || response.equals("Y")) {
			// If user answers yes, return true
			return true;
		}

		if (response.equals("n") || response.equals("N")) {
			// If user answers no, return false and print
			System.out.println("Goodbye!");
			return false;
		}

		if (!(response.equals("y") || response.equals("n") || response.equals("Y") || response.equals("N"))) {
			// If user does not input any valid response, print this line and recursively call loop(question) again
			System.out.println("Invalid response! Please try again.\n");
			loop(question);
		}

		return false;
	}

	// New tryGetLong method
	public static long tryGetLong(String prompt)
	{
		// Initialize scanner
		Scanner input = new Scanner(System.in);
		try { // Try to run this code
			// Print the prompt provided when the method is called
			System.out.print(prompt);
			// Return the user input
			return input.nextLong();
		} catch(InputMismatchException e) { // Look for an InputMismatchException error
			// This error will print if the user does not input an integer
			System.out.println("Invalid response! Please try again.\n");
			// Recursively call tryGetLong() again
			return tryGetLong(prompt);
		}
	}

	public static double calculate(double[] num)
	{
		// Return 'Not a Number' if dividing by zero
		if ((num[2] - num[3]) == 0) {
			return Double.NaN;
		}
		// Return the answer to this equation
		return (((num[0] + num[1])/(num[2] - num[3]) * num[4]));
	}
}