class Solution {

  public int rob(TreeNode root) {
    int[] res = dfs(root);
    return Math.max(res[0], res[1]);
  }

  // Returns {best if node is robbed, best if node is skipped}
  private int[] dfs(TreeNode node) {
    if (node == null) return new int[2];

    int[] left = dfs(node.left);
    int[] right = dfs(node.right);

    // Robbing this node forces both children to be skipped
    int rob = node.val + left[1] + right[1];

    // Skipping it leaves each child free to be robbed or not
    int skip = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

    return new int[] {rob, skip};
  }

  // Time: O(n)
  // Space: O(h)
}