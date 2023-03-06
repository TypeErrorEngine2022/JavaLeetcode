import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class LC3_LongestSubstringWithoutRepeatingChar {
    public static int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1) return s.length();

        int cur = 0;
        int max = 0;
        int start = 0;
        BitSet bitSet = new BitSet(128);
        for (int i = 0; i < s.length(); i++) {
            if (bitSet.get(s.charAt(i))) { //seen
                if (s.charAt(i) != s.charAt(start)) { // cannot keep slide the window
                    max = Math.max(cur, max);
                    bitSet.clear();
                    cur = 0;
                    i = start; // i will be start + 1 in next loop due to increment
                }
                start++;
            }
            else { //not seen
                cur++;
                bitSet.set(s.charAt(i));
            }
        }
        max = Math.max(cur, max);
        return max;
    }

    public static int slidingWindow(String s) {
        Set<Character> set = new HashSet<Character>();
        int max = 0;
        int start = 0;

        for (int i = 0; i < s.length(); i++) {
            while (set.contains(s.charAt(i))) {
                set.remove(s.charAt(start));
                start++;
            }
            set.add(s.charAt(i));
            max = Math.max(i - start + 1, max);
        }
        return max;
    }

    public static int slide(String s) {
        HashSet<Character> hashSet = new HashSet<>();
        int start = 0;
        int max = 0;
        int cur = 0;

        for (int i = 0; i < s.length(); i++) {
            while (hashSet.contains(s.charAt(i))) {
                hashSet.remove(s.charAt(start));
                start++;
                cur--;
            }
            hashSet.add(s.charAt(i));
            cur++;
            max = Math.max(max, cur);
        }

        return max;
    }

    public static int slideOptimized(String s) {
        // (char, last occurrence index + 1, which maybe the new start)
        HashMap<Character, Integer> hashMap = new HashMap<>();
        int res = 0;
        int start = 0;

        for (int end = 0; end < s.length(); end++) {
            if (hashMap.containsKey(s.charAt(end))) {
                start = Math.max(hashMap.get(s.charAt(end)), start);
            }
            res = Math.max(res, end - start + 1);
            hashMap.put(s.charAt(end), end + 1);
        }

        return res;
    }

    public static void main(String[] args) {
        String s = "abceacf";
        String s1 = "abcabcbb";
        String s2 = "pwwkew";
        String s3 = "tmmzuxt";
        String s4 = "ohvhjdml";
        System.out.println(slide(s4));
    }
}
