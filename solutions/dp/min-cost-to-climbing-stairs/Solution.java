class Solution {

  public int minCostClimbingStairs(int[] cost) {
    int n = cost.length;

    // prev and curr are the min cost to stand on step i - 1 and i; 
    // steps 0 and 1 are free to start on
    int prev = 0, curr = 0;
    for (int i = 2; i <= n; i++) {
      int next = Math.min(curr + cost[i - 1], prev + cost[i - 2]);
      prev = curr;
      curr = next;
    }
    return curr;
  }

  // Time: O(n)
  // Space: O(1)
}