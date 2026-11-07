class Solution {

  private int sum = 0;

  public TreeNode convertBST(TreeNode root) {
    if (root == null) return null;

    // Reverse inorder visits nodes from largest to smallest
    convertBST(root.right);
    sum += root.val;
    root.val = sum;
    convertBST(root.left);

    return root;
  }

  // Time: O(n)
  // Space: O(h)
}