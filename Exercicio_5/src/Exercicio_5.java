/* 
Author: Victor Figueira
Date:  24/09/2019
Task: 5. Faça um programa multithreaded em Java que ordene um
vetor usando o Merge Sort recursivo. Faça com que a thread
principal dispare duas threads para classificar cada metade do
vetor.
*/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Exercicio_5 {

    public static boolean isSorted(int[] values){
        if(values.length <2){
            return true;
        }
        for(int i =0; i < values.length-1; i++){
            if(values[i] > values[i+1]){
                return false;
            }
        }

        return true;

    }
    public static void main(String[] args) {
        //Generate random elements into a list.
        Random rand = new Random();
        int num_elements  = 10_000_0000;
        int[] values = new int[num_elements];
        for (int i = 0; i < num_elements; i++) {
            values[i]=rand.nextInt(num_elements);
        }

        //sort array values
        new MergeSort(values,num_elements);
        System.out.println((isSorted(values)) ? ("it works!") : ("..."));
    }
}
