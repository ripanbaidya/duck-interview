// Sorting
class Solution {
  public int longestConsecutive(int[] nums) {
    if (nums.length == 0)
      return 0;

    Arrays.sort(nums);

    int maxi = 1;
    int curConSeq = 1;

    for (int i = 1; i < nums.length; i++) {
      // Skip duplicate numbers
      if (nums[i] == nums[i - 1]) {
        continue;
      }

      if (nums[i] == nums[i - 1] + 1) {
        curConSeq++;
      } else {
        curConSeq = 1;
      }

      maxi = Math.max(maxi, curConSeq);
    }

    return maxi;
  }
}
// Time: O(n log n)
// Space: O(1