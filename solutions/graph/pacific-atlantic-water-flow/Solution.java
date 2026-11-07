// DFS
class Solution {
  private final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

  public List<List<Integer>> pacificAtlantic(int[][] heights) {
    List<List<Integer>> result = new ArrayList<>();

    if (heights == null || heights.length == 0 || heights[0].length == 0) {
      return result;
    }

    int rows = heights.length;
    int cols = heights[0].length;

    boolean[][] pacific = new boolean[rows][cols];
    boolean[][] atlantic = new boolean[rows][cols];

    // Start DFS from the Pacific and Atlantic boundaries.
    for (int col = 0; col < cols; col++) {
      dfs(0, col, heights, pacific);
      dfs(rows - 1, col, heights, atlantic);
    }

    for (int row = 0; row < rows; row++) {
      dfs(row, 0, heights, pacific);
      dfs(row, cols - 1, heights, atlantic);
    }

    // Collect cells reachable from both oceans.
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (pacific[i][j] && atlantic[i][j]) {
          result.add(Arrays.asList(i, j));
        }
      }
    }

    return result;
  }

  private void dfs(int row, int col, int[][] heights, boolean[][] visited) {
    visited[row][col] = true;

    // Reverse the water flow by moving to equal or higher cells.
    for (int[] dir : directions) {
      int nextRow = row + dir[0];
      int nextCol = col + dir[1];

      if (nextRow >= 0 && nextRow < heights.length
          && nextCol >= 0 && nextCol < heights[0].length
          && !visited[nextRow][nextCol]
          && heights[nextRow][nextCol] >= heights[row][col]) {
        dfs(nextRow, nextCol, heights, visited);
      }
    }
  }
}

// BFS
/*
class Solution {
  private final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

  public List<List<Integer>> pacificAtlantic(int[][] heights) {
    List<List<Integer>> result = new ArrayList<>();

    if (heights == null || heights.length == 0 || heights[0].length == 0) {
      return result;
    }

    int rows = heights.length;
    int cols = heights[0].length;

    boolean[][] pacific = new boolean[rows][cols];
    boolean[][] atlantic = new boolean[rows][cols];

    // Start BFS from the Pacific and Atlantic boundaries.
    for (int col = 0; col < cols; col++) {
      bfs(0, col, heights, pacific);
      bfs(rows - 1, col, heights, atlantic);
    }

    for (int row = 0; row < rows; row++) {
      bfs(row, 0, heights, pacific);
      bfs(row, cols - 1, heights, atlantic);
    }

    // Collect cells reachable from both oceans.
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (pacific[i][j] && atlantic[i][j]) {
          result.add(Arrays.asList(i, j));
        }
      }
    }

    return result;
  }

  private void bfs(int startRow, int startCol, int[][] heights, boolean[][] visited) {
    Queue<int[]> queue = new ArrayDeque<>();
    queue.offer(new int[]{startRow, startCol});
    visited[startRow][startCol] = true;

    while (!queue.isEmpty()) {
      int[] cell = queue.poll();
      int row = cell[0];
      int col = cell[1];

      // Reverse the water flow by moving to equal or higher cells.
      for (int[] dir : directions) {
        int nextRow = row + dir[0];
        int nextCol = col + dir[1];

        if (nextRow >= 0 && nextRow < heights.length
            && nextCol >= 0 && nextCol < heights[0].length
            && !visited[nextRow][nextCol]
            && heights[nextRow][nextCol] >= heights[row][col]) {
          visited[nextRow][nextCol] = true;
          queue.offer(new int[]{nextRow, nextCol});
        }
      }
    }
  }
}
*/


// Time: O(M * N)
// Space: O(M * N)
