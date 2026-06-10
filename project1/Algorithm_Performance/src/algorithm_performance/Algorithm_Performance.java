/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package algorithm_performance;

import java.util.Arrays;

/**
 *
 * @author booth
 */
public class Algorithm_Performance
{

    /**
     * @param args the command line arguments
     */
    
    private static int[] nums;
    private static int size = 4;
    private static Sorts s;
    
    public static void main(String[] args)
    {
        s = new Sorts();
        nums = generate(size);
        System.out.println(Arrays.toString(nums));
        s.startMerge(nums);
        System.out.println(Arrays.toString(nums) + ", Compares: " + s.getCycles());
        s.startQuick(nums);
        System.out.println(Arrays.toString(nums) + ", Compares: " + s.getCycles());
    }
    
    private static int[] generate(int size)
    {
        int[] generated;
        generated = new int[size];
        
        for(int i = 0; i < size; i++)
        {
            generated[i] = (int) (Math.random() * 100);
        }
        
        return generated;
    }
}
