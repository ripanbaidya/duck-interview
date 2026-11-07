class Solution {

  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) {
      return res;
    }

    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);
    boolean leftToRight = true;

    while (!q.isEmpty()) {
      int size = q.size();
      LinkedList<Integer> level = new LinkedList<>();

      for (int i = 0; i < size; i++) {
        TreeNode curr = q.poll();

        if (leftToRight) {
          level.addLast(curr.val);
        } else {
          level.addFirst(curr.val);
        }

        if (curr.left != null) {
          q.offer(curr.left);
        }
        if (curr.right != null) {
          q.offer(curr.right);
        }
      }

      res.add(level);
      leftToRight = !leftToRight;
    }

    return res;
  }

  // Time: O(N)
  // Space: O(N)
}
