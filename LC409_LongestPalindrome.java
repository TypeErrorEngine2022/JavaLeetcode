public class LC409_LongestPalindrome {
    public int longestPalindrome(String s) {
        if (s.length() == 1) return 1;
        int[] lowerFreq = new int[26];
        int[] higherFreq = new int[26];
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLowerCase(s.charAt(i)))
                lowerFreq[s.charAt(i) - 'a']++;
            else
                higherFreq[s.charAt(i) - 'A']++;
        }
        boolean hasOne = false;
        for (int i = 0; i < lowerFreq.length; i++) {
            int frequency = lowerFreq[i];
            if (frequency % 2 == 1) hasOne = true;
            // round down to closest multiple of 2
            frequency -= frequency % 2;
            res += frequency;
            lowerFreq[i] -= frequency;
        }
        for (int i = 0; i < higherFreq.length; i++) {
            int frequency = higherFreq[i];
            if (frequency % 2 == 1) hasOne = true;
            // round down to closest multiple of 2
            frequency -= frequency % 2;
            res += frequency;
            higherFreq[i] -= frequency;
        }

        return hasOne? res + 1: res;
    }
}
