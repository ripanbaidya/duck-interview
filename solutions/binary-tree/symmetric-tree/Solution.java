class Solution {

  public boolean isSymmetric(TreeNode root) {
    return mirror(root.left, root.right);
  }

  private boolean mirror(TreeNode a, TreeNode b) {
    if (a == null && b == null) 
      return true;
    if (a == null || b == null) 
      return false;
    if (a.val != b.val) 
      return false;

    // Outer pair and inner pair must match
    return mirror(a.left, b.right) && mirror(a.right, b.left);
  }

  // Time: O(n)
  // Space: O(h)
}