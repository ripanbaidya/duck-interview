class Solution {

  public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> ans = new ArrayList<>();
    dfs(root, ans);
    return ans;
  }

  // Left -> Right -> Root
  private void dfs(TreeNode root, List<Integer> ans) {
    if (root == null) {
      return;
    }

    dfs(root.left, ans);
    dfs(root.right, ans);
    ans.add(root.val);
  }

  // Time: O(n)
  // Space: O(n) including output, O(h) auxiliary recursion stack
}
