/* 
Author: Victor Figueira
Date:  24/09/2019
Task: 3. Faça um programa em Java com threads que exiba os
números primos entre 0 e 100000.
*/
import java.util.ArrayList;

public class Exercicio_3 {
    public static void main(String[] args) throws InterruptedException {
        int cores = Runtime.getRuntime().availableProcessors();
        int n = 100_000;
        ArrayList<primeThread> threads= new ArrayList<primeThread>();

        int jump = n>(cores) ? n/cores : 1;

        int up_limit = jump;
        for(int i = 0; i <=n-jump; i+=jump){
            threads.add(new primeThread(i, i+jump));
        }

        System.out.printf("prime numbers up to %d: %n%n",n);

        for (Thread t: threads){
            t.start();
        }

        for (Thread t: threads){
            t.join();
        }

    }

}

