class Solution {

  public int uniquePaths(int m, int n) {
    // dp[j] holds the paths to the current row's column j
    int[] dp = new int[n];
    Arrays.fill(dp, 1);

    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        dp[j] += dp[j - 1]; // from above + from left
      }
    }
    return dp[n - 1];
  }

  // Time: O(m * n)
  // Space: O(n)
}