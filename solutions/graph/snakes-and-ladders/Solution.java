class Solution {
  
  public int snakesAndLadders(int[][] board) {
    int n = board.length;
    int dest = n * n;
    boolean[] visited = new boolean[dest + 1];
    Queue<Integer> queue = new ArrayDeque<>();

    queue.offer(1);
    visited[1] = true;
    int moves = 0;

    while (!queue.isEmpty()) {
      int size = queue.size();

      for (int i = 0; i < size; i++) {
        int curr = queue.poll();
        if (curr == dest) return moves;

        for (int dice = 1; dice <= 6; dice++) {
          int next = curr + dice;
          if (next > dest) break;

          int[] pos = getCoordinates(next, n);
          int r = pos[0];
          int c = pos[1];
          int finalDest = board[r][c] != -1 ? board[r][c] : next;

          if (!visited[finalDest]) {
            visited[finalDest] = true;
            queue.offer(finalDest);
          }
        }
      }
      moves++;
    }

    return -1;
  }

  /**
   * Converts a 1-indexed board square number to 0-indexed 2D grid coordinates (r, c)
   * accounting for the Boustrophedon (zigzag) layout of Snakes and Ladders.
   */
  private int[] getCoordinates(int square, int n) {
    // 0-based index of the square
    int idx = square - 1;

    // Row from the top (0 is top row, n - 1 is bottom row)
    int r = n - 1 - idx / n;
  
    // Column offset from the left edge
    int rem = idx % n;
  
    // In alternate rows, the numbering reverses direction (right-to-left vs left-to-right)
    // Distance from bottom determines if the row direction is reversed
    int c = (n - 1 - r) % 2 == 0 ? rem : n - 1 - rem;
  
    return new int[] {r, c};
  }

  // Time: O(n^2)
  // Space: O(n^2)
}