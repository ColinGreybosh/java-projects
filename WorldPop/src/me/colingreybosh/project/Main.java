package me.colingreybosh.project;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main
{
	public static void main(String[] args)
	{
		// Init variables
		int yearCurrent, yearApprox, deltaT;
		double pop, rate, estimation;
		Boolean again;
		String parsedEstimation, parsedPopulation, parsedRate;

		do {
			// Get user input
			yearCurrent = tryGetInt("Enter the current year: ");
			pop = tryGetDouble("Enter the current population (in billions): ");
				// Convert received decimal to number in billions
				pop *= 1e9;
			rate = tryGetDouble("Enter the current population growth rate (in percent): ");

			do {
				// Ask for a year as long as the deltaT value will be negative
				yearApprox = tryGetInt("Enter the year to estimate population: ");
				// If the approximated year is before the current year, ask again
				if (yearApprox <= yearCurrent) System.out.println("The approximated year cannot be before the current year!");
			} while (yearApprox <= yearCurrent);

			// PeRT uses time after certain point
			deltaT = yearApprox - yearCurrent;

			// Set estimation equal to the value returned by the population calculation
			estimation = pert(pop, rate, deltaT);

			// Create a new number format, drop any decimals
			DecimalFormat format0 = new DecimalFormat("#");
				// Group numbers with commas; 74,000 vs. 74000
				format0.setGroupingUsed(true);
				format0.setGroupingSize(3);

			// Create a new number format, keep 2 decimals
			DecimalFormat format2 = new DecimalFormat("#.##");

			// parsedEstimation is equal to the new, formatted output
			parsedEstimation = format0.format(estimation);
			parsedPopulation = format0.format(pop);
			parsedRate = format2.format(rate);

			System.out.printf("\nWith a current growth rate of %s%% and a population of %s, it is estimated that the population will be %s in the year %d.\n\n", parsedRate, parsedPopulation, parsedEstimation, yearApprox);

			again = loop("Approximate another population?");
		} while (again);


	}

	private static double pert(double population, double rate, int years)
	// Current population, current pop growth rate, years from 2017
	{
		// PopulationF = Population0 * e^(r * t)

		// return (population * Math.pow(Math.E,((rate/100) * years)));
		return (population * Math.exp((rate / 100) * years));
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
			System.out.println("\nInvalid response! Please try again.\n");
			// Recursively call tryGetDouble() again
			return tryGetDouble(question);
		}
	}

	// New tryGetInt method
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
			// This error will print if the user does not input an int
			System.out.println("Invalid response! Please try again.\n");
			// Recursively call tryGetInt() again
			return tryGetInt(question);
		}
	}
}
