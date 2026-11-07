// BFS
class Solution {
  public int countIslands(char[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }

    int rows = grid.length;
    int cols = grid[0].length;
    int count = 0;

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (grid[i][j] == 'L') {
          count++;
          bfs(grid, i, j, rows, cols);
        }
      }
    }

    return count;
  }

  private void bfs(char[][] grid, int startRow, int startCol, int rows, int cols) {
    Queue<int[]> queue = new ArrayDeque<>();
    queue.offer(new int[]{startRow, startCol});
    grid[startRow][startCol] = 'W';

    // Explore all eight adjacent directions, including diagonals.
    int[][] directions = {
      {-1, 0}, {1, 0}, {0, -1}, {0, 1},
      {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
    };

    while (!queue.isEmpty()) {
      int[] cell = queue.poll();
      int row = cell[0];
      int col = cell[1];

      for (int[] dir : directions) {
        int nextRow = row + dir[0];
        int nextCol = col + dir[1];

        if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols
              && grid[nextRow][nextCol] == 'L') {
          queue.offer(new int[]{nextRow, nextCol});
          grid[nextRow][nextCol] = 'W';
        }
      }
    }
  }
}

// DFS
/*
class Solution {
  public int countIslands(char[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }

    int rows = grid.length;
    int cols = grid[0].length;
    int count = 0;

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (grid[i][j] == 'L') {
          count++;
          dfs(grid, i, j, rows, cols);
        }
      }
    }

    return count;
  }

  private void dfs(char[][] grid, int row, int col, int rows, int cols) {
    if (row < 0 || row >= rows || col < 0 || col >= cols
        || grid[row][col] == 'W') {
      return;
    }

    // Mark the land cell as visited.
    grid[row][col] = 'W';

    // Explore all eight adjacent directions, including diagonals.
    int[][] directions = {
      {-1, 0}, {1, 0}, {0, -1}, {0, 1},
      {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
    };

    for (int[] dir : directions) {
      dfs(grid, row + dir[0], col + dir[1], rows, cols);
    }
  }
}
*/

// Time: O(M * N)
// Space: O(M * N)