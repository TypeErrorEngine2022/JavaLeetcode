import java.text.StringCharacterIterator;

public class LC392_IsSubsequence {
    public static boolean isSubsequence(String target, String src) {
        if (target.length() == 0) return true;
        if (src.length() == 0) return false;
        if (src.length() < target.length()) return false;

        int srcSize = src.length();
        int targetSize = target.length();

        int targetPtr = 0;
        for (int srcPtr = 0; srcPtr < srcSize; srcPtr++) {
            if (targetPtr == targetSize)
                return true;

            if (src.charAt(srcPtr) == target.charAt(targetPtr))
                targetPtr++;
        }
        return targetPtr == target.length();
    }

    public static void main(String[] args) {
        String target = "b";
        String src = "abc";
        System.out.println(isSubsequence(target, src));
    }
}
