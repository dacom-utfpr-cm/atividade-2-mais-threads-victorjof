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
    public static void main(String[] args) {
        //Generate random elements into a list.
        Random rand = new Random();
        int num_elements = rand.nextInt(50);
        int[] values = new int[num_elements];

        for (int i = 0; i < num_elements; i++) {
            values[i]=rand.nextInt(num_elements);
        }
        int num_threads = Runtime.getRuntime().availableProcessors();
        new MergeSort(values,num_elements);
        System.out.println(Arrays.toString(values));
    }
}
