// DFS
class Solution {
  public int[][] floodFill(int[][] image, int sr, int sc, int color) {
    int initialColor = image[sr][sc];

    // Avoid unnecessary traversal if the color is already the same.
    if (initialColor != color) {
      dfs(image, sr, sc, initialColor, color);
    }

    return image;
  }

  private void dfs(int[][] image, int row, int col, int initialColor, int newColor) {
    // Stop if out of bounds or the pixel has a different color.
    if (row < 0 || row >= image.length || col < 0 || col >= image[0].length
        || image[row][col] != initialColor) {
      return;
    }

    // Change the color to mark the pixel as visited.
    image[row][col] = newColor;

    // Explore all four adjacent directions.
    dfs(image, row - 1, col, initialColor, newColor);
    dfs(image, row + 1, col, initialColor, newColor);
    dfs(image, row, col - 1, initialColor, newColor);
    dfs(image, row, col + 1, initialColor, newColor);
  }
}

// BFS
/*
class Solution {
  public int[][] floodFill(int[][] image, int sr, int sc, int color) {
    int initialColor = image[sr][sc];

    // Avoid unnecessary traversal if the color is already the same.
    if (initialColor == color) {
      return image;
    }

    int rows = image.length;
    int cols = image[0].length;

    Queue<int[]> queue = new ArrayDeque<>();
    queue.offer(new int[]{sr, sc});
    image[sr][sc] = color;

    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    while (!queue.isEmpty()) {
      int[] cell = queue.poll();
      int row = cell[0];
      int col = cell[1];

      // Explore all four adjacent directions.
      for (int[] dir : directions) {
        int nextRow = row + dir[0];
        int nextCol = col + dir[1];

        if (nextRow >= 0 && nextRow < rows
            && nextCol >= 0 && nextCol < cols
            && image[nextRow][nextCol] == initialColor) {
          image[nextRow][nextCol] = color;
          queue.offer(new int[]{nextRow, nextCol});
        }
      }
    }

    return image;
  }
}
*/

// Time: O(M * N)
// Space: O(M * N)