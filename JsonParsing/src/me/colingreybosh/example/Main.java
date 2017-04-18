package me.colingreybosh.example;

import java.io.BufferedReader;
//import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.text.NumberFormatter;

import org.json.JSONException;
import org.json.JSONObject;


public class Main
{
    public static void main(String[] args) throws IOException, JSONException
    {
        String key, city, state, locationFull;
        boolean again;
        key = "4e770eb535baee60";

        do {
            city = tryGetString("Enter a city: ");
            state = tryGetString("Enter a state: ");
            locationFull = city.concat(", ").concat(state);

            city = parseInput(city);
            state = parseInput(state);

            JSONObject json = readJsonFromUrl("http://api.wunderground.com/api/" + key + "/astronomy/q/" + state + "/" + city + ".json");

            DecimalFormat format = new DecimalFormat("#00");

            int sunriseTimeHR = json.getJSONObject("sun_phase").getJSONObject("sunrise").getInt("hour");
            int sunriseTimeMN = json.getJSONObject("sun_phase").getJSONObject("sunrise").getInt("hour");
            String sunriseTime = format.format(sunriseTimeHR).concat(":").concat(format.format(sunriseTimeMN));

            int sunsetTimeHR = json.getJSONObject("sun_phase").getJSONObject("sunset").getInt("hour");
            int sunsetTimeMN = json.getJSONObject("sun_phase").getJSONObject("sunset").getInt("hour");
            String sunsetTime = format.format(sunsetTimeHR).concat(":").concat(format.format(sunsetTimeMN));

            System.out.println("Today, the sunrise time for " + locationFull + " is " + sunriseTime + " and sunset time is " + sunsetTime + "!\n");
            again = loop("Would you like to retrieve another sunset/sunrise time?");
        } while (again);
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException
    {
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            return new JSONObject(jsonText);
        }
    }

    private static String readAll(Reader rd) throws IOException
    {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1)
        {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private static String parseInput(String input)
    {
        return input.replaceAll(" ", "_");
    }

    private static String tryGetString(String question)
    {
        // Initialize scanner
        Scanner input = new Scanner(System.in);
        String response;
        try { // Try to run this code
            do {
                // Print the prompt provided when the method is called
                System.out.print(question);
                response = input.nextLine();
            } while (response.equals(""));
            // Return the user input
            return response;
        } catch(InputMismatchException e) { // Look for an InputMismatchException error
            // This error will print if the user does not input a string
            System.out.println("\nInvalid response! Please try again.\n");
            // Recursively call tryGetString() again
            return tryGetString(question);
        }
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
}
