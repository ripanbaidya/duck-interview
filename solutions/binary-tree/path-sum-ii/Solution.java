class Solution {

  public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
    List<List<Integer>> result = new ArrayList<>();
    dfs(root, targetSum, new ArrayList<>(), result);
    return result;
  }

  private void dfs(TreeNode node, int targetSum, List<Integer> path, List<List<Integer>> result) {
    if (node == null) 
      return;

    path.add(node.val);

    // If leaf node and remaining target matches, add copy of path
    if (node.left == null && node.right == null && targetSum == node.val) {
      result.add(new ArrayList<>(path));
    } else {
      int remaining = targetSum - node.val;
      dfs(node.left, remaining, path, result);
      dfs(node.right, remaining, path, result);
    }

    path.remove(path.size() - 1);
  }

  // Time: O(N ^ 2) in the worst case due to path copying, O(N log N) balanced
  // Space: O(N)
}