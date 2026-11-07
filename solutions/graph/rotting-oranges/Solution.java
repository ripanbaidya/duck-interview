class Solution {
  public int orangesRotting(int[][] grid) {
    int rows = grid.length;
    int cols = grid[0].length;

    Queue<int[]> queue = new ArrayDeque<>();
    int fresh = 0;
    int rotted = 0;

    // Count fresh oranges and add all initially rotten oranges to the queue.
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (grid[i][j] == 1) {
          fresh++;
        } else if (grid[i][j] == 2) {
          queue.offer(new int[]{i, j, 0});
        }
      }
    }

    int minTime = 0;
    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    // Start BFS from all initially rotten oranges simultaneously.
    while (!queue.isEmpty()) {
      int[] cell = queue.poll();
      int row = cell[0];
      int col = cell[1];
      int time = cell[2];

      for (int[] dir : directions) {
        int nextRow = row + dir[0];
        int nextCol = col + dir[1];
        int nextTime = time + 1;

        if (nextRow >= 0 && nextRow < rows
            && nextCol >= 0 && nextCol < cols
            && grid[nextRow][nextCol] == 1) {
          queue.offer(new int[]{nextRow, nextCol, nextTime});
          grid[nextRow][nextCol] = 2;

          rotted++;
          minTime = Math.max(minTime, nextTime);
        }
      }
    }

    // Return -1 if some fresh oranges could not be reached.
    return fresh != rotted ? -1 : minTime;
  }
}

// Time: O(M * N)
// Space: O(M * N)