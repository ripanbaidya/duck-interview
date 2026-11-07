class Solution {

  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    Arrays.sort(candidates);
    List<List<Integer>> res = new ArrayList<>();
    backtrack(candidates, 0, target, new ArrayList<>(), res);
    return res;
  }

  private void backtrack(int[] nums, int start, int remain, List<Integer> path,
      List<List<Integer>> res) {
    if (remain == 0) {
      res.add(new ArrayList<>(path));
      return;
    }

    for (int i = start; i < nums.length; i++) {
      // Sorted, so every later number is too large as well
      if (nums[i] > remain) break;

      path.add(nums[i]);
      // Pass i, not i + 1: the same number can be reused
      backtrack(nums, i, remain - nums[i], path, res);
      path.remove(path.size() - 1);
    }
  }

  // Time: O(n^(t/m))
  // Space: O(t/m)
}