public class LC125_ValidPalindrome {
    public static boolean isPalindrome(String s) {
        if (s.length() <= 1) return true;
        int lpt = 0;
        int rpt = s.length() - 1;
        while (lpt <= rpt) {
            char l = Character.toLowerCase(s.charAt(lpt));
            if (!Character.isLetterOrDigit(l)) {
                lpt++;
                continue;
            }
            char r = Character.toLowerCase(s.charAt(rpt));
            if (!Character.isLetterOrDigit(r)) {
                rpt--;
                continue;
            }
            if (l != r) return false;
            lpt++;
            rpt--;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "0P";
        System.out.println(isPalindrome(s));
    }
}
