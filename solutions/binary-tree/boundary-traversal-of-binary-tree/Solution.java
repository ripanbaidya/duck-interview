class Solution {

   public List<Integer> boundaryTraversal(Node node) {
    List<Integer> result = new ArrayList<>();
    if (node == null) 
      return result;

    if (!isLeaf(node)) {
      result.add(node.data);
    }

    addLeftNodes(node.left, result);
    addLeafNodes(node, result);
    addRightNodes(node.right, result);

    return result;
  }

  public void addLeftNodes(Node node, ArrayList<Integer> result) {
    while (node != null) {
      if (!isLeaf(node)) {
        result.add(node.data);
      }

      node = (node.left != null) ? node.left : node.right;
    }
  }

  public void addRightNodes(Node node, ArrayList<Integer> result) {
    Stack<Integer> stack = new Stack<>();

    while (node != null) {
      if (!isLeaf(node)) {
        stack.push(node.data);
      }

      node = (node.right != null) ? node.right : node.left;
    }

    // Add right boundary in bottom-up order.
    while (!stack.isEmpty()) {
      result.add(stack.pop());
    }
  }

  public void addLeafNodes(Node node, ArrayList<Integer> result) {
    if (node == null) return;

    if (isLeaf(node)) {
      result.add(node.data);
      return;
    }

    addLeafNodes(node.left, result);
    addLeafNodes(node.right, result);
  }

  public boolean isLeaf(Node node) {
    return node.left == null && node.right == null;
  }

  // Time: O(N)
  // Space: O(H + N)
}
