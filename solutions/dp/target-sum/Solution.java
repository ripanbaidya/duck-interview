class Solution {

  public int findTargetSumWays(int[] nums, int target) {
    int total = 0;
    for (int num : nums) total += num;

    // P - N = target and P + N = total, so P = (total + target) / 2
    if (Math.abs(target) > total || (total + target) % 2 != 0) return 0;
    int need = (total + target) / 2;

    // Count subsets with sum = need
    int[] dp = new int[need + 1];
    dp[0] = 1;

    for (int num : nums) {
      for (int j = need; j >= num; j--) {
        dp[j] += dp[j - num];
      }
    }
    return dp[need];
  }

  // Time: O(n * need)
  // Space: O(need)
}