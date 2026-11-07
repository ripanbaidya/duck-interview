class Solution {

  public boolean flipEquiv(TreeNode root1, TreeNode root2) {
    if (root1 == null && root2 == null) 
      return true;
    if (root1 == null || root2 == null) 
      return false;
    if (root1.val != root2.val) 
      return false;

    // Either no flip at this node, or flip at this node
    boolean same = flipEquiv(root1.left, root2.left)
        && flipEquiv(root1.right, root2.right);
    boolean flipped = flipEquiv(root1.left, root2.right)
        && flipEquiv(root1.right, root2.left);

    return same || flipped;
  }

  // Time: O(min(n1, n2))
  // Space: O(min(h1, h2))
}