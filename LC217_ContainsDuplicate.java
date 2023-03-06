import java.util.HashSet;

public class LC217_ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int num: nums) {
            if (hashSet.contains(num))
                return true;
            else
                hashSet.add(num);
        }
        return false;
    }
}
