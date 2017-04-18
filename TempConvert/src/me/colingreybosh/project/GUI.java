package me.colingreybosh.project;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class GUI
{
    private JFormattedTextField celsiusField;
    private JFormattedTextField kelvinField;
    private JFormattedTextField fahrenheitField;
    private JPanel panelMain;

    public GUI() {
        fahrenheitField.addActionListener(e ->
        {
            String input;
            DecimalFormat tempFormat = new DecimalFormat("#.##");

            // Remove any non-numerical characters using regex
            input = fahrenheitField.getText().replaceAll("[^\\d.-]", "");
            // Format and round the input to 2 decimal places
            input = tempFormat.format(Double.parseDouble(input));
            fahrenheitField.setText(input);

            if (!input.equals("")) {
                // Set the value in the celsius text field equal to the formatted output from the convertFtoC method
                celsiusField.setText(tempFormat.format(convertFtoC(Double.parseDouble(input))));
                kelvinField.setText(tempFormat.format(convertFtoK(Double.parseDouble(input))));
            }
            if (Double.parseDouble(kelvinField.getText()) < 0.0) {
                fahrenheitField.setText("Below Absolute Zero!");
                // Highlight text
                fahrenheitField.setSelectionStart(0);
                fahrenheitField.setSelectionEnd(fahrenheitField.getText().length());

                celsiusField.setText("Below Absolute Zero!");
                kelvinField.setText("Below Absolute Zero!");
            }
        });

        celsiusField.addActionListener(e ->
        {
            String input;
            DecimalFormat tempFormat = new DecimalFormat("#.##");

            // Remove any non-numerical characters
            input = celsiusField.getText().replaceAll("[^\\d.-]", "");
            // Format and round the input to 2 decimal places
            input = tempFormat.format(Double.parseDouble(input));
            celsiusField.setText(input);

            if (!input.equals("")) {
                fahrenheitField.setText(tempFormat.format(convertCtoF(Double.parseDouble(input))));
                kelvinField.setText(tempFormat.format(convertCtoK(Double.parseDouble(input))));
            }
            if (Double.parseDouble(kelvinField.getText()) < 0.0) {
                fahrenheitField.setText("Below Absolute Zero!");

                celsiusField.setText("Below Absolute Zero!");
                celsiusField.setSelectionStart(0);
                celsiusField.setSelectionEnd(celsiusField.getText().length());

                kelvinField.setText("Below Absolute Zero!");
            }
        });

        kelvinField.addActionListener(e ->
        {
            String input;
            DecimalFormat tempFormat = new DecimalFormat("#.##");

            // Remove any non-numerical characters
            input = kelvinField.getText().replaceAll("[^\\d.-]", "");
            // Format and round the input to 2 decimal places
            input = tempFormat.format(Double.parseDouble(input));
            kelvinField.setText(input);

            if (!input.equals("")) {
                celsiusField.setText(tempFormat.format(convertKtoC(Double.parseDouble(input))));
                fahrenheitField.setText(tempFormat.format(convertKtoF(Double.parseDouble(input))));
            }
            if (Double.parseDouble(kelvinField.getText()) < 0.0) {
                fahrenheitField.setText("Below Absolute Zero!");
                celsiusField.setText("Below Absolute Zero!");

                kelvinField.setText("Below Absolute Zero!");
                kelvinField.setSelectionStart(0);
                kelvinField.setSelectionEnd(kelvinField.getText().length());
            }
        });
    }

    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run() {
                try {
                    // Create a new frame
                    JFrame frame = new JFrame("Temperature Conversion");
                    // Add the JPanel to the frame
                    frame.setContentPane(new GUI().panelMain);
                    // Close the program when the close button is clicked
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                    // Set the window to 300 x 200
                    frame.setPreferredSize(new Dimension(300, 200));
                    // Do not allow the window to be resized
                    frame.setResizable(false);
                    // Center the window
                    frame.setLocationRelativeTo(null);
                    // Display the frame
                    frame.pack();
                    frame.setVisible(true);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

    // Temperature conversion methods //

    private static double convertFtoC(double temp) {
        return ((temp - 32.0) * (5.0 / 9.0));
    }

    private static double convertFtoK(double temp) {
        return (convertFtoC(temp) + 273.15);
    }

    private static double convertCtoF(double temp) {
        return ((temp * (9.0 / 5.0)) + 32.0);
    }

    private static double convertCtoK(double temp) {
        return (temp + 273.15);
    }

    private static double convertKtoC(double temp) {
        return (temp - 273.15);
    }

    private static double convertKtoF(double temp) {
        return convertCtoF(temp - 273.15);
    }
}