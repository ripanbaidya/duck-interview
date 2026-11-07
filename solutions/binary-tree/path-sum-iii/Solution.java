class Solution {

  public int pathSum(TreeNode root, int targetSum) {
    Map<Long, Integer> prefix = new HashMap<>();
    prefix.put(0L, 1); // empty prefix, covers paths starting at root
    return dfs(root, 0L, targetSum, prefix);
  }

  private int dfs(TreeNode node, long sum, int target, Map<Long, Integer> prefix) {
    if (node == null) return 0;

    sum += node.val;

    // Earlier prefix = sum - target gives a path ending here with the target sum
    int count = prefix.getOrDefault(sum - target, 0);

    prefix.put(sum, prefix.getOrDefault(sum, 0) + 1);
    count += dfs(node.left, sum, target, prefix);
    count += dfs(node.right, sum, target, prefix);

    // Backtrack so sibling subtrees don't see this path
    prefix.put(sum, prefix.get(sum) - 1);

    return count;
  }

  // Time: O(n)
  // Space: O(n)
}