import javax.swing.*;

public class Main
{
    public static void main(String[] args)
    {
        int a, b, c;
        String message;

        a = Integer.parseInt(JOptionPane.showInputDialog("Enter a number:", "24"));
        b = Integer.parseInt(JOptionPane.showInputDialog("Enter a second number:", "48"));
        c = Integer.parseInt(JOptionPane.showInputDialog("Enter a third number:", "96"));

        message = a + " + " + b + " + " + c + " = " + (a + b + c);
        JOptionPane.showMessageDialog(null , message);
    }
}