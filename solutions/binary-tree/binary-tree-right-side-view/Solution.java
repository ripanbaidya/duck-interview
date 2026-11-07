class Solution {

  public List<Integer> rightSideView(TreeNode root) {
    List<Integer> ans = new ArrayList<>();

    if (root == null) {
      return ans;
    }

    Queue<TreeNode> queue = new ArrayDeque<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      int size = queue.size();

      for (int i = 0; i < size; i++) {
        TreeNode curr = queue.poll();

        // Add children to process the next level.
        if (curr.left != null) {
          queue.offer(curr.left);
        }

        if (curr.right != null) {
          queue.offer(curr.right);
        }

        // The last node at each level is visible from the right.
        if (i == size - 1) {
          ans.add(curr.val);
        }
      }
    }

    return ans;
  }

  // Time: O(n)
  // Space: O(n) including output, O(w) auxiliary queue
}
