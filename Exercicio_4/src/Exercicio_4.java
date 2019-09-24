/* 
Author: Victor Figueira
Date:  24/09/2019
Task: 4. Faça um programa em Java que realize uma busca paralela
em um vetor de inteiros. Informe para o método: valor
procurado, vetor de inteiros e o número de threads.
*/
import java.util.ArrayList;
import java.util.Random;

public class Exercicio_4 {

    public static void main(String[] args) {
        //Generate random elements into a list and a value that may not be present in that list.
        Random rand = new Random();
        ArrayList<Integer> values = new ArrayList<>();
        int num_elements = rand.nextInt(1_000_000);
        for (int i = 0; i < num_elements; i++) {
            values.add(rand.nextInt(num_elements));

        }
        int value = rand.nextInt(num_elements);

        int num_threads = Runtime.getRuntime().availableProcessors();
        try {
            search_element(value, values, num_threads);
        } catch (InterruptedException e) {
            e.getStackTrace();
        }


    }


    public static void search_element(Integer value, ArrayList<Integer> values, Integer num_threads) throws InterruptedException {
        ArrayList<findThread> threads = new ArrayList<>();
        int jump = values.size() > (num_threads * 100) ? values.size() / num_threads : 1;


        for (int i = 0; i <= values.size() - jump; i += jump) {
            threads.add(new findThread(value, values, i, i + jump));
        }


        for (findThread t : threads) {
            t.start();
        }

        for (findThread t : threads) {
            t.join();
        }

        boolean found_flag = false;
        search:
        {
            while (true) {
                for (findThread t : threads) {
                    if (t.found_value) {
                        System.out.printf("Found value %d at position %d in list %n",values.get(t.i),t.i);
                        break search;
                    }
                }
                //In case the desirable value doesn't exist in the list all threads will terminate, and the loop should be terminated.
                if(threads.stream().allMatch(t -> t.isAlive() != true)){
                    System.out.printf("Value %d not found in list %n", value);
                    break search;
                }
            }

        }
    }

}