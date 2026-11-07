class Solution {
  private static final int INF = Integer.MAX_VALUE;

  private static final int[][] DIRECTIONS = {
    {-1, 0}, {1, 0}, {0, -1}, {0, 1}
  };

  public void wallsAndGates(int[][] rooms) {
    if (rooms == null || rooms.length == 0) {
      return;
    }

    int rows = rooms.length;
    int cols = rooms[0].length;
    Queue<int[]> queue = new ArrayDeque<>();

    // Add all gates as starting points.
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (rooms[i][j] == 0) {
          queue.offer(new int[]{i, j});
        }
      }
    }

    // Find the shortest distance from each empty room to a gate.
    while (!queue.isEmpty()) {
      int[] cell = queue.poll();
      int row = cell[0];
      int col = cell[1];

      for (int[] dir : DIRECTIONS) {
        int nextRow = row + dir[0];
        int nextCol = col + dir[1];

        if (nextRow >= 0 && nextRow < rows
            && nextCol >= 0 && nextCol < cols
            && rooms[nextRow][nextCol] == INF) {

          // Mark the room with its shortest distance.
          rooms[nextRow][nextCol] = rooms[row][col] + 1;
          queue.offer(new int[]{nextRow, nextCol});
        }
      }
    }
  }
}

// Time: O(M * N)
// Space: O(M * N)