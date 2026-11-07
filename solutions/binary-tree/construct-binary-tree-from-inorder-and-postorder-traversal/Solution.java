class Solution {

  public TreeNode buildTree(int[] inorder, int[] postorder) {
    if (inorder.length != postorder.length) {
      return null;
    }

    int n = inorder.length;

    // Store each value's index in inorder for O(1) average lookup.
    Map<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < n; i++) {
      map.put(inorder[i], i);
    }

    return buildTreeHelper(
      postorder, 0, n - 1,
      0, n - 1, map
    );
  }

  private TreeNode buildTreeHelper(
      int[] postorder, int postStart, int postEnd,
      int inStart, int inEnd,
      Map<Integer, Integer> map) {

    // No elements left to construct the subtree.
    if (postStart > postEnd || inStart > inEnd) {
      return null;
    }

    // Last element in postorder is always the root.
    int rootVal = postorder[postEnd];
    TreeNode root = new TreeNode(rootVal);

    // Find the root's position in inorder.
    int inPos = map.get(rootVal);

    // Number of nodes in the left subtree.
    int leftSize = inPos - inStart;

    // Build the left subtree.
    root.left = buildTreeHelper(
      postorder,
      postStart,
      postStart + leftSize - 1,
      inStart,
      inPos - 1,
      map
    );

    // Build the right subtree.
    root.right = buildTreeHelper(
      postorder,
      postStart + leftSize,
      postEnd - 1,
      inPos + 1,
      inEnd,
      map
    );

    return root;
  }

  // Time: O(n)
  // Space: O(n) for HashMap + O(h) recursion stack
}
