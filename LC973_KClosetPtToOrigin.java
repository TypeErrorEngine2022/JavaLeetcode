import java.util.PriorityQueue;

public class LC973_KClosetPtToOrigin {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((pt1, pt2) -> pt2[0] * pt2[0] + pt2[1] * pt2[1] - pt1[0] * pt1[0] - pt1[1] * pt1[1]);

        for (int[] arr: points) {
            priorityQueue.offer(arr);
            if (priorityQueue.size() > k)
                priorityQueue.poll();
        }

        int[][] res = new int[k][2];
        return priorityQueue.toArray(res);
    }
}
