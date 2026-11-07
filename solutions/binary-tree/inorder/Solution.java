class Solution {

  // Recursive
  public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> ans = new ArrayList<>();
    dfs(root, ans);
    return ans;
  }

  // Left -> Root -> Right
  private void dfs(TreeNode root, List<Integer> ans) {
    if (root == null) {
      return;
    }

    dfs(root.left, ans);
    ans.add(root.val);
    dfs(root.right, ans);
  }

  // Time: O(n)
  // Space: O(n) including output, O(h) auxiliary recursion stack
}
