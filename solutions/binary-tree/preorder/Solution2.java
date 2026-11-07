class Solution {

  // Iterative
  public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> ans = new ArrayList<>();
    Deque<TreeNode> stack = new ArrayDeque<>();

    if (root == null) {
      return ans;
    }

    stack.push(root);

    while (!stack.isEmpty()) {
      TreeNode curr = stack.pop();

      // Visit the current node first.
      ans.add(curr.val);

      // Push right first so left is processed first (LIFO).
      if (curr.right != null) {
        stack.push(curr.right);
      }

      if (curr.left != null) {
        stack.push(curr.left);
      }
    }

    return ans;
  }

  // Time: O(n)
  // Space: O(n) including output, O(h) to O(n) auxiliary stack
}
