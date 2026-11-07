class Solution {

  public int kthLargest(Node root, int k) {
    Deque<Node> stack = new ArrayDeque<>();
    Node curr = root;

    // Reverse inorder: right, node, left
    while (curr != null || !stack.isEmpty()) {
      while (curr != null) {
        stack.push(curr);
        curr = curr.right;
      }
      curr = stack.pop();
      k--;
      if (k == 0) return curr.data;
      curr = curr.left;
    }
    return -1;
  }

  // Time: O(h + k)
  // Space: O(h)
}