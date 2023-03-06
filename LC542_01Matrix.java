import javax.swing.plaf.PanelUI;
import java.util.Arrays;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.Queue;

public class LC542_01Matrix {
    public static int[][] updateMatrix(int[][] mat) {
        int[][] topLeft = {{-1,0}, {0, -1}};
        int[][] bottomRight = {{1,0}, {0,1}};
        int[][] res = new int[mat.length][mat[0].length];
        for (int[] arr : res)
            Arrays.fill(arr, -1);
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 0) {
                    res[i][j] = 0;
                } else if (mat[i][j] == 1) {
                    if (res[i][j] != -1) continue;
                    BitSet bitSet = new BitSet(mat.length * mat[0].length + 1);
                    res[i][j] = dp(mat, i, j, topLeft, res, bitSet);
                    bitSet.clear();
                    res[i][j] = dp(mat, i, j, bottomRight, res, bitSet);
                }
            }
        }
        return res;
    }

    public static int dp(int[][] mat, int row, int col,
                         int[][] dir, int[][] res, BitSet bitSet) {
        if (row < 0 || row >= mat.length || col < 0 || col >= mat[0].length || bitSet.get(row * mat[0].length + col))
            return Integer.MAX_VALUE;

        if (mat[row][col] == 0)
            return 0;

        if (res[row][col] != -1)
            return res[row][col];

        bitSet.set(row * mat[0].length + col);
        int tmp;
        int min = Integer.MAX_VALUE;
        for (int[] d : dir) {
            tmp = dp(mat, row + d[0], col + d[1],
                    dir, res, bitSet);
            min = Math.min(tmp, min);
        }

        if (row == 9 && col == 1)
            System.out.println("hi");

        return res[row][col] = 1 + min;
    }

    public static int[][] dpUpdateMatrix(int[][] mat) {
        int inf = mat.length * mat[0].length;
        int[][] res = new int[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                // only intervention from mat to res
                if (mat[i][j] == 0) continue;

                int left = inf;
                int top = inf;
                if (j > 0) left = res[i][j - 1];
                if (i > 0) top = res[i - 1][j];
                res[i][j] = Math.min(left, top) + 1;
            }
        }

        for (int i = mat.length - 1; i >= 0; i--) {
            for (int j = mat[0].length - 1; j >= 0; j--) {
                if (res[i][j] == 0) continue;
                int right = inf;
                int bottom = inf;
                if (j < mat[0].length - 1) right = res[i][j + 1];
                if (i < mat.length - 1) bottom = res[i + 1][j];

                //compare with ori value calculated from (topLeft)
                // => compare 4 directions(with known value)
                res[i][j] = Math.min(res[i][j], Math.min(right, bottom) + 1);
            }
        }

        return res;
    }

    public static int[][] bfsUpdateMatrix(int[][] mat) {
        int inf = mat.length * mat[0].length;
        int[][] res = new int[mat.length][mat[0].length];
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                // only intervention from mat to res
                if (mat[i][j] == 0)
                    queue.offer(new int[]{i, j});
                else
                    res[i][j] = inf;
            }
        }

        int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int i = cell[0];
            int j = cell[1];
            for (int[] d: dir) {
                int nr = i + d[0];
                int nc = j + d[1];

                if (nr < 0 || nr > mat.length - 1 || nc < 0 || nc > mat[0].length - 1 || res[nr][nc] != inf)
                    continue;

                res[nr][nc] = res[i][j] + 1;
                queue.offer(new int[]{nr, nc});
            }
        }
        return res;
    }

    public static int dfs(int[][] mat, int row, int col, int count,
                          int[][] dir, BitSet bitSet) {
        if (row < 0 || row >= mat.length || col < 0 || col >= mat[0].length ||
                bitSet.get(row * mat[0].length + col))
            return Integer.MAX_VALUE;

        if (mat[row][col] == 0)
            return count + 1;

        count++;
        bitSet.set(row * mat[0].length + col);
        int min = Integer.MAX_VALUE;
        int tmp;
        for (int[] d : dir) {
            tmp = dfs(mat, row + d[0], col + d[1],
                    0, dir, bitSet);
            min = Math.min(count + tmp, min);
            if (count == 1) break;
        }
        return min;
    }


    public int[][] updateMatrixSecond(int[][] mat) {
        int[][] res = new int[mat.length][mat[0].length];

        int width = mat[0].length;
        int height = mat.length;

        for (int[] row: res)
            Arrays.fill(row,Integer.MAX_VALUE - 100000);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (mat[i][j] == 0) {
                    res[i][j] = 0;
                    continue;
                }
                if (i > 0)
                    res[i][j] = Math.min(res[i][j], res[i - 1][j] + 1);
                if (j > 0)
                    res[i][j] = Math.min(res[i][j], res[i][j - 1] + 1);
            }
        }

        for (int i = height - 1; i >= 0; i--) {
            for (int j = width - 1; j >= 0; j--) {
                if (i + 1 < height)
                    res[i][j] = Math.min(res[i][j], res[i + 1][j] + 1);
                if (j + 1 < width)
                    res[i][j] = Math.min(res[i][j], res[i][j + 1] + 1);
            }
        }

        return res;
    }



    public static void main(String[] args) {
        int[][] mat1 = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        int[][] mat2 = {{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};

        int[][] mat3 = {{1, 0, 1, 1, 0, 0, 1, 0, 0, 1},
                        {0, 1, 1, 0, 1, 0, 1, 0, 1, 1},
                        {0, 0, 1, 0, 1, 0, 0, 1, 0, 0},
                        {1, 0, 1, 0, 1, 1, 1, 1, 1, 1},
                        {0, 1, 0, 1, 1, 0, 0, 0, 0, 1},
                        {0, 0, 1, 0, 1, 1, 1, 0, 1, 0},
                        {0, 1, 0, 1, 0, 1, 0, 0, 1, 1},
                        {1, 0, 0, 0, 1, 1, 1, 1, 0, 1},
                        {1, 1, 1, 1, 1, 1, 1, 0, 1, 0},
                        {1, 1, 1, 1, 0, 1, 0, 0, 1, 1}};

        int[][] ans = {{1, 0, 1, 1, 0, 0, 1, 0, 0, 1},
                       {0, 1, 1, 0, 1, 0, 1, 0, 1, 1},
                       {0, 0, 1, 0, 1, 0, 0, 1, 0, 0},
                       {1, 0, 1, 0, 1, 1, 1, 1, 1, 1},
                       {0, 1, 0, 1, 1, 0, 0, 0, 0, 1},
                       {0, 0, 1, 0, 1, 1, 1, 0, 1, 0},
                       {0, 1, 0, 1, 0, 1, 0, 0, 1, 1},
                       {1, 0, 0, 0, 1, 2, 1, 1, 0, 1},
                       {2, 1, 1, 1, 1, 2, 1, 0, 1, 0},
                       {3, 2, 2, 1, 0, 1, 0, 0, 1, 1}};

        LC542_01Matrix lc = new LC542_01Matrix();
        int[][] res = lc.updateMatrixSecond(mat2);
        for (int[] arr : res)
            System.out.println(Arrays.toString(arr));
    }
}
