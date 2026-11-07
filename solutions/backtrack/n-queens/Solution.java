class Solution {

  private int n;
  private int[] queens; // queens[row] = column of the queen in that row
  private boolean[] cols, diag, anti;
  private List<List<String>> res = new ArrayList<>();

  public List<List<String>> solveNQueens(int n) {
    this.n = n;
    queens = new int[n];
    cols = new boolean[n];
    diag = new boolean[2 * n];
    anti = new boolean[2 * n];
    place(0);
    return res;
  }

  private void place(int row) {
    if (row == n) {
      res.add(build());
      return;
    }

    for (int col = 0; col < n; col++) {
      // Cells on the same diagonal share row - col; on the same anti-diagonal, row + col
      int d = row - col + n; // shift so the index is never negative
      int a = row + col;
      if (cols[col] || diag[d] || anti[a]) continue;

      cols[col] = diag[d] = anti[a] = true;
      queens[row] = col;
      place(row + 1);
      cols[col] = diag[d] = anti[a] = false;
    }
  }

  private List<String> build() {
    List<String> board = new ArrayList<>();
    for (int row = 0; row < n; row++) {
      char[] line = new char[n];
      Arrays.fill(line, '.');
      line[queens[row]] = 'Q';
      board.add(new String(line));
    }
    return board;
  }

  // Time: O(n!)
  // Space: O(n)
}