package java_I.examples.integerexample1;

import java.util.Scanner; // Import the scanner library for use in the main method

public class IntegerExample // Create a class called IntegerExample
{
    public static void main( String[] args ) // Create the main method which can receive an array of string arguments
    {
        Scanner input = new Scanner( System.in ); // Create a new scanner called input which looks for input in System.in
        int x; // Declare an integer variable named x
        int y; // Declare an integer variable named y
        int difference; // Declare an integer variable named difference
        System.out.print( "Enter first integer: " ); // Prompt the user to input an integer
        x = input.nextInt();
        System.out.print( "Enter second integer: " ); // Prompt the user to input another integer
        y = input.nextInt();
        difference = x - y; // Set the integer difference equal to x minus y
        System.out.printf( "Difference is %d\n", difference );
    }
}