class Solution {
  
  int ans;

  public int longestUnivaluePath(TreeNode root) {
    // Use -1 for the parent value of the root node.
    solve(root, -1);
    return ans;
  }

  int solve(TreeNode root, int parent) {
    if (root == null) {
      return 0;
    }

    // The longest univalue path can cover both sides of the root.
    int left = solve(root.left, root.val);
    int right = solve(root.right, root.val);

    ans = Math.max(ans, left + right);

    // Return the longest matching path including the current node.
    return root.val == parent ? Math.max(left, right) + 1 : 0;
  }

  // Time: O(N)
  // Space: O(H) - recursion stack, where H is tree height
}