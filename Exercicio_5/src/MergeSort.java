// merge sort from: https://www.baeldung.com/java-merge-sort
public class MergeSort{
    private int depth = 0;
    private int max_depth = 0;

    public void single_thread_sort(int[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        single_thread_sort(l, mid);
        single_thread_sort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }

    public void threaded_sort(int[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }

        if(maxThreads()){
            Thread half_a =  new Thread(() -> { single_thread_sort(l, mid);});
            Thread half_b =  new Thread(() -> { single_thread_sort(r, n - mid);});
            half_a.start();
            half_b.start();

            try {
                half_a.join();
                half_b.join();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        else {
            Thread half_a =  new Thread(() -> { threaded_sort(l, mid);});
            Thread half_b =  new Thread(() -> { threaded_sort(r, n - mid);});
            half_a.start();
            half_b.start();

            try {
                half_a.join();
                half_b.join();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }

        }

        merge(a, l, r, mid, n - mid);
    }


    public void merge(int[] values, int[] l, int[] r, int left, int right){
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                values[k++] = l[i++];
            }
            else {
                values[k++] = r[j++];
            }
        }
        while (i < left) {
            values[k++] = l[i++];
        }
        while (j < right) {
            values[k++] = r[j++];
        }
    }

    public boolean maxThreads(){
        if(this.depth >= max_depth){
            return true;
        }
        else {
            this.depth += 1;
            return false;
        }
    }

    public void setDepth(int num_values){
        int num_threads= Runtime.getRuntime().availableProcessors();

        if(num_values/num_threads < 10_000){
            System.out.printf("Not worth using threads");
            return;
        }
        //Merge sort divides the problem into 2 parts, and my threaded  version generates 2 thread each iteration/'depth', therefore, when max_depth is reached, it should stop creating threads.
        this.max_depth = (int) (Math.log(num_threads)/Math.log(2)+1e-10);
    }

    MergeSort(int[] values, int num_elements){
        //array values  copy to test single thread execution time
        int [] aux_values = values.clone();

        setDepth(values.length);

        long startTime = System.currentTimeMillis();
        threaded_sort(values,num_elements);
        long t_estimatedTime = System.currentTimeMillis() - startTime;

        startTime = System.currentTimeMillis();
        single_thread_sort(aux_values,num_elements);
        long estimatedTime = System.currentTimeMillis() - startTime;

        System.out.printf("Threaded version of merge sort was done in %.3f seconds. Single thread was done in %.3f seconds.%n", (float)t_estimatedTime/1000, (float)estimatedTime/1000);

    }
}
