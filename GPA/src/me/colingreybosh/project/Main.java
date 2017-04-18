package me.colingreybosh.project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main
{
    private JSpinner spinner1;
    private JRadioButton APRadioButton;
    private JRadioButton honorsRadioButton;
    private JPanel coursePanel;
    private JPanel panelMain;
    private JButton addNewCourseButton;
    private JButton removeButton;
    private JRadioButton nonHonorsRadioButton;
    private JSpinner grade;

    public Main() {

    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Lol");
        frame.setContentPane(new Main().panelMain);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        spinner1.setModel(new SpinnerNumberModel(0.0, 0.0, 100, .01));
    }
}
