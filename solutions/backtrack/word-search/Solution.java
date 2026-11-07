class Solution {
  private static final int[][] DIRECTIONS = {
    {-1, 0}, {1, 0}, {0, -1}, {0, 1}
  };

  public boolean exist(char[][] board, String word) {
    int rows = board.length;
    int cols = board[0].length;

    boolean[][] visited = new boolean[rows][cols];

    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        if (board[row][col] == word.charAt(0)) {
          if (dfs(board, visited, word, row, col, 0)) {
            return true;
          }
        }
      }
    }

    return false;
  }

  private boolean dfs(char[][] board, boolean[][] visited, String word,
      int row, int col, int index) {

    if (index == word.length()) {
      return true;
    }

    // Check boundaries, visited cells, and character match.
    if (row < 0 || row >= board.length || col < 0 || col >= board[0].length
        || visited[row][col] || board[row][col] != word.charAt(index)) {
      return false;
    }

    visited[row][col] = true;

    // Explore all four adjacent directions.
    for (int[] dir : DIRECTIONS) {
      int nextRow = row + dir[0];
      int nextCol = col + dir[1];

      if (dfs(board, visited, word, nextRow, nextCol, index + 1)) {
        return true;
      }
    }

    // Backtrack to explore other possible paths.
    visited[row][col] = false;

    return false;
  }
}

// Time: O(M * N * 4^L)
// Space: O(M * N + L)