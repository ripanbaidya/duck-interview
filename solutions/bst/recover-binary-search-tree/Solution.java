class Solution {

  private TreeNode first, second, prev;

  public void recoverTree(TreeNode root) {
    inorder(root);
    int temp = first.val;
    first.val = second.val;
    second.val = temp;
  }

  private void inorder(TreeNode node) {
    if (node == null) return;

    inorder(node.left);

    if (prev != null && prev.val > node.val) {
      // First drop marks the larger swapped node (prev).
      // A second drop, if any, marks the smaller one (node).
      if (first == null) first = prev;
      second = node;
    }
    prev = node;

    inorder(node.right);
  }

  // Time: O(n)
  // Space: O(h)
}