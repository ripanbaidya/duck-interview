class Solution {

  // Morris traversal
  public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> ans = new ArrayList<>();

    TreeNode dummy = new TreeNode(0);
    dummy.left = root;

    TreeNode curr = dummy;

    while (curr != null) {
      if (curr.left == null) {
        curr = curr.right;
      } else {
        // Find the rightmost node in the left subtree.
        // This is the inorder predecessor of curr.
        TreeNode prev = curr.left;

        while (prev.right != null && prev.right != curr) {
          prev = prev.right;
        }

        if (prev.right == null) {
          // Create a temporary thread back to curr.
          prev.right = curr;
          curr = curr.left;
        } else {
          // Left subtree is processed.
          // Reverse the right boundary to get postorder.
          reverseRight(curr.left, prev);

          // Visit nodes along the reversed boundary.
          TreeNode node = prev;

          while (true) {
            ans.add(node.val);

            if (node == curr.left) {
              break;
            }

            node = node.right;
          }

          // Restore the original right pointers.
          reverseRight(prev, curr.left);

          // Remove the temporary thread.
          prev.right = null;

          curr = curr.right;
        }
      }
    }

    return ans;
  }

  // Reverse the right pointers from start to end.
  private void reverseRight(TreeNode start, TreeNode end) {
    if (start == end) {
      return;
    }

    TreeNode prev = start;
    TreeNode curr = start.right;

    while (prev != end) {
      TreeNode next = curr.right;
      curr.right = prev;
      prev = curr;
      curr = next;
    }
  }

  // Time: O(n)
  // Space: O(1) auxiliary space, O(n) output space
}
