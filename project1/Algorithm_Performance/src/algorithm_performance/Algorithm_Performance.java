/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package algorithm_performance;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import javax.swing.*;

/**
 * @author Carter Booth
 * CS 2430, Section 501
 * Programming Project 1 - Spring 2026
 * This is the main file that creates a JFrame and it's components, also causes
 * the sorts to run.
 */
public class Algorithm_Performance
{

    // This is all the variables used for the program.
    private static int[] nums;
    private static int size = 4;
    final private static Sorts s = new Sorts();
    final private static JFrame frame = new JFrame("Algorithm Performance");
    final private static JPanel panel = new JPanel();
    private static JLabel l1, l2, l3, l4, l5;
    final private static JButton button = new JButton();
    final private static JSpinner spinner = new JSpinner(new SpinnerNumberModel(4, 4, 20, 1));

    // The Main method creates the JFrame and JPanel that display the output of the sorts.
    public static void main(String[] args)
    {
        frame.add(panel);
        frame.setSize(800, 200);
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(l1 = new JLabel());
        panel.add(l2 = new JLabel());
        panel.add(l3 = new JLabel());
        panel.add(l4 = new JLabel());
        panel.add(l5 = new JLabel());
        button.setText("Refresh");
        button.addActionListener((ActionEvent e) ->
        {
            size = (int) spinner.getValue();
            refresh();
        });
        panel.add(spinner);
        panel.add(button);
        spinner.setMaximumSize(new Dimension(40, 30));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        refresh();
        frame.setVisible(true);
    }

    // This generates an array of a certain size for the sorting algorithms to use.
    private static int[] generate(int size)
    {
        int[] generated;
        generated = new int[size];

        for (int i = 0; i < size; i++)
        {
            generated[i] = (int) (Math.random() * 100);
        }

        return generated;
    }

    // This creates a new unsorted array and runs the sorts then refreshes the output.
    private static void refresh()
    {
        nums = generate(size);
        l1.setText("Unsorted Array: " + Arrays.toString(nums));
        l2.setText("Merge Sort: " + Arrays.toString(s.startMerge(nums)) + ", Compares: " + s.getCycles());
        l3.setText("Quick Sort: " + Arrays.toString(s.startQuick(nums)) + ", Compares: " + s.getCycles());
        l4.setText("Heap Sort: " + Arrays.toString(s.startHeap(nums)) + ", Compares: " + s.getCycles());
        l5.setText("Cocktail Sort: " + Arrays.toString(s.startCocktail(nums)) + ", Compares: " + s.getCycles());
    }
}
