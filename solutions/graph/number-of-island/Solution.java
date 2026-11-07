// DFS
class Solution {
  public int numIslands(char[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }

    int rows = grid.length;
    int cols = grid[0].length;
    int count = 0;

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (grid[i][j] == '1') {
          dfs(grid, i, j, rows, cols);
          count++;
        }
      }
    }

    return count;
  }

  private void dfs(char[][] grid, int row, int col, int rows, int cols) {
    if (row < 0 || row >= rows || col < 0 || col >= cols || grid[row][col] == '0') {
      return;
    }

    // Mark the current land cell as visited.
    grid[row][col] = '0';

    // Explore all four adjacent directions.
    dfs(grid, row - 1, col, rows, cols);
    dfs(grid, row + 1, col, rows, cols);
    dfs(grid, row, col - 1, rows, cols);
    dfs(grid, row, col + 1, rows, cols);
  }
}

// BFS
/*
class Solution {
  public int numIslands(char[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }

    int rows = grid.length;
    int cols = grid[0].length;
    int count = 0;

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (grid[i][j] == '1') {
          bfs(grid, i, j, rows, cols);
          count++;
        }
      }
    }

    return count;
  }

  private void bfs(char[][] grid, int startRow, int startCol, int rows, int cols) {
    Queue<int[]> queue = new ArrayDeque<>();

    queue.offer(new int[]{startRow, startCol});
    grid[startRow][startCol] = '0';

    // Explore all four adjacent directions.
    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    while (!queue.isEmpty()) {
      int[] cell = queue.poll();
      int row = cell[0];
      int col = cell[1];

      for (int[] dir : directions) {
        int nextRow = row + dir[0];
        int nextCol = col + dir[1];

        if (isSafe(nextRow, nextCol, rows, cols, grid)) {
          queue.offer(new int[]{nextRow, nextCol});
          grid[nextRow][nextCol] = '0';
        }
      }
    }
  }

  private boolean isSafe(int row, int col, int rows, int cols, char[][] grid) {
    return row >= 0 && row < rows
        && col >= 0 && col < cols
        && grid[row][col] == '1';
  }
}
*/


// Time: O(M * N)
// Space: O(M * N)  