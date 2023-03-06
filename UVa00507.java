import java.util.Scanner;

public class UVa00507 {
    static int b, r, s[], i, j, k, l, ct = 1;

    public static int[] max1d(int arr[], int range, int start, int en) {
        int maxnow = Integer.MIN_VALUE, cur = 0;
        int maxs = start, maxe = en;
        int[] res = new int[3];

        for (k = 1; k < range; k++) {
            cur += arr[k];
            en++;
            if (cur > maxnow) {
                maxs = start;
                maxe = en;
                maxnow = cur;
            }
            else if (cur == maxnow) {
                if (en - start > maxe - maxs) {
                    maxs = start;
                    maxe = en;
                    maxnow = cur;
                }
                else if (en - start == maxe - maxs) {
                    if (start < maxs) {
                        maxs = start;
                        maxe = en;
                        maxnow = cur;
                    }
                }
            }

            if (cur < 0) {
                start = en;
                cur = 0;
            }
        }
        res[0] = maxnow;
        res[1] = maxs;
        res[2] = maxe;
        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        b = scanner.nextInt();

        while (b-- > 0) {
            s = new int[20020];
            r = scanner.nextInt();
            for (i = 1; i < r; i++) {
                s[i] = scanner.nextInt();
            }
            int[] maxnow = max1d(s, r, 1, 1);
            if (maxnow[0] > 0)
                System.out.printf("The nicest part of route " + ct + " is between stops " + maxnow[1] + " and " + maxnow[2]);
            else
                System.out.printf("Route " + ct + " has no nice parts");

            ct++;
            if (b > 0) System.out.println();
        }
    }
}
