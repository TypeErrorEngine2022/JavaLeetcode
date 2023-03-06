import java.util.*;

public class Dijkstra {
    public static int[][] dijkstra(int[][] graph, int src) {
        int[] cost = new int[graph.length];
        int[] preVertex = new int[graph.length];
        boolean[] visited = new boolean[graph.length];
        int visitedNo = 0;

        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[src] = 0;
        preVertex[src] = -1;
        Arrays.fill(visited, false);

        int minIndex = src;

        while (visitedNo < graph.length) {
            // we have already examined all edges incident to the minIndex
            // visit next vertex with the smallest known distance to src
            // do not visit this vertex again
            int curTarget = minIndex;
            visited[curTarget] = true;
            visitedNo++;

            // traverse through neighbour to find the neighbour with the shortest distance
            int minCost = Integer.MAX_VALUE;
            for (int i = 0; i < graph[curTarget].length; i++) {
                if (!visited[i] && graph[curTarget][i] != 0 &&
                    cost[curTarget] + graph[curTarget][i] < cost[i]) {

                    cost[i] = cost[curTarget] + graph[curTarget][i];
                    // curTarget is the shortest edge incident to i currently
                    preVertex[i] = curTarget;

                    // next vertex should be unvisited
                    if (cost[i] < minCost){
                        minCost = cost[i];
                        minIndex = i;
                    }
                }
            }
        }

        return new int[][]{cost, preVertex};
    }

    public static void printPath(int[] preVertex, int distIndex) {
        StringBuilder path = new StringBuilder();
        path.append(distIndex);
        int prev = preVertex[distIndex];
        while (prev != -1) {
            path.append(" > ");
            path.append(prev);
            prev = preVertex[prev];
        }

        path.reverse();
        System.out.println(path.toString());
    }

    public static void main(String[] args) {
        int[][] graph = {{0, 6, 0, 1, 0}, {6,0,5,2,2}, {0,5,0,0,5}, {1,2,0,0,1}, {0,2,5,1,0}};

        int[][] costAndPreVertex = dijkstra(graph, 0);;

        System.out.println(Arrays.toString(costAndPreVertex[0]));
        printPath(costAndPreVertex[1], 2);
    }
}
