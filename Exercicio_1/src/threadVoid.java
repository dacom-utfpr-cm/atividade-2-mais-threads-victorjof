

public class threadVoid extends Thread  {
    final private int id;

    @Override
    public void run() {
        int i = 0;
        boolean flag = false;
        while (true) {

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                flag = true;
            }
            if (flag) {
                currentThread().interrupt();

            }

        }
    }
    public int getID(){
        return id;
    }

    threadVoid(int id){
        this.id=id;
    }


}
