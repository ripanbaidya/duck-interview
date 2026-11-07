class Solution {
  
  public int[] searchRange(int[] nums, int target) {
    int first = getFirst(nums, target);
    int last = getLast(nums, target);
    return new int[]{first, last};
  }

  private int getFirst(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;
    int idx = -1;

    while (left <= right) {
      int mid = left + (right - left) / 2;

      if (nums[mid] == target) {
        idx = mid;
        right = mid - 1; // Search left for first occurrence
      } else if (nums[mid] < target) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    return idx;
  }

  private int getLast(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;
    int idx = -1;

    while (left <= right) {
      int mid = left + (right - left) / 2;

      if (nums[mid] == target) {
        idx = mid;
        left = mid + 1; // Search right for last occurrence
      } else if (nums[mid] < target) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    return idx;
  }

  // Time: O(log n)
  // Space: O(1)
}