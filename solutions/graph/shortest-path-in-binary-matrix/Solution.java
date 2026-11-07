class Solution {
  
  public int shortestPathBinaryMatrix(int[][] grid) {
    int n = grid.length;
    if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) return -1;

    Queue<int[]> queue = new ArrayDeque<>();
    queue.offer(new int[] {0, 0, 1});
    grid[0][0] = 1;

    int[][] directions = {
      {-1, 0}, {1, 0}, {0, -1}, {0, 1},
      {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
    };

    while (!queue.isEmpty()) {
      int[] curr = queue.poll();
      int row = curr[0];
      int col = curr[1];
      int dist = curr[2];

      if (row == n - 1 && col == n - 1) return dist;

      for (int[] dir : directions) {
        int nextRow = row + dir[0];
        int nextCol = col + dir[1];

        if (nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < n && grid[nextRow][nextCol] == 0) {
          queue.offer(new int[] {nextRow, nextCol, dist + 1});
          grid[nextRow][nextCol] = 1;
        }
      }
    }

    return -1;
  }
  // Time: O(n^2)
  // Space: O(n^2)
}