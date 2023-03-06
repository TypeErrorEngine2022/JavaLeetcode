import java.util.*;

public class LC567 {
    // expensive in hashmap clear
    public static boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length())
            return false;

        boolean res = false;
        Map<Character, Integer> hashMap = new HashMap<>();
        Map<Character, Integer> subMap = new HashMap<>();
        int srcLen = s1.length();
        int subLen = 0;

        for (int i = 0; i < s1.length(); i++)
            hashMap.put(s1.charAt(i), hashMap.getOrDefault(s1.charAt(i), 0) + 1);

        int start = 0;

        // worst case O(s2.length / 2 * 26) => O(n)
        for (int i = 0; i < s2.length(); i++) {
            char ch = s2.charAt(i); // O(1)
            if (!hashMap.containsKey(ch)) { // O(1) search in hashmap
                start = i + 1;
                // O(26) in worst case: s1 contains 24 lowercase char , s2 contain 23 char + 1 unseen char in s1 at the end
                // => clear when encounter last word(unseen of s2) => subMap size = O(26)
                // happens when s1: abc, s2: s1+new s1+new s1+new ... , keep triggering subMap.clear()
                // another worse example: s1: a, s2: ab ab ab ab ab ab, call clear() for s2.length / 2 times
                subMap.clear();
                subLen = 0;
                continue;
            }

            // encounter duplicate character => delete the duplicated character at start
            // keep deleting start character until the window is valid: subMap is subset of hashMap,
            // valid window: no elements in subMap has higher frequency than that in hashMap
            while (Objects.equals(subMap.getOrDefault(ch, 0), hashMap.get(ch))) { // O(1)
                subMap.replace(s2.charAt(start), subMap.get(s2.charAt(start)) - 1);
                start++;
                subLen--;
            }
            subMap.put(ch, subMap.getOrDefault(ch, 0) + 1); // O(1) in insertion for hashmap
            subLen++;
            if (subLen == srcLen) { // O(1)
                res = true;
                break;
            }
        }
        return res;
    }



    public static boolean betterCheckInclusion(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if (len1 > len2) return false;

        int[] count = new int[26];
        for (int i = 0; i < len1; i++) {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }
        if (allZero(count)) return true;

        for (int i = len1; i < len2; i++) {
            count[s2.charAt(i) - 'a']--;
            // keep window size == s1.length
            count[s2.charAt(i - len1) - 'a']++;

            //same char frequencies
            if (allZero(count)) return true;
        }

        return false;
    }

    private static boolean allZero(int[] count) {
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) return false;
        }
        return true;
    }

    public static boolean simpleCheckInclusion(String s1, String s2) {

        int len1 = s1.length(), len2 = s2.length();
        if(len1 > len2) return false;

        int[] chars = new int[26];
        for(char ch : s1.toCharArray()) chars[ch - 'a']++;

        int i = 0;
        int count = 0; // number of s1 chars counted
        for(int j = 0; j < len2; j++) {
            // decrement each char we pass by, if it is a char we want, increment count
            if(chars[s2.charAt(j) - 'a'] > 0) {
                chars[s2.charAt(j) - 'a']--;
                count++;
            }

            // we found a permutation
            if(count == len1) return true;

            // check when we get a window equal to size of s1
            // correct window size && NOT return true at above
            // => this window(substring) doesn't contain permuatation
            if(j - i + 1 == len1) {
                // for cases where window has chars we need but also includes other chars
                // we put the starting chars back into the map and decrement count
                chars[s2.charAt(i) - 'a']++;

                // if the starting char is in s1
                if(chars[s2.charAt(i) - 'a'] >= 0) {
                    count--;
                }
                // slide the window to keep it in (len1) size
                i++;
            }

        }
        return false;
    }

    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "abdabc";
        System.out.println(simpleCheckInclusion(s1, s2));
    }
}

