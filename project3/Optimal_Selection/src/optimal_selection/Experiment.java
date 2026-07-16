/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package optimal_selection;

/**
 * @author Carter Booth
 * CS 2430, Section 501
 * Programming Project 3 - Spring 2026
 * This is a class that stores 3 fields name, weight, and rate.
 */
public class Experiment
{
    private String name;
    private int weight, rate;
    
    public Experiment(String name, int weight, int rate)
    {
        this.name = name;
        this.weight = weight;
        this.rate = rate;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getWeight()
    {
        return weight;
    }
    
    public int getRate()
    {
        return rate;
    }

    @Override
    public String toString()
    {
        return name + ", Weight " + weight + ", Rate " + rate;
    }
}
