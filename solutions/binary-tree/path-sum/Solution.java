class Solution {
  
  public boolean hasPathSum(TreeNode root, int targetSum) {
    if (root == null) return false;

    // Check if leaf node matches remaining target
    if (root.left == null && root.right == null) {
      return targetSum == root.val;
    }

    int remaining = targetSum - root.val;
    return hasPathSum(root.left, remaining) || hasPathSum(root.right, remaining);
  }

  // Time: O(N)
  // Space: O(H)
}