class Solution {

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    while (root != null) {
      if (p.val < root.val && q.val < root.val) root = root.left;
      else if (p.val > root.val && q.val > root.val) root = root.right;
      else return root; // split point, or root is p or q
    }
    return null;
  }

  // Time: O(h)
  // Space: O(1)
}