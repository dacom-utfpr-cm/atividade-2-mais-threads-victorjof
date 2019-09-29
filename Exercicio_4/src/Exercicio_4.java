/* 
Author: Victor Figueira
Date:  24/09/2019
Task: 4. Faça um programa em Java que realize uma busca paralela
em um vetor de inteiros. Informe para o método: valor
procurado, vetor de inteiros e o número de threads.
*/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Exercicio_4 {

    public static void main(String[] args) {
        //Generate random elements into a list and a value that may not be present in that list.
        Random rand = new Random();
        ArrayList<Integer> values = new ArrayList<>();
        int num_elements = rand.nextInt(100);
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
        int[] position_help = new int[values.size()];

        //jump size
        int jump =  values.size() / num_threads;


        for (int i = 0; i <= values.size() - jump; i += jump) {
            threads.add(new findThread(value, values, i, i + jump));
        }

        for (int i = 0; i < values.size(); i++) {
            position_help[i] = i;
        }


        for (findThread t : threads) {
            t.start();
        }


        //in this threaded search  version, all threads should be terminated when a value is found
        search:
        {
            while (true) {
                for (findThread t : threads) {
                    if (t.foundValue()) {
                        System.out.printf("Found value %d at position %d in list %n",t.getValueFound(),t.getValueIndex());
                        break search;
                    }

                }
                //In case the desirable value doesn't exist in the list all threads will terminate, and the loop should be terminated.
                if(threads.stream().allMatch(t -> t.getState() == Thread.State.TERMINATED)){
                    System.out.printf("Value %d not found in list %n", value);
                    break search;
                }

            }
        }
        System.out.println(values);
        System.out.println(Arrays.toString(position_help));

        }


    }

