public class LC344 {
    public static void reverseString(char[] s) {
        int left = 0;
        int right =s.length - 1;

        while (left < right) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        char[] s2 = {'1', '2', '3'};
        char[] s3 = {'1', '2', '3', '4'};
        reverseString(s2);
        System.out.println(s2);
        reverseString(s3);
        System.out.println(s3);
    }
}
