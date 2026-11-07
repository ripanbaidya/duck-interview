class Solution {

  public int maxSubArray(int[] nums) {
    int curr = nums[0], max = nums[0];
    for (int i = 1; i < nums.length; i++) {
      // A negative running sum only hurts, so restart at nums[i]
      curr = Math.max(nums[i], curr + nums[i]);
      max = Math.max(max, curr);
    }
    return max;
  }

  // Time: O(n)
  // Space: O(1)
}