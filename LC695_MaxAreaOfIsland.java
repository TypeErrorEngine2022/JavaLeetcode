public class LC695_MaxAreaOfIsland {
    public static int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 0)
                    continue;

                max = Math.max(dfs(grid, row, col, 0, dir), max);
            }
        }
        return max;
    }

    public static int dfs(int[][] grid, int row, int col, int count, int[][] dir) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || grid[row][col] != 1) {
            return count;
        }

        grid[row][col] = 0;
        count++;

        for (int[] d: dir)
            count = dfs(grid, row + d[0], col + d[1], count, dir);

        return count;
    }

    public static void main(String[] args) {

    }
}
