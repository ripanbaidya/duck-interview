class Solution {

  public int numDistinct(String s, String t) {
    int n = t.length();

    // dp[j] = ways to form the first j chars of t from the part of s seen so far
    int[] dp = new int[n + 1];
    dp[0] = 1;

    for (char ch : s.toCharArray()) {
      // Go backward so dp[j - 1] still holds the value from before this char
      for (int j = n; j >= 1; j--) {
        if (ch == t.charAt(j - 1)) dp[j] += dp[j - 1];
      }
    }
    return dp[n];
  }

  // Time: O(m * n)
  // Space: O(n)
}