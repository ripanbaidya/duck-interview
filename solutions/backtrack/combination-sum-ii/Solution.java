class Solution {

  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
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
      if (nums[i] > remain) break;

      // Skip a duplicate at the same depth, or the same combination repeats
      if (i > start && nums[i] == nums[i - 1]) continue;

      path.add(nums[i]);
      backtrack(nums, i + 1, remain - nums[i], path, res);
      path.remove(path.size() - 1);
    }
  }

  // Time: O(2^n)
  // Space: O(n)
}