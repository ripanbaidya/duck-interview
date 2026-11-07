class Solution {
  public ArrayList<ArrayList<Integer>> nearest(int[][] grid) {
    int rows = grid.length;
    int cols = grid[0].length;

    Queue<int[]> queue = new ArrayDeque<>();
    int[][] result = new int[rows][cols];

    // Add all cells containing 1 as starting points.
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (grid[i][j] == 1) {
          queue.offer(new int[]{i, j, 0});
        }
      }
    }

    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    // Calculate the shortest distance from each cell to the nearest 1.
    while (!queue.isEmpty()) {
      int[] cell = queue.poll();
      int row = cell[0];
      int col = cell[1];
      int distance = cell[2];

      for (int[] dir : directions) {
        int nextRow = row + dir[0];
        int nextCol = col + dir[1];

        if (nextRow >= 0 && nextRow < rows
            && nextCol >= 0 && nextCol < cols
            && grid[nextRow][nextCol] == 0) {
          queue.offer(new int[]{nextRow, nextCol, distance + 1});

          // Mark as visited to avoid processing the cell again.
          grid[nextRow][nextCol] = 1;
          result[nextRow][nextCol] = distance + 1;
        }
      }
    }

    // Convert the result matrix to the required ArrayList format.
    ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

    for (int i = 0; i < rows; i++) {
      ArrayList<Integer> row = new ArrayList<>();

      for (int j = 0; j < cols; j++) {
        row.add(result[i][j]);
      }

      ans.add(row);
    }

    return ans;
  }
}

// Time: O(M * N)
// Space: O(M * N)