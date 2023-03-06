import java.util.*;

public class LC733_FloodFill {
    /*public static int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int colSize = image[0].length;
        int rowSize = image.length;
        int originalColor = image[sr][sc];
        int[][] res = new int[rowSize][colSize];
        for (int i = 0; i < rowSize; i++) {
            System.arraycopy(image[i], 0, res[i], 0, colSize);
        }
        BitSet visited = new BitSet(rowSize * colSize);
        Stack<Integer> stack = new Stack<>();
        stack.add(sr * colSize + sc);
        while (!stack.empty()) {
            int target = stack.pop();
            printString(res);

            if (visited.get(target))
                continue;

            visited.set(target);
            int targetX = target / colSize;
            int targetY = target % colSize;

            if (targetX < 0 || targetX >= rowSize || targetY < 0 || targetY >= colSize)
                System.out.println("hi");

            if (image[targetX][targetY] != originalColor) {
                res[targetX][targetY] = image[targetX][targetY];
                continue;
            }

            res[targetX][targetY] = color;

            if (targetX - 1 >= 0) // left
                stack.add((targetX - 1) * colSize + targetY);
            if (targetY - 1 >= 0) // up
                stack.add(targetX * colSize + (targetY - 1));
            if (targetX + 1 < rowSize) // down
                stack.add((targetX + 1) * colSize + targetY);
            if (targetY + 1 < colSize) // right
                stack.add(targetX * colSize + (targetY + 1));
        }
        return res;
    }*/

    public static int[][] secondFloodFill(int[][] image, int sr, int sc, int color) {
        if (image[sr][sc] == color) return image;

        int colSize = image[0].length;
        int rowSize = image.length;
        int originalColor = image[sr][sc];
        BitSet visited = new BitSet(rowSize * colSize);
        Stack<Integer> stack = new Stack<>();
        stack.add(sr * colSize + sc);
        while (!stack.empty()) {
            int target = stack.pop();
            printString(image);

            int targetX = target / colSize;
            int targetY = target % colSize;

            if (targetX < 0 || targetY < 0 || targetX >= image.length || targetY >= image[0].length ||
                    image[targetX][targetY] != originalColor || visited.get(target)) {
                continue;
            }

            /*if (targetX < 0 || targetX >= rowSize || targetY < 0 || targetY >= colSize)
                System.out.println("hi");*/

            image[targetX][targetY] = color;
            visited.set(target);

            stack.add((targetX - 1) * colSize + targetY);
            stack.add(targetX * colSize + (targetY - 1));
            stack.add((targetX + 1) * colSize + targetY);
            stack.add(targetX * colSize + (targetY + 1));
        }
        return image;
    }

    public static void dfs(int[][] image, int sr, int sc, int oriColor, int newColor) {
        // always true when oriColor == newColor, so prevent this in helper function
        if (sr < 0 || sc < 0 || sr >= image.length || sc >= image[0].length || image[sr][sc] != oriColor) {
            return;
        }

        image[sr][sc] = newColor;
        printString(image);

        dfs(image, sr, sc + 1, oriColor, newColor);
        dfs(image, sr, sc - 1, oriColor, newColor);
        dfs(image, sr + 1, sc, oriColor, newColor);
        dfs(image, sr - 1, sc, oriColor, newColor);
    }

    public static int[][] floodFill(int[][] image, int sr, int sc, int color) {
        // ensure oriColor != color => avoid infinite recursive calls in dfs
        if (image[sr][sc] == color) return image;
        dfs(image, sr, sc, image[sr][sc], color);
        return image;
    }

    public static void printString(int[][] image) {
        for (int[] arr: image) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println("------------------");
    }

    public static int[][] floodFillBFS(int[][] image, int sr, int sc, int color) {
        int width = image[0].length;
        int height = image.length;
        int oldColor = image[sr][sc];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(sr * width + sc);
        boolean[][] visited = new boolean[image.length][image[0].length];
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int row = cur / width;
            int col = cur % width;
            if (visited[row][col]) continue;

            visited[row][col] = true;
            if (image[row][col] == oldColor)
                image[row][col] = color;
            else continue;

            if (col < width - 1) //right
                queue.offer(row * width + (col + 1));
            if (col > 0) // left
                queue.offer(row * width + (col - 1));
            if (row > 0 && col < width) //up
                queue.offer((row - 1) * width + col);
            if (row < height - 1 && col < width) // down
                queue.offer((row + 1) * width + col);
        }

        return image;
    }

    public static void main(String[] args) {
        int[][] image = {{0,0,1},
                        {0,0,1}};
        int[][] res = floodFillBFS(image, 0, 0, 2);
        System.out.println("Result:");
        printString(res);
    }
}
