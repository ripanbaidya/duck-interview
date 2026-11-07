class Solution {

  public boolean findTarget(TreeNode root, int k) {
    return dfs(root, k, new HashSet<>());
  }

  private boolean dfs(TreeNode node, int k, Set<Integer> seen) {
    if (node == null) return false;
    if (seen.contains(k - node.val)) return true;

    seen.add(node.val);
    return dfs(node.left, k, seen) || dfs(node.right, k, seen);
  }

  // Time: O(n)
  // Space: O(n)
}