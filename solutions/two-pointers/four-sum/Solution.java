// Optimal solution

class Solution {
  public List<List<Integer>> fourSum(int[] nums, int target) {
    int n = nums.length;

    // Sort the array to use two pointers and skip duplicate values efficiently.
    Arrays.sort(nums);

    List<List<Integer>> quadruplets = new ArrayList<>();

    for (int i = 0; i < n - 3; i++) {
      // Skip duplicate first elements to avoid repeating quadruplets.
      if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
      }

      for (int j = i + 1; j < n - 2; j++) {
        // Skip duplicate second elements for unique quadruplets.
        if (j > i + 1 && nums[j] == nums[j - 1]) {
          continue;
        }

        int left = j + 1;
        int right = n - 1;

        while (left < right) {
          // Use long to prevent integer overflow while calculating the sum.
          long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];

          if (sum == target) {
            quadruplets.add(Arrays.asList(
                nums[i], nums[j], nums[left], nums[right]));

            left++;
            right--;

            // Skip duplicates after finding a valid quadruplet.
            while (left < right && nums[left] == nums[left - 1]) {
              left++;
            }

            while (left < right && nums[right] == nums[right + 1]) {
              right--;
            }
          } else if (sum < target) {
            // Increase the sum by moving the left pointer forward.
            left++;
          } else {
            // Decrease the sum by moving the right pointer backward.
            right--;
          }
        }
      }
    }

    return quadruplets;
  }
}

// Time: O(n³)
// Space: O(1) auxiliary space, excluding the output list and sorting stack.