class Solution {

  public List<TreeNode> delNodes(TreeNode root, int[] toDelete) {
    Set<Integer> del = new HashSet<>();
    for (int val : toDelete)
      del.add(val);

    List<TreeNode> res = new ArrayList<>();
    if (dfs(root, del, res) != null) 
      res.add(root);
    return res;
  }

  // Returns the node if it stays, or null if it is deleted
  private TreeNode dfs(TreeNode node, Set<Integer> del, List<TreeNode> res) {
    if (node == null) 
      return null;

    // Postorder: children are handled first so the parent can cut them off
    node.left = dfs(node.left, del, res);
    node.right = dfs(node.right, del, res);

    if (!del.contains(node.val)) 
      return node;

    // Deleted node: its surviving children become roots of new trees
    if (node.left != null) 
      res.add(node.left);
    if (node.right != null) 
      res.add(node.right);
    return null;
  }

  // Time: O(n)
  // Space: O(n)
}