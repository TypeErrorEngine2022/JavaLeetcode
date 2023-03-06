import java.util.HashMap;
import java.util.HashSet;

public class LC383_RansomNote {
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) return false;
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < ransomNote.length(); i++) {
            char chr = ransomNote.charAt(i);
            hashMap.put(chr, hashMap.getOrDefault(chr, 0) + 1);
        }
        for (int i = 0; i < magazine.length(); i++) {
            if (hashMap.containsKey(magazine.charAt(i))) {
                char chr = magazine.charAt(i);
                hashMap.put(chr, hashMap.getOrDefault(chr, 0) - 1);
            }
        }
        for (int freq: hashMap.values())
            if (freq > 0) return false;
        return true;
    }
}
