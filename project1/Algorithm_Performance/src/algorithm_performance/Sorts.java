/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algorithm_performance;

/**
 *
 * @author booth
 */
public class Sorts
{
    private int cycles;
    
    public Sorts()
    {
    
    }
    
    public int getCycles()
    {
        return cycles;
    }
    
    public int[] startMerge(int[] unsorted)
    {
        int[] sorted = unsorted;
        cycles = 0;
        mergeSort(sorted, 0, sorted.length - 1);
        return sorted;
    }
    
    public void mergeSort(int[] array, int left, int right)
    {
        int mid;
        
        if(left < right)
        {
            mid = left + (right - left) / 2;
            
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            
            merge(array, left, mid, right);
        }
    }
    
    private void merge(int[] array, int left, int mid, int right)
    {
        int n1, n2;
        int[] L, R;
        
        n1 = mid - left + 1;
        n2 = right - mid;
        
        L = new int[n1];
        R = new int[n2];
        
        for(int i = 0; i < n1; i++)
        {
            L[i] = array[left + i];
        }
        for(int j = 0; j < n2; j++)
        {
            R[j] = array[mid + 1 + j];
        }
        
        int i, j, k;
        
        i = 0;
        j = 0;
        k = left;
        
        while(i < n1 && j < n2)
        {
            if(L[i] <= R[j])
            {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            k++;
            cycles++;
        }
        
        while(i < n1)
        {
            array[k] = L[i];
            i++;
            k++;
        }
        
        while(j < n2)
        {
            array[k] = R[j];
            j++;
            k++;
        }
    }
    
    public int[] startQuick(int[] unsorted)
    {
        int[] sorted = unsorted;
        cycles = 0;
        quickSort(sorted, 0, sorted.length - 1);
        return sorted;
    }
    
    private int quickPart(int[] array, int low, int high)
    {
        int pivot = array[high];
        int i = low - 1;
        
        for(int j = 0; j < high; j++)
        {
            cycles++;
            if(array[j] < pivot)
            {
                i++;
                quickSwap(array, i, j);
            }
        }
        
        quickSwap(array, i + 1, high);
        return i + 1;
    }
    
    private void quickSwap(int[] array, int i, int j)
    {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
    private void quickSort(int[] array, int low, int high)
    {
        if(low < high)
        {
            int pivot = quickPart(array, low, high);
            
            quickSort(array, low, pivot - 1);
            quickSort(array, pivot + 1, high);
        }
    }
}
