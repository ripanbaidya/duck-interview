class Solution {

  public int findNumberOfLIS(int[] nums) {
    int n = nums.length;
    int[] len = new int[n]; // length of the LIS ending at i
    int[] cnt = new int[n]; // number of LIS of that length ending at i
    int maxLen = 0, res = 0;

    for (int i = 0; i < n; i++) {
      len[i] = 1;
      cnt[i] = 1;
      for (int j = 0; j < i; j++) {
        if (nums[j] >= nums[i]) continue;

        if (len[j] + 1 > len[i]) {
          len[i] = len[j] + 1;
          cnt[i] = cnt[j];
        } else if (len[j] + 1 == len[i]) {
          cnt[i] += cnt[j];
        }
      }

      if (len[i] > maxLen) {
        maxLen = len[i];
        res = cnt[i];
      } else if (len[i] == maxLen) {
        res += cnt[i];
      }
    }
    return res;
  }

  // Time: O(n^2)
  // Space: O(n)
}