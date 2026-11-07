class Solution {

  private boolean foundP = false;
  private boolean foundQ = false;

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    TreeNode lca = dfs(root, p, q);
    return foundP && foundQ ? lca : null;
  }

  private TreeNode dfs(TreeNode node, TreeNode p, TreeNode q) {
    if (node == null) 
      return null;

    // Visit both subtrees before the early return, so a deeper p or q is still seen
    TreeNode left = dfs(node.left, p, q);
    TreeNode right = dfs(node.right, p, q);

    if (node == p) foundP = true;
    if (node == q) foundQ = true;
    if (node == p || node == q) 
      return node;

    if (left != null && right != null) 
      return node;

    return left != null ? left : right;
  }

  // Time: O(n)
  // Space: O(h)
}
