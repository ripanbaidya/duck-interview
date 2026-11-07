class Solution {
  public int[] sortedSquares(int[] nums) {
    int n = nums.length;
    int[] result = new int[n];

    // Initialize two pointers:
    // 'left' starts at the beginning of the array (negative numbers)
    // 'right' starts at the end of the array (positive numbers)
    int left = 0;
    int right = n - 1;

    // Fill the result array from right to left (largest to smallest squares)
    for (int i = n - 1; i >= 0; i--) {
      int leftSquare = nums[left] * nums[left];
      int rightSquare = nums[right] * nums[right];

      // Compare the squares of the elements at the left and right pointers
      if (leftSquare > rightSquare) {
        result[i] = leftSquare;
        left++; // Move left pointer forward
      } else {
        result[i] = rightSquare;
        right--; // Move right pointer backward
      }
    }

    return result;
  }
}

// Time: O(n)
// Space: O(1)