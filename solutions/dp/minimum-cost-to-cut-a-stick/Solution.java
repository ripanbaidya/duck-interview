class Solution {

  public int minCost(int n, int[] cuts) {
    // Add both ends so every piece is a range between two positions
    int m = cuts.length + 2;
    int[] pos = new int[m];
    for (int i = 0; i < cuts.length; i++) pos[i + 1] = cuts[i];
    pos[m - 1] = n;
    Arrays.sort(pos);

    // dp[i][j] = min cost to fully cut the piece from pos[i] to pos[j]
    int[][] dp = new int[m][m];

    for (int gap = 2; gap < m; gap++) {
      for (int i = 0; i + gap < m; i++) {
        int j = i + gap;
        dp[i][j] = Integer.MAX_VALUE;

        // Try each cut k as the first cut on this piece
        for (int k = i + 1; k < j; k++) {
          dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
        }
        dp[i][j] += pos[j] - pos[i]; // the first cut costs the piece length
      }
    }
    return dp[0][m - 1];
  }

  // Time: O(c^3)
  // Space: O(c^2)
}