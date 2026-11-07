class Solution {

  public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) {
      return res;
    }

    Queue<TreeNode> deq = new ArrayDeque<>();
    deq.offer(root);

    while (!deq.isEmpty()) {
      int size = deq.size();
      List<Integer> level = new ArrayList<>();

      for (int i = 0; i < size; i++) {
        TreeNode curr = deq.poll();
        level.add(curr.val);

        if (curr.left != null) {
          deq.offer(curr.left);
        }
        if (curr.right != null) {
          deq.offer(curr.right);
        }
      }

      res.add(level);
    }

    return res;
  }

  // Time: O(N)
  // Space: O(N)
}
