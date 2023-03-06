import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class LC200_NumberOfIslands {
    public int numIslands(char[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Stack<Integer> stack = new Stack<>();
        int m = grid.length;
        int n = grid[0].length;
        int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int res = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] || grid[i][j] == '0') continue;

                // has '1' and unvisited
                // start exploring
                res++;
                stack.push(i * n + j);
                while (!stack.isEmpty()) {
                    int cur = stack.pop();
                    int x = cur / n;
                    int y = cur % n;
                    if (grid[x][y] == '0') continue;

                    visited[x][y] = true;

                    for (int[] vec: dir) {
                        int nr = x + vec[0];
                        int nc = y + vec[1];
                        if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                        if (visited[nr][nc]) continue;
                        stack.push(nr * n + nc);
                    }
                }
            }
        }
        return res;
    }

    public int numIslandsRecursiveDFS(char[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    recursiveDFS(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    private void recursiveDFS(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length ||
            grid[i][j] != '1')
            return;

        grid[i][j] = '0';
        recursiveDFS(grid, i, j + 1);
        recursiveDFS(grid, i, j - 1);
        recursiveDFS(grid, i + 1, j);
        recursiveDFS(grid, i - 1, j);
    }

    public int numIslandsBFS(char[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Queue<Integer> queue = new LinkedList<>();
        int m = grid.length;
        int n = grid[0].length;
        int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int res = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] || grid[i][j] == '0') continue;

                // has '1' and unvisited
                // start exploring
                res++;
                queue.offer(i * n + j);
                while (!queue.isEmpty()) {
                    int cur = queue.poll();
                    int x = cur / n;
                    int y = cur % n;
                    if (grid[x][y] == '0') continue;

                    visited[x][y] = true;

                    for (int[] vec: dir) {
                        int nr = x + vec[0];
                        int nc = y + vec[1];
                        if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                        if (visited[nr][nc]) continue;
                        queue.offer(nr * n + nc);
                    }
                }
            }
        }
        return res;
    }
}
