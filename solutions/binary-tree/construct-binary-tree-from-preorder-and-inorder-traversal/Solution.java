class Solution {
  public TreeNode buildTree(int[] preorder, int[] inorder) {
    if (preorder.length != inorder.length) {
      return null;
    }

    int n = inorder.length;

    // Store each value's index in inorder for O(1) average lookup.
    Map<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < n; i++) {
      map.put(inorder[i], i);
    }

    return buildTreeHelper(preorder, 0, n - 1, 0, n - 1, map);
  }

  private TreeNode buildTreeHelper(
      int[] preorder, int preStart, int preEnd,
      int inStart, int inEnd,
      Map<Integer, Integer> map) {

    // No elements left to construct the subtree.
    if (preStart > preEnd || inStart > inEnd) {
      return null;
    }

    // First element in preorder is always the root.
    int rootVal = preorder[preStart];
    TreeNode root = new TreeNode(rootVal);

    // Find the root's position in inorder.
    int inPos = map.get(rootVal);

    // Number of nodes in the left subtree.
    int leftSize = inPos - inStart;

    // Build the left subtree.
    root.left = buildTreeHelper(
      preorder,
      preStart + 1,
      preStart + leftSize,
      inStart,
      inPos - 1,
      map
    );

    // Build the right subtree.
    root.right = buildTreeHelper(
      preorder,
      preStart + leftSize + 1,
      preEnd,
      inPos + 1,
      inEnd,
      map
    );

    return root;
  }

  // Time: O(n)
  // Space: O(n) for HashMap + O(h) recursion stack
}
