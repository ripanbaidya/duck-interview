class Solution {

  public int change(int amount, int[] coins) {
    int[] dp = new int[amount + 1];
    dp[0] = 1;

    // Coins in the outer loop count combinations, not orderings
    for (int coin : coins) {
      for (int j = coin; j <= amount; j++) {
        dp[j] += dp[j - coin];
      }
    }
    return dp[amount];
  }

  // Time: O(amount * coins)
  // Space: O(amount)
}