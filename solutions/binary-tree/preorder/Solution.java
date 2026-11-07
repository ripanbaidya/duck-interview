class Solution {

  // Recursive
  public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> ans = new ArrayList<>();
    dfs(root, ans);
    return ans;
  }

  // Root -> Left -> Right
  private void dfs(TreeNode root, List<Integer> ans) {
    if (root == null) {
      return;
    }

    // Visit the root first.
    ans.add(root.val);

    // Traverse the left subtree, then the right subtree.
    dfs(root.left, ans);
    dfs(root.right, ans);
  }

  // Time: O(n)
  // Space: O(n) including output, O(h) auxiliary recursion stack
}
