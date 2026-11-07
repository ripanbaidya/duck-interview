class Solution {

  public int sumNumbers(TreeNode root) {
    return dfs(root, 0);
  }

  private int dfs(TreeNode node, int curr) {
    if (node == null) return 0;

    curr = curr * 10 + node.val;

    // Leaf: curr is a complete root-to-leaf number
    if (node.left == null && node.right == null) return curr;

    return dfs(node.left, curr) + dfs(node.right, curr);
  }

  // Time: O(n)
  // Space: O(h)
}