class Solution {

  public boolean canPartition(int[] nums) {
    int sum = 0;
    for (int num : nums) sum += num;
    if (sum % 2 != 0) return false;

    int target = sum / 2;
    boolean[] dp = new boolean[target + 1];
    dp[0] = true;

    for (int num : nums) {
      // Go backward so each number is used at most once
      for (int j = target; j >= num; j--) {
        dp[j] = dp[j] || dp[j - num];
      }
    }
    return dp[target];
  }

  // Time: O(n * sum)
  // Space: O(sum)
}