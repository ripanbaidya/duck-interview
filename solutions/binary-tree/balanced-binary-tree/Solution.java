class Solution {

  public boolean isBalanced(TreeNode root) {
    return dfs(root) != -1;
  }

  private int dfs(TreeNode node) {
    if (node == null) 
      return 0;

    int left = dfs(node.left);
    if (left == -1) 
      return -1;

    int right = dfs(node.right);
    if (right == -1) 
      return -1;

    // Return -1 if the current subtree is unbalanced.
    if (Math.abs(left - right) > 1) {
      return -1;
    }

    return 1 + Math.max(left, right);
  }

  // Time: O(N)
  // Space: O(H) - recursion stack, where H is tree height
}
