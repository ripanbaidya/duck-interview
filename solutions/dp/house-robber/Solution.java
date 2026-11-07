class Solution {

  public int rob(int[] nums) {
    int prev = 0, curr = 0;
    for (int num : nums) {
      // Either skip this house, or rob it and add the best from two houses back
      int next = Math.max(curr, prev + num);
      prev = curr;
      curr = next;
    }
    return curr;
  }

  // Time: O(n)
  // Space: O(1)
}