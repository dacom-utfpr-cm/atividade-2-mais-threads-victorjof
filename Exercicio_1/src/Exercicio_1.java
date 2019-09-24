/* 
Author: Victor Figueira
Date:  24/09/2019
Task: 1. Faça um programa em Java que consulte periodicamente o
estado de um conjunto de threads.
*/
public class Exercicio_1 {
    public static void main(String[] args) throws InterruptedException{
        threadVoid t1 = new threadVoid(1);
        threadVoid t2 = new threadVoid(2);
        threadVoid t3 = new threadVoid(3);

        t1.start();
        t2.start();
        t2.interrupt();
        stateCheck stateChecker = new stateCheck(t1,t2,t3);
        stateChecker.start();
        Thread.sleep(500);
        t2.sleep(500);

    }

}
