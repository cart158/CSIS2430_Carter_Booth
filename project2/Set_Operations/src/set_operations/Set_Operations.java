/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package set_operations;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author booth
 */
public class Set_Operations
{

    public static boolean[] A, B;
    public static void main(String[] args)
    {
        A = generateBools();
        B = generateBools();
        
        System.out.println(Arrays.toString(A));
        System.out.println(Arrays.toString(B));
        System.out.println(Arrays.toString(union(A, B)));
        System.out.println(Arrays.toString(intersect(A, B)));
        System.out.println(Arrays.toString(difference(A, B)));
        System.out.println(Arrays.toString(symetricDiff(A, B)));
    }
    
    public static boolean[] generateBools()
    {
        boolean[] X = new boolean[12];
        Random rand = new Random();
        double n;
        
        for(int i = 0; i < X.length; i++)
        {
            X[i] = rand.nextBoolean();
        }
        
        return X;
    }
    
    public static boolean[] not(boolean[] A)
    {
        boolean[] X = Arrays.copyOf(A, A.length);
        
        for(int i = 0; i < X.length; i++)
        {
            X[i] = !X[i];
        }
        
        return X;
    }
    
    public static boolean[] union(boolean[] A, boolean[] B)
    {
        boolean[] X = new boolean[A.length];
        
        for(int i = 0; i < X.length; i++)
        {
            X[i] = A[i] || B[i];
        }
        
        return X;
    }
    
    public static boolean[] intersect(boolean[] A, boolean[] B)
    {
        boolean[] X = new boolean[A.length];
        
        for(int i = 0; i < X.length; i++)
        {
            X[i] = A[i] && B[i];
        }
        
        return X;
    }
    
    public static boolean[] difference(boolean[] A, boolean[] B)
    {
        boolean[] X = new boolean[A.length];
        
        for(int i = 0; i < X.length; i++)
        {
            X[i] = A[i] && !B[i];
        }
        
        return X;
    }
    
    public static boolean[] symetricDiff(boolean[] A, boolean[] B)
    {
        boolean[] X = new boolean[A.length];
        
        for(int i = 0; i < X.length; i++)
        {
            X[i] = (A[i] && !B[i]) || (B[i] && !A[i]);
        }
        
        return X;
    }
}
