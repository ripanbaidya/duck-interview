class Solution {

  public int diameterOfBinaryTree(TreeNode root) {
    int[] diameter = new int[1];
    dfs(root, diameter);
    return diameter[0];
  }

  private int dfs(TreeNode node, int[] diameter) {
    if (node == null) return 0;

    int left = dfs(node.left, diameter);
    int right = dfs(node.right, diameter);

    // Diameter through the current node.
    diameter[0] = Math.max(diameter[0], left + right);

    return 1 + Math.max(left, right);
  }

  // Time: O(N)
  // Space: O(H) - recursion stack, where H is tree height
}
