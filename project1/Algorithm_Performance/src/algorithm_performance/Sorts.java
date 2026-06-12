/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algorithm_performance;

import java.util.Arrays;

/**
 * @author Carter Booth
 * CS 2430, Section 501
 * Programming Project 1 - Spring 2026
 * This file is a class that has each of the 4 sorts.
 */
public class Sorts
{

    // Cycles is used to count the comparisons.
    private int cycles;

    // The constructor doesn't need to initialize anything.
    public Sorts()
    {
    }

    // Use this after calling a sort to get the number of comparisons.
    public int getCycles()
    {
        return cycles;
    }

    // This starts the Merge sort and resets the cycles back to zero.
    public int[] startMerge(int[] unsorted)
    {
        int[] sorted = Arrays.copyOf(unsorted, unsorted.length);
        cycles = 0;
        mergeSort(sorted, 0, sorted.length - 1);
        return sorted;
    }

    // This is a recursive method that splits the array in half and calls the merge method.
    private void mergeSort(int[] array, int left, int right)
    {
        int mid;

        if (left < right)
        {
            mid = left + (right - left) / 2;

            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);

            merge(array, left, mid, right);
        }
    }

    // This method compares the elements in the sub arrays and then merges the sub arrays into one sorted array.
    private void merge(int[] array, int left, int mid, int right)
    {
        int n1, n2;
        int[] L, R;

        n1 = mid - left + 1;
        n2 = right - mid;

        L = new int[n1];
        R = new int[n2];

        for (int i = 0; i < n1; i++)
        {
            L[i] = array[left + i];
        }
        for (int j = 0; j < n2; j++)
        {
            R[j] = array[mid + 1 + j];
        }

        int i, j, k;

        i = 0;
        j = 0;
        k = left;

        while (i < n1 && j < n2)
        {
            if (L[i] <= R[j])
            {
                array[k] = L[i];
                i++;
            } else
            {
                array[k] = R[j];
                j++;
            }
            k++;
            cycles++;
        }

        while (i < n1)
        {
            array[k] = L[i];
            i++;
            k++;
        }

        while (j < n2)
        {
            array[k] = R[j];
            j++;
            k++;
        }
    }

    // This method starts the Quick Sort and sets cycles to zero.
    public int[] startQuick(int[] unsorted)
    {
        int[] sorted = Arrays.copyOf(unsorted, unsorted.length);
        cycles = 0;
        quickSort(sorted, 0, sorted.length - 1);
        return sorted;
    }

    // This method creates the pivot that the Quick sort relies on and also compares the elements in the array.
    private int quickPart(int[] array, int low, int high)
    {
        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++)
        {
            cycles++;
            if (array[j] < pivot)
            {
                i++;
                quickSwap(array, i, j);
            }
        }

        quickSwap(array, i + 1, high);
        return i + 1;
    }

    // This method is for swapping elements in the array.
    private void quickSwap(int[] array, int i, int j)
    {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // This method is recursive and uses the pivot created in the quickPart method.
    private void quickSort(int[] array, int low, int high)
    {
        if (low < high)
        {
            int pivot = quickPart(array, low, high);

            quickSort(array, low, pivot - 1);
            quickSort(array, pivot + 1, high);
        }
    }

    // This method creates a binary tree that has the largest element as the root.
    private void heapify(int[] array, int n, int i)
    {
        int large = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n)
        {
            cycles++;
            if (array[left] > array[large])
            {

                large = left;
            }
        }

        if (right < n)
        {
            cycles++;
            if (array[right] > array[large])
            {
                large = right;
            }
        }

        if (large != i)
        {
            int temp = array[i];
            array[i] = array[large];
            array[large] = temp;

            heapify(array, n, large);
        }
    }

    // This method calls the heapify to create the heap then takes out one element at a time to complete the sort.
    private void heapSort(int[] array)
    {
        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--)
        {
            heapify(array, n, i);
        }

        for (int i = n - 1; i > 0; i--)
        {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            heapify(array, i, 0);
        }
    }

    // This method starts the Heap sort and resets the cycles variable back to zero.
    public int[] startHeap(int[] unsorted)
    {
        int[] sorted = Arrays.copyOf(unsorted, unsorted.length);
        cycles = 0;
        heapSort(sorted);
        return sorted;
    }

    // This method is the only one needed for the cocktail sort, it compares forwards then backwards repeating until the array is sorted.
    public void cocktailSort(int[] array)
    {
        boolean swapped = true;
        int start = 0;
        int end = array.length;

        while (swapped == true)
        {
            swapped = false;

            for (int i = start; i < end - 1; i++)
            {
                cycles++;
                if (array[i] > array[i + 1])
                {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    swapped = true;
                }
            }

            if (swapped == false)
            {
                break;
            }

            swapped = false;
            end--;

            for (int i = end - 1; i >= start; i--)
            {
                cycles++;
                if (array[i] > array[i + 1])
                {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    swapped = true;
                }
            }

            start++;
        }
    }

    // This method starts the cocktail sort and sets cycles to zero.
    public int[] startCocktail(int[] unsorted)
    {
        int[] sorted = Arrays.copyOf(unsorted, unsorted.length);
        cycles = 0;
        cocktailSort(sorted);
        return sorted;
    }
}
