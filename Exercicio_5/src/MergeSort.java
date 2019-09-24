// merge sort from: https://www.baeldung.com/java-merge-sort
public class MergeSort {

    public void sort(int[] a, int n) {
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
        Thread half_a =  new Thread(() -> { sort(l, mid);});
        Thread half_b =  new Thread(() -> { sort(r, n - mid);});

        half_a.start();
        half_b.start();

        try {
            half_a.join();
            half_b.join();
        }
        catch (InterruptedException e){
            e.printStackTrace();
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

    MergeSort(int[] values, int num_elements){
        sort(values,num_elements);
    }
}
