class Solution {

  private ListNode curr;

  public TreeNode sortedListToBST(ListNode head) {
    int n = 0;
    for (ListNode node = head; node != null; node = node.next) n++;

    curr = head;
    return build(0, n - 1);
  }

  // Builds in inorder, so curr walks the list once with no need to find the middle
  private TreeNode build(int left, int right) {
    if (left > right) return null;

    int mid = left + (right - left) / 2;
    TreeNode leftChild = build(left, mid - 1);

    TreeNode node = new TreeNode(curr.val);
    curr = curr.next;

    node.left = leftChild;
    node.right = build(mid + 1, right);
    return node;
  }

  // Time: O(n)
  // Space: O(log n)
}