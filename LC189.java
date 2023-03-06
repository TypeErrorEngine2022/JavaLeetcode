import java.util.Arrays;

public class LC189{
    public static void rotate(int[] a, int k) {
        int size = a.length;
        if (k % a.length == 0)
            return;
        k = k % size;
        int pivot = size - k;
        int l = 0;
        int r = pivot;
        int[] res = new int[size];
        int p1 = 0; // res pointer
        int p2 = k;
        while (l < pivot || r < size) {
            if (r < size) res[p1++] = a[r++];
            if (l < pivot) res[p2++] = a[l++];
        }
        System.arraycopy(res, 0, a, 0, size);
    }

    public static void main(String[] args) {
        int[] a = {-1,-100,3,99};
        rotate(a, 2);
        System.out.println(Arrays.toString(a));
    }
}

