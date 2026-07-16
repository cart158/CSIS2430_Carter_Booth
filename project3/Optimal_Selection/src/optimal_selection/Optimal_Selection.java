/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package optimal_selection;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author Carter Booth CS 2430, Section 501 Programming Project 3 - Spring 2026
 * This is the main file that creates a text file called output.txt
 */
public class Optimal_Selection
{

    // I chose to store the experiments in arraylists that get sent to the methods.
    private final static ArrayList<Experiment> possibles = new ArrayList();
    private final static ArrayList<Experiment> payload = new ArrayList();
    private final static int capacity = 700;

    // The main method initializes the Experiment values then calls each method and creates a text file.
    public static void main(String[] args)
    {
        possibles.add(new Experiment("Cloud Patterns", 36, 5));
        possibles.add(new Experiment("Solar Flares", 264, 9));
        possibles.add(new Experiment("Solar Power", 188, 6));
        possibles.add(new Experiment("Binary Stars", 203, 8));
        possibles.add(new Experiment("Relativity", 104, 8));
        possibles.add(new Experiment("Seed Viability", 7, 4));
        possibles.add(new Experiment("Sun Spots", 90, 2));
        possibles.add(new Experiment("Mice Tumors", 65, 8));
        possibles.add(new Experiment("Microgravity Plant Growth", 75, 5));
        possibles.add(new Experiment("Micrometeorites", 170, 9));
        possibles.add(new Experiment("Cosmic Rays", 80, 7));
        possibles.add(new Experiment("Yeast Fermentation", 27, 4));

        System.out.println(highRateFirst(possibles, capacity));
        System.out.println(lightFirst(possibles, capacity));
        System.out.println(ratioScore(possibles, capacity));
        System.out.println(bruteForce(possibles, capacity));
        System.out.println(dynamic(possibles, capacity));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt")))
        {
            writer.write(highRateFirst(possibles, capacity));
            writer.newLine();
            writer.write(lightFirst(possibles, capacity));
            writer.newLine();
            writer.write(ratioScore(possibles, capacity));
            writer.newLine();
            writer.write(bruteForce(possibles, capacity));
            writer.newLine();
            writer.write("""
                         Only the Brute Force method was able to get the optimal solution to the problem.
                         The high rate first was the worst while the light first and highest ratio methods were close to the optimal.
                         """);
            writer.newLine();
            writer.write(dynamic(possibles, capacity));
            writer.newLine();
            writer.write("""
                         The Dynamic Programming method is much faster than Brute Force because it's runtime is O(n * w).
                         The Brute Force has a runtime of O(2^n) which is exponential plus the time doing operations done for each subset.
                         The Brute Force method is easier to understand at a glance because how it works is fairly straightforward, it finds the best subset (top three in my case) of items and discards the rest.
                         Dynamic Programming is more complex for a few reasons such as needing to back track to get the items in the best subset.
                         """);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // The "Greedy" method names I think are self explanatory. All the methods return a string of the subset each one finds.
    // highRateFirst sorts the ArrayList by the rate of experiments in descending order then takes from the front of the list.
    // Then adds the selected items into another ArrayList that is used to build the output.
    public static String highRateFirst(ArrayList<Experiment> list, int cap)
    {
        payload.clear();

        int totalWeight, totalRate;
        ArrayList<Experiment> items = new ArrayList(list);
        StringBuilder out = new StringBuilder();

        totalWeight = 0;
        totalRate = 0;

        items.sort(Comparator.comparingInt(Experiment::getRate).reversed());

        for (Experiment possible : items)
        {
            if ((totalWeight + possible.getWeight()) < cap)
            {
                payload.add(possible);
                totalWeight += possible.getWeight();
                totalRate += possible.getRate();
            }
        }

        out.append("High Rate First");
        out.append("\nTotal Rate: ");
        out.append(totalRate);

        out.append("\nExperiments: ");
        for (Experiment item : payload)
        {

            out.append(item.getName());
            out.append(", ");
        }

        out.append("\nTotal Weight: ");
        out.append(totalWeight);
        out.append("\n");

        return out.toString();
    }

    // lightFirst sorts the ArrayList by the weight in ascending order then takes from the front of the list.
    // Then adds the selected items into another ArrayList that is used to build the output.
    public static String lightFirst(ArrayList<Experiment> list, int cap)
    {
        payload.clear();

        int totalWeight, totalRate;
        ArrayList<Experiment> items = new ArrayList(list);
        StringBuilder out = new StringBuilder();

        totalWeight = 0;
        totalRate = 0;

        items.sort(Comparator.comparingInt(Experiment::getWeight));

        for (Experiment possible : items)
        {
            if ((totalWeight + possible.getWeight()) < cap)
            {
                payload.add(possible);
                totalWeight += possible.getWeight();
                totalRate += possible.getRate();
            }
        }

        out.append("Low Weight First");
        out.append("\nTotal Rate: ");
        out.append(totalRate);

        out.append("\nExperiments: ");
        for (Experiment item : payload)
        {

            out.append(item.getName());
            out.append(", ");
        }

        out.append("\nTotal Weight: ");
        out.append(totalWeight);
        out.append("\n");

        return out.toString();
    }

    // ratioScore sorts the ArrayList based on the rate to weight ratio in descending order and takes from the front of the list.
    // Then adds the selected items into another ArrayList that is used to build the output.
    public static String ratioScore(ArrayList<Experiment> list, int cap)
    {
        payload.clear();

        int totalWeight, totalRate;
        ArrayList<Experiment> items = new ArrayList(list);
        StringBuilder out = new StringBuilder();

        totalWeight = 0;
        totalRate = 0;

        items.sort(Comparator.comparingDouble((Experiment e) -> (double) e.getRate() / e.getWeight()).reversed());

        for (Experiment item : items)
        {
            if ((totalWeight + item.getWeight()) < cap)
            {
                payload.add(item);
                totalWeight += item.getWeight();
                totalRate += item.getRate();
            }
        }

        out.append("Highest Rate to Weight Ratio");
        out.append("\nTotal Rate: ");
        out.append(totalRate);

        out.append("\nExperiments: ");
        for (Experiment item : payload)
        {

            out.append(item.getName());
            out.append(", ");
        }

        out.append("\nTotal Weight: ");
        out.append(totalWeight);
        out.append("\n");

        return out.toString();
    }

    // bruteForce uses bit masking to check all 4096 subsets and it picks the top 3 best subsets.
    // Then uses the chosen bit masks to get the items that are included and builds the output string.
    public static String bruteForce(ArrayList<Experiment> list, int capacity)
    {
        ArrayList<Experiment> items = new ArrayList(list);
        StringBuilder out = new StringBuilder();
        int n = items.size();
        int[] bestRates =
        {
            0, 0, 0
        };
        int[] bestMasks =
        {
            0, 0, 0
        };

        for (int m = 0; m < (1 << n); m++)
        {
            int totalWeight = 0;
            int totalRate = 0;

            for (int i = 0; i < n; i++)
            {
                if ((m & (1 << i)) != 0)
                {
                    totalWeight += items.get(i).getWeight();
                    totalRate += items.get(i).getRate();
                }
            }

            if (totalWeight <= capacity)
            {
                if (totalRate > bestRates[0])
                {
                    bestRates[2] = bestRates[1];
                    bestMasks[2] = bestMasks[1];

                    bestRates[1] = bestRates[0];
                    bestMasks[1] = bestMasks[0];

                    bestRates[0] = totalRate;
                    bestMasks[0] = m;
                } else if (totalRate > bestRates[1])
                {
                    bestRates[2] = bestRates[1];
                    bestMasks[2] = bestMasks[1];

                    bestRates[1] = totalRate;
                    bestMasks[1] = m;
                } else if (totalRate > bestRates[2])
                {
                    bestRates[2] = totalRate;
                    bestRates[2] = m;
                }
            }
        }

        out.append("Brute Force Top Three");

        for (int i = 0; i < 3; i++)
        {
            int totalWeight = 0;

            out.append("\nRank #");
            out.append(i + 1);
            out.append("\nTotal Rate: ");
            out.append(bestRates[i]);
            out.append("\nExperiments: ");

            for (int x = 0; x < items.size(); x++)
            {
                if ((bestMasks[i] & (1 << x)) != 0)
                {
                    out.append(items.get(x).getName());
                    out.append(", ");
                    totalWeight += items.get(x).getWeight();
                }
            }
            out.append("\nTotal Weight: ");
            out.append(totalWeight);
            out.append("\n");
        }

        return out.toString();
    }

    // dynamic uses a 2D array that stores the rates based on the item and capacity.
    // It chooses to include or exclude items based on the remaining capacity. It keeps the larger value and repeats until it finds the best rate.
    // It also needs to backtrack at the end to get the items for the output.
    public static String dynamic(ArrayList<Experiment> list, int cap)
    {
        ArrayList<Experiment> items = new ArrayList(list);
        StringBuilder out = new StringBuilder();
        int n = items.size();
        int[][] dyTable = new int[n + 1][cap + 1];

        for (int i = 1; i <= n; i++)
        {
            int weight = items.get(i - 1).getWeight();
            int rate = items.get(i - 1).getRate();

            for (int x = 0; x <= cap; x++)
            {
                dyTable[i][x] = dyTable[i - 1][x];

                if (weight <= x)
                {
                    int value = dyTable[i - 1][x - weight] + rate;
                    dyTable[i][x] = Math.max(dyTable[i][x], value);
                }
            }
        }

        out.append("Dynamic Programming\n");
        out.append("Best Rate: ");
        out.append(dyTable[n][cap]);
        out.append("\nExperiments: ");

        int totalWeight = cap;

        for (int i = n; i > 0; i--)
        {
            if (dyTable[i][cap] != dyTable[i - 1][cap])
            {
                out.append(items.get(i - 1).getName());
                out.append(", ");
                cap -= items.get(i - 1).getWeight();
            }
        }

        totalWeight -= cap;

        out.append("\nTotal Weight: ");
        out.append(totalWeight);
        out.append("\n");
        
        return out.toString();
    }
}
