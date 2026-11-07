class Solution {
  
  public double findMaxAverage(int[] nums, int k) {
    int n = nums.length;
    double sum = 0;

    for (int i = 0; i < k; i++) {
      sum += nums[i];
    }

    double maxAvg = sum / k;

    // Slide the window across the array
    for (int i = k; i < n; i++) {
      sum += nums[i] - nums[i - k];
      maxAvg = Math.max(maxAvg, sum / k);
    }

    return maxAvg;
  }

  // Time: O(n)
  // Space: O(1)
}