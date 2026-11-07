// Better solution
// Note: This solution is optimal in terms of time and space complexity, but it requires two passes.
// In an interview, your interviewer might ask you to implement the one-pass solution, which has
// the same time and space complexity but avoids the extra pass.
// Check 'sort-colors/Solution.java' for the one-pass solution using the Dutch National Flag algorithm.

class Solution {
  public void sortColors(int[] nums) {
    int zeroCount = 0, oneCount = 0, twoCount = 0;

    // Count the occurrences of each color.
    for (int num : nums) {
      if (num == 0) {
        zeroCount++;
      } else if (num == 1) {
        oneCount++;
      } else {
        twoCount++;
      }
    }

    int index = 0;

    // Overwrite the array in sorted order using the counts.
    while (zeroCount-- > 0) {
      nums[index++] = 0;
    }

    while (oneCount-- > 0) {
      nums[index++] = 1;
    }

    while (twoCount-- > 0) {
      nums[index++] = 2;
    }
  }
}

// Time: O(n)
// Space: O(1)