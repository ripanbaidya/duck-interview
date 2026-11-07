class Solution {
  
  public int findMin(int[] nums) {
    int low = 0;
    int high = nums.length - 1;

    while (low < high) {
      int mid = low + (high - low) / 2;

      // If mid is greater than high, the minimum is in the right half
      if (nums[mid] > nums[high]) {
        low = mid + 1;
      } else {
        high = mid;
      }
    }

    return nums[low];
  }

  // Time: O(log n)
  // Space: O(1)
}