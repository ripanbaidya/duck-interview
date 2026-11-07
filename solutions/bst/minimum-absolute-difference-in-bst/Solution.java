class Solution {

  private int min = Integer.MAX_VALUE;
  private TreeNode prev = null;

  public int getMinimumDifference(TreeNode root) {
    inorder(root);
    return min;
  }

  // Inorder is sorted, so the minimum gap is always between neighbors
  private void inorder(TreeNode node) {
    if (node == null) return;

    inorder(node.left);
    if (prev != null) min = Math.min(min, node.val - prev.val);
    prev = node;
    inorder(node.right);
  }

  // Time: O(n)
  // Space: O(h)
}