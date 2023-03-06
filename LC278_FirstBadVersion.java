public class LC278_FirstBadVersion {
    public boolean isBadVersion(int n) {
        return true;
    }
    public int firstBadVersion(int n) {
        int l = 1;
        int r = n;
        int mid = 0;
        while (l <= r) {
            mid = l + (r - l) / 2;
            if (isBadVersion(mid)) {
                if (!isBadVersion(mid - 1))
                    break;
                r = mid - 1;
            }
            else {
                l = mid + 1;
            }
        }
        return mid;
    }
}
