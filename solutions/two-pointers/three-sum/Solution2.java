// Better approach

class Solution {
  public List<List<Integer>> threeSum(int[] nums) {
    int n = nums.length;
    Set<List<Integer>> uniqueTriplets = new HashSet<>();

    for (int i = 0; i < n; i++) {
      Set<Integer> seen = new HashSet<>();

      for (int j = i + 1; j < n; j++) {
        int target = -(nums[i] + nums[j]);

        if (seen.contains(target)) {
          List<Integer> triplet = new ArrayList<>(
              Arrays.asList(nums[i], nums[j], target));

          Collections.sort(triplet);
          uniqueTriplets.add(triplet);
        }

        // Store the current value for future complement checks.
        seen.add(nums[j]);
      }
    }

    return new ArrayList<>(uniqueTriplets);
  }
}

// Time: O(n² +k log k) = O(n³), where 'k' is the number of triplets found;
//   since each triplet has three elements, this simplifies to O(n²)expected time.
// Space: O(n + k), where 'k' is the number of unique triplets stored.