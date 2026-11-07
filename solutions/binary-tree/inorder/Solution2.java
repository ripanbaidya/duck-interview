class Solution {

  // Iterative
  public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> ans = new ArrayList<>();
    Deque<TreeNode> stack = new ArrayDeque<>();

    TreeNode node = root;

    while (node != null || !stack.isEmpty()) {
      if (node != null) {
        stack.push(node);
        node = node.left;
      } else {
        node = stack.pop();
        ans.add(node.val);
        node = node.right;
      }
    }

    return ans;
  }

  // Time: O(n)
  // Space: O(n) including output, O(h) auxiliary stack
}
