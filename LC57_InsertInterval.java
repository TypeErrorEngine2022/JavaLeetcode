import java.util.Arrays;
import java.util.Comparator;

public class LC57_InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals == null) return new int[][]{newInterval};
        if (intervals.length == 0) return new int[][]{newInterval};
        int l = 0;
        int r = intervals.length - 1;
        int mid;
        while (l <= r) {
            mid = l + (r - l) / 2;
            int[] midIntervals = intervals[mid];
            if (midIntervals[0] == newInterval[0]) {
                r = mid;
                //intervals[mid] = mergeInterval(midIntervals, newInterval);
                break;
            }
            else if (midIntervals[0] > newInterval[0]) {
                r = mid - 1;
            }
            else {
                l = mid + 1;
            }
        }

        int mergeCt = 0;
        int mergePos = 0;
        boolean merged = false;
        boolean beginning = r < 0;
        if (r < 0) r = 0;
        for (int i = r; i < intervals.length; i++) {
            if (conflictIntervals(intervals[i], newInterval)) {
                if (!merged) {
                    mergePos = i;
                    merged = true;
                }
                newInterval = mergeInterval(intervals[i], newInterval);
                mergeCt++;
            }
        }
        if (!merged){
            if (!beginning)
                mergePos = r + 1;
        }
        int[][] mergedRes = new int[intervals.length - mergeCt + 1][2];
        for (int i = 0; i < mergePos; i++) {
            mergedRes[i] = intervals[i];
        }
        mergedRes[mergePos] = newInterval;
        for (int i = mergePos + mergeCt; i < intervals.length; i++) {
            int resIndex = i - mergeCt + 1;
            mergedRes[resIndex] = intervals[i];
        }
        return mergedRes;
    }

    /**
     * Compare the starting time of intervals
     * @param firstInterval
     * @param secondInterval
     * @return true if first interval starts earlier than the second one
     */
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

    // start earlier, end later
    private boolean containSecondInterval(int[] firstInterval,
                                          int[] secondInterval) {
        return compStart(firstInterval, secondInterval) &&
                !compEnd(firstInterval, secondInterval);
    }

    /**
     * Compare the intervals
     * @param firstInterval
     * @param secondInterval
     * @return true if first interval is before the second one, without conflicting
     */
    /*private boolean compInterval(int[] firstInterval,
                                int[] secondInterval) {
        return compStart(firstInterval, secondInterval) &&
                compEnd(firstInterval, secondInterval);
    }*/

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

    private int[] mergeInterval(int[] firstInterval,
                                int[] secondInterval) {
        int[] res = new int[2];
        res[0] = Math.min(firstInterval[0], secondInterval[0]);
        res[1] = Math.max(firstInterval[1], secondInterval[1]);
        return res;
    }

    public static void main(String[] args) {
        LC57_InsertInterval obj = new LC57_InsertInterval();
        int[][] intervals = {{0,4}, {7, 12}};
        int[] newIntervals = {0,5};
        System.out.println(Arrays.deepToString(obj.insert(intervals, newIntervals)));
    }
}
