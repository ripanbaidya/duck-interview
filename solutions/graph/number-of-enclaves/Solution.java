// DFS
class Solution {
  public int numEnclaves(int[][] grid) {
    int rows = grid.length;
    int cols = grid[0].length;

    // Remove all land cells connected to the boundary.
    for (int col = 0; col < cols; col++) {
      dfs(0, col, grid);
      dfs(rows - 1, col, grid);
    }

    for (int row = 0; row < rows; row++) {
      dfs(row, 0, grid);
      dfs(row, cols - 1, grid);
    }

    // Count remaining land cells that cannot reach the boundary.
    int count = 0;

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (grid[i][j] == 1) {
          count++;
        }
      }
    }

    return count;
  }

  private void dfs(int row, int col, int[][] grid) {
    if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length
        || grid[row][col] == 0) {
      return;
    }

    // Mark the land cell as visited.
    grid[row][col] = 0;

    // Explore all four adjacent directions.
    dfs(row - 1, col, grid);
    dfs(row + 1, col, grid);
    dfs(row, col - 1, grid);
    dfs(row, col + 1, grid);
  }
}

// BFS
/*
class Solution {
  public int numEnclaves(int[][] grid) {
    int rows = grid.length;
    int cols = grid[0].length;

    // Remove all land cells connected to the boundary.
    for (int col = 0; col < cols; col++) {
      bfs(0, col, grid);
      bfs(rows - 1, col, grid);
    }

    for (int row = 0; row < rows; row++) {
      bfs(row, 0, grid);
      bfs(row, cols - 1, grid);
    }

    // Count remaining land cells that cannot reach the boundary.
    int count = 0;

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (grid[i][j] == 1) {
          count++;
        }
      }
    }

    return count;
  }

  private void bfs(int startRow, int startCol, int[][] grid) {
    if (grid[startRow][startCol] == 0) {
      return;
    }

    int rows = grid.length;
    int cols = grid[0].length;

    Queue<int[]> queue = new ArrayDeque<>();
    queue.offer(new int[]{startRow, startCol});
    grid[startRow][startCol] = 0;

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
            && grid[nextRow][nextCol] == 1) {
          queue.offer(new int[]{nextRow, nextCol});
          grid[nextRow][nextCol] = 0;
        }
      }
    }
  }
}
*/

// Time: O(M * N)
// Space: O(M * N)