class Solution {
  static class Pair {
    TreeNode node;
    long index;

    Pair(TreeNode node, long index) {
      this.node = node;
      this.index = index;
    }
  }

  public int widthOfBinaryTree(TreeNode root) {
    if (root == null) {
      return 0;
    }

    int maxWidth = 0;
    Queue<Pair> queue = new ArrayDeque<>();
    queue.offer(new Pair(root, 0));

    while (!queue.isEmpty()) {
      int size = queue.size();

      // Normalize indices to avoid unnecessary growth.
      long minIndex = queue.peek().index;

      long first = 0;
      long last = 0;

      for (int i = 0; i < size; i++) {
        Pair curr = queue.poll();

        TreeNode node = curr.node;
        long index = curr.index - minIndex;

        // Capture the first and last positions at this level.
        if (i == 0) {
          first = index;
        }

        if (i == size - 1) {
          last = index;
        }

        // Calculate the positional indices of the children.
        if (node.left != null) {
          queue.offer(new Pair(node.left, 2 * index));
        }

        if (node.right != null) {
          queue.offer(new Pair(node.right, 2 * index + 1));
        }
      }

      // Width includes gaps between the first and last nodes.
      maxWidth = Math.max(maxWidth, (int) (last - first + 1));
    }

    return maxWidth;
  }

  // Time: O(n)
  // Space: O(w) auxiliary queue, where w is the maximum width
}
