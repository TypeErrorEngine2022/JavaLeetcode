import java.lang.reflect.Array;
import java.util.*;

public class LC56_MergeIntervals {
    public int[][] merge(int[][] intervals) {
        List<int[]> list = new ArrayList<>();
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        int[] newInterval = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (conflictIntervals(newInterval, intervals[i])) {
                newInterval = mergeInterval(newInterval, intervals[i]);
            }
            else {
                int[] temp = new int[newInterval.length];
                System.arraycopy(newInterval, 0, temp, 0, newInterval.length);
                list.add(temp);
                newInterval = intervals[i];
            }
        }
        list.add(newInterval);
        return list.toArray(new int[0][]);
    }

    private int[] mergeInterval(int[] firstInterval,
                                int[] secondInterval) {
        int[] res = new int[2];
        res[0] = Math.min(firstInterval[0], secondInterval[0]);
        res[1] = Math.max(firstInterval[1], secondInterval[1]);
        return res;
    }

    private boolean conflictIntervals (int[] firstInterval,
                                       int[] secondInterval) {
        if (containSecondInterval(firstInterval, secondInterval))
            return true;

        if (firstInterval[0] <= secondInterval[0] &&
                secondInterval[0] <= firstInterval[1])
            return true;

        if (secondInterval[0] <= firstInterval[0] &&
                firstInterval[0] <= secondInterval[1])
            return true;

        return false;
    }

    private boolean containSecondInterval(int[] firstInterval,
                                          int[] secondInterval) {
        return compStart(firstInterval, secondInterval) &&
                !compEnd(firstInterval, secondInterval);
    }

    private boolean compStart(int[] firstInterval, int[] secondInterval) {
        return firstInterval[0] <= secondInterval[0];
    }

    /**
     * Compare the end time of intervals
     * @param firstInterval
     * @param secondInterval
     * @return true if first interval end earlier than the second one
     */
    private boolean compEnd(int[] firstInterval, int[] secondInterval) {
        return firstInterval[1] <= secondInterval[1];
    }

    public static void main(String[] args) {
        int[][] arr1 = {{1,3},{2,6},{8,10},{15,18}};
        int[][] arr2 = {{1,4}, {0,4}};
        LC56_MergeIntervals lc = new LC56_MergeIntervals();
        int[][] res = lc.merge(arr2);
    }
}
