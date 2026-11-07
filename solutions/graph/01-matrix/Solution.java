class Solution {
  public int[][] updateMatrix(int[][] mat) {
    int rows = mat.length;
    int cols = mat[0].length;

    Queue<int[]> queue = new ArrayDeque<>();
    int[][] result = new int[rows][cols];

    // Add all zero cells as starting points.
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (mat[i][j] == 0) {
          queue.offer(new int[]{i, j, 0});
        }
      }
    }

    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    // Find the shortest distance from each cell to the nearest zero.
    while (!queue.isEmpty()) {
      int[] cell = queue.poll();
      int row = cell[0];
      int col = cell[1];
      int distance = cell[2];

      for (int[] dir : directions) {
        int nextRow = row + dir[0];
        int nextCol = col + dir[1];
        int nextDistance = distance + 1;

        if (nextRow >= 0 && nextRow < rows
            && nextCol >= 0 && nextCol < cols
            && mat[nextRow][nextCol] != 0) {
          queue.offer(new int[]{nextRow, nextCol, nextDistance});

          // Mark as visited to avoid processing the cell again.
          mat[nextRow][nextCol] = 0;
          result[nextRow][nextCol] = nextDistance;
        }
      }
    }

    return result;
  }
}

// Time: O(M * N)
// Space: O(M * N)