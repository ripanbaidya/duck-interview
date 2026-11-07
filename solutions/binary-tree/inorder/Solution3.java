class Solution {

  // Morris traversal
  public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> ans = new ArrayList<>();
    TreeNode curr = root;

    while (curr != null) {
      // If there is no left subtree, visit the current node
      // and move to the right.
      if (curr.left == null) {
        ans.add(curr.val);
        curr = curr.right;
      } else {
        // Find the rightmost node in the left subtree.
        // This node will be the inorder predecessor of curr.
        TreeNode prev = curr.left;

        while (prev.right != null && prev.right != curr) {
          prev = prev.right;
        }

        if (prev.right == null) {
          // First visit: create a temporary thread back to curr.
          // Move left to process the left subtree first.
          prev.right = curr;
          curr = curr.left;
        } else {
          // Second visit: the left subtree is processed.
          // Remove the temporary thread to restore the tree.
          // Visit curr and move to its right subtree.
          prev.right = null;
          ans.add(curr.val);
          curr = curr.right;
        }
      }
    }

    return ans;
  }

  // Time: O(n)
  // Space: O(1) auxiliary space, O(n) output space
}