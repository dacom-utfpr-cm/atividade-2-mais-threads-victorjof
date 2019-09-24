/* 
Author: Victor Figueira
Date:  24/09/2019
Task: 2. Faça um programa em Java para testar um conjunto de
operações sobre um grupo de threads. Use o ThreadGroup.
*/

public class Exercicio_2 {
    public static void main (String [] args) throws InterruptedException
    {
        ThreadGroup tg = new ThreadGroup("group1");
        Thread t1 = new Thread(tg,"thread1");
        Thread t2 = new Thread(tg,"thread2");
        t1.start();
        t2.start();
        System.out.println("Active threads: "+tg.activeCount()+" in "+ tg.getName());



        //Thread group inside other thread group
        ThreadGroup tg2 =  new ThreadGroup(tg,"group2");
        Thread t3 = new Thread(tg, "thread3");
        System.out.println("Group` max priority is "+ tg.getMaxPriority());

        tg2.interrupt();
        System.out.println("Threads from group are " + (tg.isDaemon() ? "Daemon" : "User") + " type");
        tg.list();
        tg.interrupt();

    }
}
