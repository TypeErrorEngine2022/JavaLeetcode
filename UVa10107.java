import java.util.*;

public class UVa10107 {
    static int median;
    static ArrayList<Integer> nums;

    public static void main(String[] args) {
        nums = new ArrayList<Integer>();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextInt()) {
            nums.add(sc.nextInt());
            Collections.sort(nums);
            int len = nums.size();
            int mid = (len + 1) / 2 - 1;
            if (len % 2 != 0) {
                System.out.println(nums.get(mid));
            }
            else {
                System.out.println((nums.get(mid) + nums.get(mid + 1)) / 2);
            }
        }
    }
}
