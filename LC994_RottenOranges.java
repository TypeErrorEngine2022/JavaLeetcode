import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class LC994_RottenOranges {
    public static int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int fresh = 0;
        // contains all rotten source
        Queue<int[]> queue = new LinkedList<>();
        boolean hasTwo = false;
        boolean hasOne = false;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                    hasTwo = true;
                }
                if (grid[i][j] == 1)
                    fresh++;
            }
        }

        if (fresh == 0) return 0;
        // no rotten -> impossible for all fresh orange to be rotten
        if (!hasTwo) return -1;

        int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
        int count = 0;
        while (!queue.isEmpty()) {
            // snapshot of queue size for this generation
            // queue.size will be different when offer elements
            int qSize = queue.size();
            // separate different generation of rotten source
            for (int q = 0; q < qSize; q++) {
                int[] point = queue.poll();
                if (point == null) continue;
                int i = point[0];
                int j = point[1];
                for (int[] d : dir) {
                    int nr = i + d[0];
                    int nc = j + d[1];

                    if (nr < 0 || nr >= m || nc < 0 || nc >= n || grid[nr][nc] != 1)
                        continue;

                    grid[nr][nc] = 2;
                    fresh--;
                    queue.offer(new int[]{nr, nc});
                }
            }
            count++; // extra increment when no new rotten oranges at last search
        }

        if (fresh > 0) return -1;

        return count - 1; //offset the extra increment
    }

    // for each cell, measure the closet distance to rotten orange
    // time = the longest time among cells
    public static int Rotting(int[][] grid) {
        int[][] mat = new int[grid.length][grid[0].length];
        int res = Integer.MIN_VALUE;
        final int INIT = Integer.MAX_VALUE - 100000;
        boolean hasOne = false;

        for (int[] row: mat)
            Arrays.fill(row,INIT);

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // empty cell acts as barrier, stop accumulation
                if (grid[i][j] == 0){
                    // change from big value to zero, mark as barrier to stop accumulation
                    mat[i][j] = 0;
                    continue;
                }

                // rotten orange
                if (grid[i][j] == 2) {
                    mat[i][j] = -1;
                    continue;
                }

                hasOne = true;

                if (j - 1 >= 0 && mat[i][j - 1] != 0) {
                    // base case: near to rotten orange
                    if (mat[i][j - 1] == -1)
                        mat[i][j] = 1;
                    else // accumulate
                        mat[i][j] = Math.min(mat[i][j], mat[i][j - 1] + 1);
                }

                if (i - 1 >= 0 && mat[i - 1][j] != 0) {
                    if (mat[i - 1][j] == -1)
                        mat[i][j] = 1;
                    else
                        mat[i][j] = Math.min(mat[i][j], mat[i - 1][j] + 1);
                }
            }
        }
        if (!hasOne) return 0;
        for (int[] arr: mat) {
            printRow(arr);
        }
        System.out.println("------------");


        for (int i = mat.length - 1; i >= 0; i--) {
            for (int j = mat[0].length - 1; j >= 0; j--) {
                if (i == 0){
                    int a = 1;
                }
                // barrier or rotten -> skip
                if (mat[i][j] == 0 || mat[i][j] == -1){
                    continue;
                }

                if (i + 1 < grid.length && mat[i + 1][j] != 0) {
                    // base case: near to rotten orange
                    if (mat[i + 1][j] == -1)
                        mat[i][j] = 1;
                    else // accumulate
                        mat[i][j] = Math.min(mat[i][j], mat[i + 1][j] + 1);
                }
                if (j + 1 < grid[0].length && mat[i][j + 1] != 0) {
                    // base case: near to rotten orange
                    if (mat[i][j + 1] == -1)
                        mat[i][j] = 1;
                    else // accumulate
                        mat[i][j] = Math.min(mat[i][j], mat[i][j + 1] + 1);
                }
                // if any INIT is left -> pruning
                res = Math.max(res, mat[i][j]);
            }
        }
        for (int[] arr: mat) {
            printRow(arr);
        }
        System.out.println("------------");

        int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 0 || mat[i][j] == -1) continue;
                for (int[] vec: dir){
                    int nr = i + vec[0];
                    int nc = j + vec[1];
                    if (nr < 0 || nr > mat.length - 1 || nc < 0 || nc > mat[0].length - 1) continue;
                    if (mat[nr][nc] == 0){
                        continue;
                    }
                    if (mat[nr][nc] == -1){
                        mat[i][j] = 1;
                        break;
                    }
                    mat[i][j] = Math.min(mat[i][j], mat[nr][nc] + 1);
                }
            }
        }
        for (int[] arr: mat) {
            printRow(arr);
        }
        System.out.println("------------");
        return res != INIT? res: -1;
    }

    private static void printRow(int[] row) {
        for (int i : row) {
            System.out.printf("%10d", i);
            System.out.print("\t");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] grid = {{2,1,1},
                        {1,1,0},
                        {0,1,1}};

        int[][] grid2 = {{2,1,1},
                         {0,1,1},
                         {1,0,1}};

        int[][] grid3 = {{0,2}};

        int[][] grid4 = {{1,2}};

        int[][] grid5 ={{2,0,1,1,1,1,1,1,1,1},
                        {1,0,1,0,0,0,0,0,0,1},
                        {1,0,1,0,1,1,1,1,0,1},
                        {1,0,1,0,1,0,0,1,0,1},
                        {1,0,1,0,1,0,0,1,0,1},
                        {1,0,1,0,1,1,0,1,0,1},
                        {1,0,1,0,0,0,0,1,0,1},
                        {1,0,1,1,1,1,1,1,0,1},
                        {1,0,0,0,0,0,0,0,0,1},
                        {1,1,1,1,1,1,1,1,1,1}};

        System.out.println(Rotting(grid5));
    }
}
