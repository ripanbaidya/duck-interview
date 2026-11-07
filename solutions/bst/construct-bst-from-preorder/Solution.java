class Solution {

  private int idx = 0;

  public TreeNode bstFromPreorder(int[] preorder) {
    return build(preorder, Integer.MAX_VALUE);
  }

  // bound is the largest value allowed in this subtree
  private TreeNode build(int[] preorder, int bound) {
    if (idx == preorder.length || preorder[idx] > bound) return null;

    TreeNode node = new TreeNode(preorder[idx]);
    idx++;
    node.left = build(preorder, node.val);
    node.right = build(preorder, bound);
    return node;
  }

  // Time: O(n)
  // Space: O(h)
}