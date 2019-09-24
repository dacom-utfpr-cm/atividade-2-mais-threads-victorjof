import java.util.ArrayList;
import java.util.Arrays;

public class stateCheck extends Thread{
    private ArrayList<threadVoid> watchlist;
    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(5000);
            }

            catch (InterruptedException e){}

            for (threadVoid t : watchlist) {
                System.out.printf("Thread %d state's %s %n",t.getID(),t.getState().toString());
            }
        }
    }

    stateCheck(threadVoid ... threads){
        watchlist = new ArrayList<threadVoid>();
        watchlist.addAll(Arrays.asList(threads));
    }
}
