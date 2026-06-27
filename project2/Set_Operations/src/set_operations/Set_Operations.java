/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package set_operations;

import java.awt.Dimension;
import java.util.Arrays;
import java.util.Random;
import javax.swing.*;

/**
 * @author Carter Booth
 * CS 2430, Section 501
 * Programming Project 1 - Summer 2026
 * This file is the entirety of Project 2
 */
public class Set_Operations
{
    // These are all the variables used in the file.
    public static boolean[] Ab, Bb; // The lowercase b stands for boolean
    public static int[] Ai = {3, 0, 1, 2, 0, 1, 4, 0, 2, 1, 0, 2}; // The lowercase i stands for integer
    public static int[] Bi = {1, 2, 0, 2, 3, 0, 1, 2, 0, 1, 3, 0}; // The each element is the number of times a thing appears
    public static JFrame frame = new JFrame("Set/MultiSet Operations");
    public static JPanel setPanel = new JPanel();
    public static JPanel multiPanel = new JPanel();
    public static JPanel wrap = new JPanel();
    public static JLabel set1, set2, set3, set4, set5, set6, set7, set8, set9, multi1, multi2, multi3, multi4, multi5, multi6, multi7;
    
    
    // The main method creates the window and displays the output.
    public static void main(String[] args)
    {
        Ab = generateBools();
        Bb = generateBools();
        
        wrap.add(setPanel);
        wrap.add(multiPanel);
        frame.add(wrap);
        frame.setSize(550, 420);
        
        setPanel.setLayout(new BoxLayout(setPanel, BoxLayout.PAGE_AXIS));
        setPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        multiPanel.setLayout(new BoxLayout(multiPanel, BoxLayout.PAGE_AXIS));
        multiPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        wrap.setLayout(new BoxLayout(wrap, BoxLayout.PAGE_AXIS));
        
        setPanel.add(set1 = new JLabel());
        setPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        setPanel.add(set2 = new JLabel());
        setPanel.add(set3 = new JLabel());
        setPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        setPanel.add(set4 = new JLabel());
        setPanel.add(set5 = new JLabel());
        setPanel.add(set6 = new JLabel());
        setPanel.add(set7 = new JLabel());
        setPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        setPanel.add(set8 = new JLabel());
        setPanel.add(set9 = new JLabel());
        
        multiPanel.add(multi1 = new JLabel());
        multiPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        multiPanel.add(multi2 = new JLabel());
        multiPanel.add(multi3 = new JLabel());
        multiPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        multiPanel.add(multi4 = new JLabel());
        multiPanel.add(multi5 = new JLabel());
        multiPanel.add(multi6 = new JLabel());
        multiPanel.add(multi7 = new JLabel());
        
        set1.setText("Set Operations");
        set1.setFont(set1.getFont().deriveFont(18f));
        set2.setText("Boolean Array A: " + Arrays.toString(Ab));
        set3.setText("Boolean Array B: " + Arrays.toString(Bb));
        set4.setText("Union: " + Arrays.toString(union(Ab, Bb)));
        set5.setText("Intersect: " + Arrays.toString(intersect(Ab, Bb)));
        set6.setText("Difference: " + Arrays.toString(diff(Ab, Bb)));
        set7.setText("Symetric Difference: " + Arrays.toString(symetricDiff(Ab, Bb)));
        set8.setText("Not A: " + Arrays.toString(not(Ab)));
        set9.setText("Not B: " + Arrays.toString(not(Bb)));

        multi1.setText("Multiset (Bag) Operations");
        multi1.setFont(set1.getFont().deriveFont(18f));
        multi2.setText("Multiset A: " + Arrays.toString(Ai));
        multi3.setText("Multiset B: " + Arrays.toString(Bi));
        multi4.setText("Union: " + Arrays.toString(multiUnion(Ai, Bi)));
        multi5.setText("Intersect: " + Arrays.toString(multiIntersect(Ai, Bi)));
        multi6.setText("Difference: " + Arrays.toString(multiDiff(Ai, Bi)));
        multi7.setText("Sum: " + Arrays.toString(multiSum(Ai, Bi)));
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    // This method creates a randomized array of 12 booleans
    public static boolean[] generateBools()
    {
        boolean[] X = new boolean[12];
        Random rand = new Random();
        
        for(int i = 0; i < X.length; i++)
        {
            X[i] = rand.nextBoolean();
        }
        
        return X;
    }
    
    // All of the next methods return arrays. I describe some only with one element from each array.
    
    // This reverses the value of all the elements in a boolean array
    public static boolean[] not(boolean[] A)
    {
        boolean[] X = Arrays.copyOf(A, A.length);
        
        for(int i = 0; i < X.length; i++)
        {
            X[i] = !X[i];
        }
        
        return X;
    }
    
    // This compares each element and returns true if at least one value is true
    public static boolean[] union(boolean[] A, boolean[] B)
    {
        boolean[] X = new boolean[A.length];
        
        for(int i = 0; i < X.length; i++)
        {
            X[i] = A[i] || B[i];
        }
        
        return X;
    }
    
    // This returns true if both elements are true
    public static boolean[] intersect(boolean[] A, boolean[] B)
    {
        boolean[] X = new boolean[A.length];
        
        for(int i = 0; i < X.length; i++)
        {
            X[i] = A[i] && B[i];
        }
        
        return X;
    }
    
    // This returns true if A[i] is true and B[i] is false
    public static boolean[] diff(boolean[] A, boolean[] B)
    {
        boolean[] X = new boolean[A.length];
        
        for(int i = 0; i < X.length; i++)
        {
            X[i] = A[i] && !B[i];
        }
        
        return X;
    }
    
    // This returns true if the two elements are different
    // Could have coded it as A[i] ^ B[i]
    public static boolean[] symetricDiff(boolean[] A, boolean[] B)
    {
        boolean[] X = new boolean[A.length];
        
        for(int i = 0; i < X.length; i++)
        {
            X[i] = (A[i] && !B[i]) || (B[i] && !A[i]);
        }
        
        return X;
    }
    
    // This returns the value of the largest element
    public static int[] multiUnion(int[] A, int[] B)
    {
        int[] X = new int[A.length];
        
        for(int i = 0; i < X.length; i++)
        {
            X[i] = Math.max(A[i], B[i]);
        }
        
        return X;
    }
    
    // This returns the smallest value between the two elements
    public static int[] multiIntersect(int[] A1, int[] B1)
    {
        int[] X = new int[A1.length];
        
        for(int i = 0; i < X.length; i++)
        {
            X[i] = Math.min(A1[i], B1[i]);
        }
        
        return X;
    }
    
    // This subtracts the value of B[i] from A[i], cannot be negative
    public static int[] multiDiff(int[] A, int[] B)
    {
        int[] X = new int[A.length];
        
        for(int i = 0; i < X.length; i++)
        {
            X[i] = A[i] - B[i];
            
            if(X[i] < 0)
            {
                X[i] = 0;
            }
        }
        
        return X;
    }
    
    // This adds the value of A[i] and B[i]
    public static int[] multiSum(int[] A, int[] B)
    {
        int[] X = new int[A.length];
        
        for(int i = 0; i < X.length; i++)
        {
            X[i] = A[i] + B[i];
        }
        
        return X;
    }
}
