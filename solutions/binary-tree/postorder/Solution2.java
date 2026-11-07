class Solution {

  public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> ans = new ArrayList<>();
    Deque<TreeNode> stack = new ArrayDeque<>();

    if (root == null) {
      return ans;
    }

    stack.push(root);

    while (!stack.isEmpty()) {
      TreeNode curr = stack.pop();

      // Visit the root first.
      ans.add(curr.val);

      // Push left first so right is processed first (LIFO).
      if (curr.left != null) {
        stack.push(curr.left);
      }

      if (curr.right != null) {
        stack.push(curr.right);
      }
    }

    // Reverse Root -> Right -> Left to get Left -> Right -> Root.
    Collections.reverse(ans);

    return ans;
  }

  // Time: O(n)
  // Space: O(n) including output, O(n) auxiliary stack
}
