class Solution {

  public Node connect(Node root) {
    if (root == null) {
      return null;
    }

    Queue<Node> queue = new ArrayDeque<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      // Capture the number of nodes at the current level.
      int size = queue.size();

      for (int i = 0; i < size; i++) {
        Node curr = queue.poll();

        // Add children to process the next level.
        if (curr.left != null) {
          queue.offer(curr.left);
        }

        if (curr.right != null) {
          queue.offer(curr.right);
        }

        // Connect to the next node at the same level.
        // The last node at each level points to null.
        curr.next = (i + 1 < size) ? queue.peek() : null;
      }
    }

    return root;
  }

  // Time: O(n)
  // Space: O(n) including the tree's queue, O(w) auxiliary queue
}