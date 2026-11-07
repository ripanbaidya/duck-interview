// DFS
class Solution {
  public int countSubIslands(int[][] grid1, int[][] grid2) {
    int rows = grid1.length;
    int cols = grid1[0].length;

    boolean[][] visited = new boolean[rows][cols];
    int count = 0;

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (grid2[i][j] == 1 && !visited[i][j]) {
          if (dfs(i, j, grid1, grid2, visited)) {
            count++;
          }
        }
      }
    }

    return count;
  }

  private boolean dfs(int row, int col, int[][] grid1, int[][] grid2, boolean[][] visited) {
    // Out of bounds, water, or already visited cells do not invalidate the island.
    if (row < 0 || row >= grid2.length || col < 0 || col >= grid2[0].length
        || grid2[row][col] == 0 || visited[row][col]) {
      return true;
    }

    visited[row][col] = true;

    // Every land cell in grid2 must also be land in grid1.
    boolean isSubIsland = grid1[row][col] == 1;

    // Explore all four adjacent directions.
    isSubIsland &= dfs(row - 1, col, grid1, grid2, visited);
    isSubIsland &= dfs(row + 1, col, grid1, grid2, visited);
    isSubIsland &= dfs(row, col - 1, grid1, grid2, visited);
    isSubIsland &= dfs(row, col + 1, grid1, grid2, visited);

    return isSubIsland;
  }
}

// BFS
/*
class Solution {
  public int countSubIslands(int[][] grid1, int[][] grid2) {
    int rows = grid1.length;
    int cols = grid1[0].length;

    boolean[][] visited = new boolean[rows][cols];
    int count = 0;

    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (grid2[i][j] == 1 && !visited[i][j]) {
          if (bfs(i, j, grid1, grid2, visited, directions)) {
            count++;
          }
        }
      }
    }

    return count;
  }

  private boolean bfs(int startRow, int startCol, int[][] grid1, int[][] grid2,
      boolean[][] visited, int[][] directions) {
    Queue<int[]> queue = new ArrayDeque<>();
    queue.offer(new int[]{startRow, startCol});
    visited[startRow][startCol] = true;

    boolean isSubIsland = true;

    while (!queue.isEmpty()) {
      int[] cell = queue.poll();
      int row = cell[0];
      int col = cell[1];

      // Every land cell in grid2 must also be land in grid1.
      if (grid1[row][col] == 0) {
        isSubIsland = false;
      }

      for (int[] dir : directions) {
        int nextRow = row + dir[0];
        int nextCol = col + dir[1];

        if (nextRow >= 0 && nextRow < grid2.length
            && nextCol >= 0 && nextCol < grid2[0].length
            && grid2[nextRow][nextCol] == 1
            && !visited[nextRow][nextCol]) {
          visited[nextRow][nextCol] = true;
          queue.offer(new int[]{nextRow, nextCol});
        }
      }
    }

    return isSubIsland;
  }
}
*/

// Time: O(M * N)
// Space: O(M * N)