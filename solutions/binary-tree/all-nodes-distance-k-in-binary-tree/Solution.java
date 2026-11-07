class Solution {

  public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
    Map<TreeNode, TreeNode> parent = new HashMap<>();
    setParents(root, null, parent);

    // BFS from target, treating the tree as an undirected graph
    Queue<TreeNode> queue = new ArrayDeque<>();
    Set<TreeNode> visited = new HashSet<>();
    queue.add(target);
    visited.add(target);

    int dist = 0;
    while (!queue.isEmpty() && dist < k) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        TreeNode node = queue.poll();
        for (TreeNode next : new TreeNode[] {node.left, node.right, parent.get(node)}) {
          if (next != null && visited.add(next)) queue.add(next);
        }
      }
      dist++;
    }

    List<Integer> res = new ArrayList<>();
    for (TreeNode node : queue) res.add(node.val);
    return res;
  }

  private void setParents(TreeNode node, TreeNode par, Map<TreeNode, TreeNode> parent) {
    if (node == null) return;
    parent.put(node, par);
    setParents(node.left, node, parent);
    setParents(node.right, node, parent);
  }

  // Time: O(n)
  // Space: O(n)
}
