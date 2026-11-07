class Solution {
  
  public int subarraysWithKDistinct(int[] nums, int k) {
    return atMost(nums, k) - atMost(nums, k - 1);
  }

  // Helper function to count subarrays with at most k distinct elements
  private int atMost(int[] nums, int k) {
    Map<Integer, Integer> freqMap = new HashMap<>();
    int left = 0;
    int count = 0;

    for (int right = 0; right < nums.length; right++) {
      freqMap.put(nums[right], freqMap.getOrDefault(nums[right], 0) + 1);

      while (freqMap.size() > k) {
        int leftVal = nums[left];
        freqMap.put(leftVal, freqMap.get(leftVal) - 1);
        if (freqMap.get(leftVal) == 0) {
          freqMap.remove(leftVal);
        }
        left++;
      }

      count += right - left + 1;
    }

    return count;
  }

  // Time: O(n)
  // Space: O(k)
}