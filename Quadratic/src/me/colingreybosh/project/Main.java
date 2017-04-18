package me.colingreybosh.project;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		// Initialize variables
		Boolean again;
		// Stores A, B, and C
		double[] coef = new double[3];
		// Stores the two x intercepts
		double[] root;
		// Amount of decimal digits in the answer
		int precision;

		// Prints ascii art to console
		System.out.println(
			"   ____                        __                   __     _                  ______            __                   __           __                \n" +
			"  / __ \\  __  __  ____ _  ____/ /   _____  ____ _  / /_   (_)  _____         / ____/  ____ _   / /  _____  __  __   / /  ____ _  / /_  ____    _____\n" +
			" / / / / / / / / / __ `/ / __  /   / ___/ / __ `/ / __/  / /  / ___/        / /      / __ `/  / /  / ___/ / / / /  / /  / __ `/ / __/ / __ \\  / ___/\n" +
			"/ /_/ / / /_/ / / /_/ / / /_/ /   / /    / /_/ / / /_   / /  / /__         / /___   / /_/ /  / /  / /__  / /_/ /  / /  / /_/ / / /_  / /_/ / / /    \n" +
			"\\___\\_\\ \\__,_/  \\__,_/  \\__,_/   /_/     \\__,_/  \\__/  /_/   \\___/         \\____/   \\__,_/  /_/   \\___/  \\__,_/  /_/   \\__,_/  \\__/  \\____/ /_/"
		);

		System.out.println("\n____________________________________________________________________________________________________________________________________________________\n");

		do {

			System.out.println("Ax^2 + Bx + C = 0");
			do {
				// Ask for the A value until it is not 0
				coef[0] = tryGetDouble("A = ");
				if (coef[0] == 0) System.out.println("A cannot be zero!");
			} while (coef[0] == 0);

			coef[1] = tryGetDouble("B = ");
			coef[2] = tryGetDouble("C = ");
			precision = tryGetInt("How many decimal places do you want? ");

			System.out.println("\n" + coef[0] + "x^2 + " + coef[1] + "x + " + coef[2] + " = 0");

			// Root array equals the array returned by quad()
			root = quad(coef, precision);

			// If the roots are imaginary, print this line
			if ((Double.isNaN(root[0]) && (Double.isNaN(root[1])))) {
				System.out.println("x is imaginary!\n");
			}

			// If the roots are not imaginary, print this line
			// NaN == NaN is false, so this code will only execute if the parabola intersects the x-axis once
			if (root[0] == root[1]) {
				System.out.println("x = " + root[0] + "\n");
			}

			// If the roots do not equal each other and are numbers, print this line
			if ((root[0] != root[1]) && (!(Double.isNaN(root[0]) && (Double.isNaN(root[1]))))) {
				System.out.println("x = " + root[0] + ", " + root[1] + "\n");
			}

			again = loop("Calculate another quadratic?");
		} while (again);
	}

	private static double[] quad(double[] coef, int precision)
	{
		// Initialize variables for equation readability
		double[] x = new double[2];
		double a = coef[0];
		double b = coef[1];
		double c = coef[2];

		// sqrt(b^2 - 4ac)
		double sqrt = Math.pow(b,2.0) - 4.0 * a * c;
		if (sqrt < 0) {
			// Check that the radicand is not negative. The answer is imaginary if so.
			x[0] = Double.NaN;
			x[1] = Double.NaN;
			return x;
		}
		if (sqrt >= 0) sqrt = Math.sqrt(sqrt);
		// Take the square root if the radicand is not negative.
		// 2a
		double twoA = 2.0 * a;
		// -b
		double oppB = -1.0 * b;

		// If the square root is
		if (sqrt >= 0) {
			// x = ((-b +- sqrt(b^2-4ac)) / (2a))
			for (int i = 0; i < 2; i++) {
				// Calculates the root
				// Math.pow(-1.0,i) alternates positive and negative signs
				x[i] = (oppB + Math.pow(-1.0, i) * sqrt) / twoA;
				// Shifts the decimal place to round to a certain precision
				x[i] = (Math.round(x[i] * Math.pow(10,precision))) / Math.pow(10,precision);
			}
			return x;
		}
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
	private static double tryGetDouble(String question)
	{
		// Initialize scanner
		Scanner input = new Scanner(System.in);
		try { // Try to run this code
			// Print the prompt provided when the method is called
			System.out.print(question);
			// Return the user input
			return input.nextDouble();
		} catch(InputMismatchException e) { // Look for an InputMismatchException error
			// This error will print if the user does not input a double
			System.out.println("Invalid response! Please try again.\n");
			// Recursively call tryGetDouble() again
			return tryGetDouble(question);
		}
	}

	// New tryGetDouble method
	private static int tryGetInt(String question)
	{
		// Initialize scanner
		Scanner input = new Scanner(System.in);
		try { // Try to run this code
			// Print the prompt provided when the method is called
			System.out.print(question);
			// Return the user input
			return input.nextInt();
		} catch(InputMismatchException e) { // Look for an InputMismatchException error
			// This error will print if the user does not input an integer
			System.out.println("Invalid response! Please try again.\n");
			// Recursively call tryGetInt() again
			return tryGetInt(question);
		}
	}
}
