class Solution {
  public void moveZeroes(int[] nums) {
    // Pointer 'i' tracks the position where the next
    // non-zero element should be placed
    int i = 0;

    // Iterate through the array to shift all non-zero elements
    // to the front
    for (int j = 0; j < nums.length; j++) {
      if (nums[j] != 0) {
        nums[i] = nums[j];
        i++;
      }
    }

    // Fill the remaining positions from index 'i' to the end of
    // the array with zeros
    for (int j = i; j < nums.length; j++) {
      nums[j] = 0;
    }
  }
}

// Time: O(n)
// Space: O(1)