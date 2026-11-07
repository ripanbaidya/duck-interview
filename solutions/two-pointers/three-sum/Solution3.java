// Brute force

class Solution {
  public List<List<Integer>> threeSum(int[] nums) {
    int n = nums.length;
    Set<List<Integer>> uniqueTriplets = new HashSet<>();

    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        for (int k = j + 1; k < n; k++) {
          if (nums[i] + nums[j] + nums[k] == 0) {
            List<Integer> triplet = new ArrayList<>(
                Arrays.asList(nums[i], nums[j], nums[k]));

            Collections.sort(triplet);
            uniqueTriplets.add(triplet);
          }
        }
      }
    }

    return new ArrayList<>(uniqueTriplets);
  }
}

// Time: O(n³log 3) = O(n³)
// Space: O(m), where 'm' is the number of unique triplets stored.