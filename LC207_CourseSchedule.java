import javax.swing.*;
import java.util.*;

public class LC207_CourseSchedule {
    /*public static boolean canFinish(int numCourses, int[][] prerequisites) {
        // if 2 courses, [0, 0] -> true, [1, 0] -> false;
        // if 3 courses, [0, 1, 0] -> true, [2, 2, 1], false
        // each element contains its prerequisites
        List<BitSet> edge = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            edge.add(new BitSet());
        }

        for (int[] arr: prerequisites) {
            int target = arr[0];
            int pReq = arr[1];
            if (target == pReq) return false;
            BitSet targetAdjList = edge.get(target);
            targetAdjList.set(pReq);
        }

        for (int i = 0; i < numCourses; i++) {
            if (edge.get(i).size() == 0) continue;

            BitSet adjList = edge.get(i);
            Stack<Integer> stack = new Stack<>();
            stack.push(i);
            while (!stack.isEmpty()) {
                int cur = stack.pop();

            }
        }

        return true;
    }*/

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // adjacency list for course mapping to its prerequisites
        Map<Integer, List<Integer>> preMap = new HashMap<>();

        for (int i = 0; i < numCourses; i++) {
            preMap.put(i, new ArrayList<>());
        }

        for (int[] pre: prerequisites) {
            preMap.get(pre[0]).add(pre[1]);
        }

        Set<Integer> visited = new HashSet<>();

        // loop through all courses in case the graph has separate subgraphs
        for (int course = 0; course < numCourses; course++) {
            if (!dfs(course, preMap, visited))
                return false;
        }
        return true;
    }

    private boolean dfs(int course, Map<Integer, List<Integer>> preMap,
                     Set<Integer> visited) {
        List<Integer> list = preMap.get(course);

        // can be completed when no prerequisites
        if (list.isEmpty()) return true;
        // detect cycle in DFS path
        if (visited.contains(course)) return false;

        visited.add(course);
        for (int pre: list) {
            if (!dfs(pre, preMap, visited)) {
                return false; //pruning
            }
        }

        // just like backtracking
        // since other separate graphs may have to visit the same prerequisite
        visited.remove(course);

        // since all prerequisites can be finished
        // this course can be finished
        list.clear();
        return true;
    }


    public static void main(String[] args) {
        int[][] preq = {{1,0}, {0, 1}};
        int[][] preq2 = {{1, 0}};
        int[][] preq3 = {{0,10},{3,18},{5,5},{6,11},{11,14},{13,1},{15,1},{17,4}};
        int[][] preq4 = {{0,1},{0,2},{1,2}};
        LC207_CourseSchedule lc = new LC207_CourseSchedule();
        System.out.println(lc.canFinish(19, preq4));
    }
}
