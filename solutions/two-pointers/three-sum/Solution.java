// Optimal solution - using two pointers

class Solution {
  public List<List<Integer>> threeSum(int[] nums) {
    int n = nums.length;
    List<List<Integer>> triplets = new ArrayList<>();
    Arrays.sort(nums);

    for (int i = 0; i < n - 2; i++) {
      // Skip duplicate values to avoid repeating triplets.
      if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
      }

      // A positive first element cannot form a zero-sum triplet.
      if (nums[i] > 0) {
        break;
      }

      int left = i + 1;
      int right = n - 1;

      while (left < right) {
        int sum = nums[i] + nums[left] + nums[right];

        if (sum == 0) {
          triplets.add(Arrays.asList(nums[i], nums[left], nums[right]));
          left++;
          right--;

          // Skip duplicate values at both pointers.
          while (left < right && nums[left] == nums[left - 1]) {
            left++;
          }

          while (left < right && nums[right] == nums[right + 1]) {
            right--;
          }
        } else if (sum < 0) {
          left++;
        } else {
          right--;
        }
      }
    }

    return triplets;
  }
}

// Time: O(n²)
// Space: O(1) auxiliary space, excluding the output list. 
//  Sorting may require O(log n) stack space.