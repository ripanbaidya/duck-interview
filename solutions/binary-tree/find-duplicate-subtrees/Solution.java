class Solution {

  private Map<String, Integer> ids = new HashMap<>();
  private Map<Integer, Integer> count = new HashMap<>();
  private List<TreeNode> res = new ArrayList<>();

  public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
    dfs(root);
    return res;
  }

  // Gives every distinct subtree shape a unique id; null is 0
  private int dfs(TreeNode node) {
    if (node == null) 
      return 0;

    // Child ids keep the key short, so there is no O(n) string per node
    String key = dfs(node.left) + "," + node.val + "," + dfs(node.right);

    Integer id = ids.get(key);
    if (id == null) {
      id = ids.size() + 1;
      ids.put(key, id);
    }

    // Add on the second sighting only, so each duplicate is reported once
    if (count.merge(id, 1, Integer::sum) == 2) res.add(node);

    return id;
  }

  // Time: O(n)
  // Space: O(n)
}