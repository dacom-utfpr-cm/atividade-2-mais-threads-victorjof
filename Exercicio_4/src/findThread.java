import java.util.ArrayList;

public class findThread extends Thread{
    private final int up_limit,down_limit;
    private final ArrayList<Integer> values;
    private final int value;
    public boolean found_value = false;
    public int i = -1;

    @Override
    public void run(){
        for(int i = down_limit; i<up_limit; i++){
            if(value == values.get(i)){
                this.found_value = true;
                this.i = i;
                break;
            }
        }
    }



    findThread( int value, ArrayList<Integer> values, int down_limit, int up_limit){
        this.value = value;
        this.values = values;
        this.down_limit = down_limit;
        this.up_limit = up_limit;
    }


}
