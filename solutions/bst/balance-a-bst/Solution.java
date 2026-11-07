class Solution {

  private List<Integer> vals = new ArrayList<>();

  public TreeNode balanceBST(TreeNode root) {
    inorder(root);
    return build(0, vals.size() - 1);
  }

  private void inorder(TreeNode node) {
    if (node == null) return;
    inorder(node.left);
    vals.add(node.val);
    inorder(node.right);
  }

  private TreeNode build(int left, int right) {
    if (left > right) return null;

    int mid = left + (right - left) / 2;
    TreeNode node = new TreeNode(vals.get(mid));
    node.left = build(left, mid - 1);
    node.right = build(mid + 1, right);
    return node;
  }

  // Time: O(n)
  // Space: O(n)
}