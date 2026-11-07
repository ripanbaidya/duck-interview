class Solution {
  
  public int longestOnes(int[] nums, int k) {
    int i = 0, j = 0;
    
    for (; j < nums.length; j++) {
      if (nums[j] == 0) {
        k--;
      }
      
      // If we exceed allowed zeros, shrink the window from the left
      if (k < 0) {
        if (nums[i] == 0) {
          k++;
        }
        i++;
      }
    }
    
    return j - i;
  }

  // Time: O(n)
  // Space: O(1)
}