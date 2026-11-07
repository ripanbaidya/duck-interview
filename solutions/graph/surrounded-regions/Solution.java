// DFS
class Solution {
  public void solve(char[][] board) {
    int rows = board.length;
    int cols = board[0].length;

    // Track cells connected to the boundary.
    boolean[][] safe = new boolean[rows][cols];

    // Check top and bottom boundaries.
    for (int j = 0; j < cols; j++) {
      if (board[0][j] == 'O') {
        dfs(0, j, board, safe);
      }

      if (board[rows - 1][j] == 'O') {
        dfs(rows - 1, j, board, safe);
      }
    }

    // Check left and right boundaries.
    for (int i = 0; i < rows; i++) {
      if (board[i][0] == 'O') {
        dfs(i, 0, board, safe);
      }

      if (board[i][cols - 1] == 'O') {
        dfs(i, cols - 1, board, safe);
      }
    }

    // Capture surrounded regions and preserve safe cells.
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (!safe[i][j]) {
          board[i][j] = 'X';
        }
      }
    }
  }

  private void dfs(int row, int col, char[][] board, boolean[][] safe) {
    if (row < 0 || row >= board.length || col < 0 || col >= board[0].length
        || board[row][col] == 'X' || safe[row][col]) {
      return;
    }

    safe[row][col] = true;

    // Explore all four adjacent directions.
    dfs(row - 1, col, board, safe);
    dfs(row + 1, col, board, safe);
    dfs(row, col - 1, board, safe);
    dfs(row, col + 1, board, safe);
  }
}

// BFS
/*
class Solution {
  public void solve(char[][] board) {
    int rows = board.length;
    int cols = board[0].length;

    boolean[][] safe = new boolean[rows][cols];

    // Check top and bottom boundaries.
    for (int j = 0; j < cols; j++) {
      if (board[0][j] == 'O') {
        bfs(0, j, board, safe);
      }

      if (board[rows - 1][j] == 'O') {
        bfs(rows - 1, j, board, safe);
      }
    }

    // Check left and right boundaries.
    for (int i = 0; i < rows; i++) {
      if (board[i][0] == 'O') {
        bfs(i, 0, board, safe);
      }

      if (board[i][cols - 1] == 'O') {
        bfs(i, cols - 1, board, safe);
      }
    }

    // Capture surrounded regions and preserve safe cells.
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (!safe[i][j]) {
          board[i][j] = 'X';
        }
      }
    }
  }

  private void bfs(int startRow, int startCol, char[][] board, boolean[][] safe) {
    Queue<int[]> queue = new ArrayDeque<>();
    queue.offer(new int[]{startRow, startCol});
    safe[startRow][startCol] = true;

    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    while (!queue.isEmpty()) {
      int[] cell = queue.poll();
      int row = cell[0];
      int col = cell[1];

      // Explore all four adjacent directions.
      for (int[] dir : directions) {
        int nextRow = row + dir[0];
        int nextCol = col + dir[1];

        if (nextRow >= 0 && nextRow < board.length
            && nextCol >= 0 && nextCol < board[0].length
            && board[nextRow][nextCol] == 'O'
            && !safe[nextRow][nextCol]) {
          safe[nextRow][nextCol] = true;
          queue.offer(new int[]{nextRow, nextCol});
        }
      }
    }
  }
}
*/

// Time: O(M * N)
// Space: O(M * N)