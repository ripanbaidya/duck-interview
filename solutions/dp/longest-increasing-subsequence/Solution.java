class Solution {

  public int lengthOfLIS(int[] nums) {
    // tails[i] = smallest tail value of any increasing subsequence of length i + 1
    int[] tails = new int[nums.length];
    int size = 0;

    for (int num : nums) {
      // First index with tails[idx] >= num (strict increase, so equal values replace)
      int left = 0, right = size;
      while (left < right) {
        int mid = left + (right - left) / 2;
        if (tails[mid] < num) left = mid + 1;
        else right = mid;
      }
      tails[left] = num;
      if (left == size) size++;
    }
    return size;
  }

  // Time: O(n log n)
  // Space: O(n)
}