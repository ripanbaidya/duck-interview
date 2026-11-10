class Solution {

  public int threeSumClosest(int[] nums, int target) {
    int n = nums.length;
    Arrays.sort(nums);

    long ans = (long) nums[0] + nums[1] + nums[2];

    for (int i = 0; i < n - 2; i++) {
      int j = i + 1;
      int k = n - 1;

      while (j < k) {
        long currSum = (long) nums[i] + nums[j] + nums[k];

        if (Math.abs(currSum - target) < Math.abs(ans - target)) {
          ans = currSum;
        }

        if (currSum == target) {
          return (int) currSum;
        } else if (currSum > target) {
          k--;
        } else {
          j++;
        }
      }
    }

    return (int) ans;
  }
}
// Time: O(n^2) The outer loop runs n times and the inner while loop runs at most 
// n times in total for each iteration of the outer loop, leading to a quadratic time complexity.
// Space: O(1) The algorithm uses a constant amount of extra space regardless of the input size.