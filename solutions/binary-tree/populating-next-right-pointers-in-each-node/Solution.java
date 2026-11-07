class Solution {
  
  public Node connect(Node root) {
    if (root == null) {
      return null;
    }

    // Connect left child to right child.
    if (root.left != null) {
      root.left.next = root.right;

      // Connect right child to the left child of the next subtree.
      if (root.next != null) {
        root.right.next = root.next.left;
      }

      // Recursively connect the next level.
      connect(root.left);
      connect(root.right);
    }

    return root;
  }

  // Time: O(n)
  // Space: O(h) recursion stack, O(log n) for a perfect binary tree
}