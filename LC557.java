public class LC557 {
    public static String reverseWords(String s) {
        String[] sub = s.split(" ");
        for (int i = 0; i < sub.length; i++) {
            StringBuilder res = new StringBuilder();
            res.append(sub[i]);
            res.reverse();
            if (i != sub.length - 1) res.append(" ");
            sub[i] = res.toString();
        }
        StringBuilder sum = new StringBuilder();
        for (String str: sub) {
            sum.append(str);
        }
        return sum.toString();
    }

    //pure two pointer approach
    public static void reverseStringTwoPointer(String s) {

    }

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
        String s = "Let's take LeetCode contest";
        s = reverseWords(s);
        System.out.println(s);
    }
}
