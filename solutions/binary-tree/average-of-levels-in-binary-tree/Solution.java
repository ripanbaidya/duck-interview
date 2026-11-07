class Solution {

  public List<Double> averageOfLevels(TreeNode root) {
    List<Double> ans = new ArrayList<>();

    if (root == null) {
      return ans;
    }

    Queue<TreeNode> queue = new ArrayDeque<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      int size = queue.size();
      double sum = 0.0;

      // Process all nodes at the current level.
      for (int i = 0; i < size; i++) {
        TreeNode curr = queue.poll();
        sum += curr.val;

        // Add children to process the next level.
        if (curr.left != null) {
          queue.offer(curr.left);
        }

        if (curr.right != null) {
          queue.offer(curr.right);
        }
      }

      // Calculate the average of the current level.
      ans.add(sum / size);
    }

    return ans;
  }

  // Time: O(n)
  // Space: O(n) including output, O(w) auxiliary queue
}