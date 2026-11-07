class Solution {
  
  public int subarraysDivByK(int[] nums, int k) {
    int totalCount = 0;
    int prefixSum = 0;

    // Store {remainder, frequency}
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 1); // Base case: prefix sum divisible by k

    for (int num : nums) {
      prefixSum += num;

      // Normalize remainder to handle negative values
      int remainder = (prefixSum % k + k) % k;

      // If this remainder was seen before,
      // all previous occurrences form valid subarrays
      totalCount += map.getOrDefault(remainder, 0);

      // Update frequency of current remainder
      map.put(remainder, map.getOrDefault(remainder, 0) + 1);
    }

    return totalCount;
  }

  // Time: O(n)
  // Space: O(min(n, k))
}