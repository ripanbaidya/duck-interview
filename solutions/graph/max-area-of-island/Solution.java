// DFS
class Solution {
  public int maxAreaOfIsland(int[][] grid) {
    int rows = grid.length;
    int cols = grid[0].length;
    int maxArea = 0;

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (grid[i][j] == 1) {
          int[] area = new int[1];
          dfs(grid, i, j, rows, cols, area);
          maxArea = Math.max(maxArea, area[0]);
        }
      }
    }

    return maxArea;
  }

  private void dfs(int[][] grid, int row, int col, int rows, int cols, int[] area) {
    if (row < 0 || row >= rows || col < 0 || col >= cols || grid[row][col] == 0) {
      return;
    }

    // Mark the cell as visited and increase the island area.
    grid[row][col] = 0;
    area[0]++;

    // Explore all four adjacent directions.
    dfs(grid, row - 1, col, rows, cols, area);
    dfs(grid, row + 1, col, rows, cols, area);
    dfs(grid, row, col - 1, rows, cols, area);
    dfs(grid, row, col + 1, rows, cols, area);
  }
}

// BFS
/*
class Solution {
  public int maxAreaOfIsland(int[][] grid) {
    int rows = grid.length;
    int cols = grid[0].length;
    int maxArea = 0;

    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (grid[i][j] == 1) {
          maxArea = Math.max(maxArea, bfs(grid, i, j, rows, cols, directions));
        }
      }
    }

    return maxArea;
  }

  private int bfs(int[][] grid, int startRow, int startCol, int rows, int cols,
      int[][] directions) {

    Queue<int[]> queue = new ArrayDeque<>();
    queue.offer(new int[]{startRow, startCol});
    grid[startRow][startCol] = 0;

    int area = 0;

    while (!queue.isEmpty()) {
      int[] cell = queue.poll();
      int row = cell[0];
      int col = cell[1];
      area++;

      // Explore all four adjacent directions.
      for (int[] dir : directions) {
        int nextRow = row + dir[0];
        int nextCol = col + dir[1];

        if (nextRow >= 0 && nextRow < rows
            && nextCol >= 0 && nextCol < cols
            && grid[nextRow][nextCol] == 1) {
          queue.offer(new int[]{nextRow, nextCol});
          grid[nextRow][nextCol] = 0;
        }
      }
    }

    return area;
  }
}
*/

// Time: O(M * N)
// Space: O(M * N)