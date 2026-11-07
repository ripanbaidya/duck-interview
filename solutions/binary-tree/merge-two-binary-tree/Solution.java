class Solution {

  public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
    if (root1 == null) 
      return root2;
    if (root2 == null) 
      return root1;

    // Reuse root1 as the merged tree
    root1.val += root2.val;
    root1.left = mergeTrees(root1.left, root2.left);
    root1.right = mergeTrees(root1.right, root2.right);

    return root1;
  }

  // Time: O(min(m, n))
  // Space: O(min(h1, h2))
}