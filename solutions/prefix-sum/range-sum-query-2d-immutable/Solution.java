class NumMatrix {
  
  int[][] sum;

  public NumMatrix(int[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;

    // Extra row and column avoid boundary checks
    sum = new int[m + 1][n + 1];

    // Build 2D Prefix Sum
    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        sum[i][j] = matrix[i - 1][j - 1]
                  + sum[i - 1][j]
                  + sum[i][j - 1]
                  - sum[i - 1][j - 1];
      }
    }
  }

  public int sumRegion(int row1, int col1, int row2, int col2) {
    // BIG - TOP - LEFT + OVERLAP
    return sum[row2 + 1][col2 + 1]
         - sum[row1][col2 + 1]
         - sum[row2 + 1][col1]
         + sum[row1][col1];
  }

  // Constructor Time: O(m * n)
  // sumRegion Time: O(1)
  // Space: O(m * n)
}