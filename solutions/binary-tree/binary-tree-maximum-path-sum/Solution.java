class Solution {

  public int maxPathSum(TreeNode root) {
    int[] best = new int[] {Integer.MIN_VALUE};
    dfs(root, best);
    return best[0];
  }

  // Returns the max gain of a path that starts at node and goes down one side
  private int dfs(TreeNode node, int[] best) {
    if (node == null) return 0;

    // Gain is never negative: skip a child if it only lowers the sum
    int left = dfs(node.left, best);
    int right = dfs(node.right, best);

    // Path that bends at this node, using both sides
    best[0] = Math.max(best[0], node.val + left + right);

    // Going up to the parent, the path can use only one side
    return Math.max(0, node.val + Math.max(left, right));
  }

  // Time: O(n)
  // Space: O(h)
}