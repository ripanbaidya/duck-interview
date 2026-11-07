class Solution {

  private Node head, prev;

  public Node bToDLL(Node root) {
    head = null;
    prev = null;
    inorder(root);
    return head;
  }

  // Inorder order becomes the list order; left = prev, right = next
  private void inorder(Node node) {
    if (node == null) return;

    inorder(node.left);

    if (prev == null) {
      head = node;
    } else {
      prev.right = node;
      node.left = prev;
    }
    prev = node;

    inorder(node.right);
  }

  // Time: O(n)
  // Space: O(h)
}