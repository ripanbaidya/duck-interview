// DFS
class Solution {
  public int islandPerimeter(int[][] grid) {
    int rows = grid.length;
    int cols = grid[0].length;
    boolean[][] visited = new boolean[rows][cols];

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (grid[i][j] == 1) {
          return dfs(i, j, rows, cols, grid, visited);
        }
      }
    }

    return 0;
  }

  private int dfs(int row, int col, int rows, int cols, int[][] grid, boolean[][] visited) {
    // Water or boundary contributes one perimeter edge.
    if (row < 0 || row >= rows || col < 0 || col >= cols || grid[row][col] == 0) {
      return 1;
    }

    if (visited[row][col]) {
      return 0;
    }

    visited[row][col] = true;

    // Count exposed edges in all four directions.
    int perimeter = 0;
    perimeter += dfs(row - 1, col, rows, cols, grid, visited);
    perimeter += dfs(row + 1, col, rows, cols, grid, visited);
    perimeter += dfs(row, col - 1, rows, cols, grid, visited);
    perimeter += dfs(row, col + 1, rows, cols, grid, visited);

    return perimeter;
  }
}

// BFS
/*
class Solution {
  public int islandPerimeter(int[][] grid) {
    int rows = grid.length;
    int cols = grid[0].length;
    boolean[][] visited = new boolean[rows][cols];

    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (grid[i][j] == 1) {
          return bfs(i, j, rows, cols, grid, visited, directions);
        }
      }
    }

    return 0;
  }

  private int bfs(int startRow, int startCol, int rows, int cols, int[][] grid,
      boolean[][] visited, int[][] directions) {
    Queue<int[]> queue = new ArrayDeque<>();
    queue.offer(new int[]{startRow, startCol});
    visited[startRow][startCol] = true;

    int perimeter = 0;

    while (!queue.isEmpty()) {
      int[] cell = queue.poll();
      int row = cell[0];
      int col = cell[1];

      for (int[] dir : directions) {
        int nextRow = row + dir[0];
        int nextCol = col + dir[1];

        // Water or boundary contributes one perimeter edge.
        if (nextRow < 0 || nextRow >= rows || nextCol < 0 || nextCol >= cols
            || grid[nextRow][nextCol] == 0) {
          perimeter++;
        } else if (!visited[nextRow][nextCol]) {
          visited[nextRow][nextCol] = true;
          queue.offer(new int[]{nextRow, nextCol});
        }
      }
    }

    return perimeter;
  }
}
*/

// Time: O(M * N)
// Space: O(M * N)