package Operations;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Compare
{
    public static void main(String[] args)
    {
        long num1, num2;
        Boolean again;
        do {

            num1 = tryGetLong("Enter a number: ");
            num2 = tryGetLong("Enter a second number: ");

            System.out.println();

            if (num1 == num2) {
                System.out.printf("%d = %d\n", num1, num2);
            }
            if (num1 != num2) {
                System.out.printf("%d != %d\n", num1, num2);
            }
            if (num1 < num2) {
                System.out.printf("%d < %d\n", num1, num2);
            }
            if (num1 > num2) {
                System.out.printf("%d > %d\n", num1, num2);
            }
            if (num1 <= num2) {
                System.out.printf("%d <= %d\n", num1, num2);
            }
            if (num1 >= num2) {
                System.out.printf("%d >= %d\n", num1, num2);
            }

            System.out.println();

            again = loop("Compare more numbers?");

        } while (again);
    }

    public static boolean loop(String output)
    {
        String response;
        Scanner input = new Scanner(System.in);

        System.out.println(output + " y/n");
        response = input.next();

        if (response.equals("y") || response.equals("Y")) {
            return true;
        }

        if (response.equals("n") || response.equals("N")) {
            return false;
        }

        if (!(response.equals("y") || response.equals("n") || response.equals("Y") || response.equals("N"))) {
            System.out.println("Invalid response! Please try again.\n");
            loop(output);
        }

        return false;
    }

    public static long tryGetLong(String prompt)
	{
		Scanner input = new Scanner(System.in);
		try {
			System.out.print(prompt);
			return input.nextLong();
		} catch(InputMismatchException e) {
			System.out.println("Invalid response! Please try again.\n");
			return tryGetLong(prompt);
		}
	}
}