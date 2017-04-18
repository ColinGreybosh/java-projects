package JavaI.project.example.NewWorld;

// Includes these libraries to be used in the program
import java.text.SimpleDateFormat;
import java.util.Date;

// Creates a publicly available class named
public class Hello {
    // The main method of a program is automatically ran
    public static void main(String[] args) {
        // First, get the date as seconds since 1/1/1970, the unix epoch
        // Note that a Date object also contains time information
        Date now = new Date();
        // Second, create a formatter object
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, MMM dd, yyyy HH:mm:ss");
        // Third, apply the formatter to the date
        String formattedDate = formatter.format(now);
        // Finally, add out formatted date to our output
        // args[0] and args[1] references arguments that are passed to the program when it is ran
        System.out.println(formattedDate + "> Hello, " + args[0] + " " + args[1] + "!");
    }
}