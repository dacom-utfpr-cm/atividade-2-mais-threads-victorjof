//Without use of shared memory/sync  a primality test was done instead of a (ideal) sieve.


public class primeThread extends Thread {

    private final int up_limit,down_limit;

    @Override
    public void run(){
        for(int i = down_limit; i<up_limit; i++){
            if(isPrime(i)){
                System.out.print(i+" ");
            }
        }
    }

    public static boolean isPrime(int n){
        if (n <= 3){
            return n >1;
        }

        else if (n % 2 == 0  ||  n % 3 == 0){
            return false;
        }
        int i = 5;
        while(i * i <= n){
            if(n % i == 0 || n % (i+2) == 0){
                return false;
            }
            i += 6;
        }
        return true;
    }


    primeThread(int down_limit, int up_limit){
        this.down_limit = down_limit;
        this.up_limit = up_limit;
    }

}
