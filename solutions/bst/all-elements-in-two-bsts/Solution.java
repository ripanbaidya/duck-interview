class Solution {

  public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
    List<Integer> list1 = new ArrayList<>();
    List<Integer> list2 = new ArrayList<>();
    inorder(root1, list1);
    inorder(root2, list2);

    List<Integer> res = new ArrayList<>();
    int i = 0, j = 0;
    while (i < list1.size() && j < list2.size()) {
      if (list1.get(i) <= list2.get(j)) res.add(list1.get(i++));
      else res.add(list2.get(j++));
    }
    while (i < list1.size()) res.add(list1.get(i++));
    while (j < list2.size()) res.add(list2.get(j++));
    return res;
  }

  private void inorder(TreeNode node, List<Integer> list) {
    if (node == null) return;
    inorder(node.left, list);
    list.add(node.val);
    inorder(node.right, list);
  }

  // Time: O(m + n)
  // Space: O(m + n)
}