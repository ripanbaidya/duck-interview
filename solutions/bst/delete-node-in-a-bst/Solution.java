class Solution {

  public TreeNode deleteNode(TreeNode root, int key) {
    if (root == null) return null;

    if (key < root.val) {
      root.left = deleteNode(root.left, key);
    } else if (key > root.val) {
      root.right = deleteNode(root.right, key);
    } else {
      if (root.left == null) return root.right;
      if (root.right == null) return root.left;

      // Two children: copy the inorder successor, then delete it from the right side
      TreeNode succ = root.right;
      while (succ.left != null) succ = succ.left;
      root.val = succ.val;
      root.right = deleteNode(root.right, succ.val);
    }
    return root;
  }

  // Time: O(h)
  // Space: O(h)
}