class BSTIterator {

  private Deque<TreeNode> stack = new ArrayDeque<>();

  public BSTIterator(TreeNode root) {
    pushLeft(root);
  }

  public int next() {
    TreeNode node = stack.pop();
    pushLeft(node.right);
    return node.val;
  }

  public boolean hasNext() {
    return !stack.isEmpty();
  }

  // Stack holds only the path of pending nodes, never the whole tree
  private void pushLeft(TreeNode node) {
    while (node != null) {
      stack.push(node);
      node = node.left;
    }
  }

  // Time: O(1) amortized per next(), O(h) worst case
  // Space: O(h)
}