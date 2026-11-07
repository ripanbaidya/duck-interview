class Solution {
  
  public List<Integer> leftView(Node root) {
    List<Integer> res = new ArrayList<>();
    if (root == null) {
      return res;
    }

    Queue<Node> q = new LinkedList<>();
    q.offer(root);

    while (!q.isEmpty()) {
      int size = q.size();

      for (int i = 0; i < size; i++) {
        Node curr = q.poll();

        if (i == 0) {
          res.add(curr.data);
        }

        if (curr.left != null) {
          q.offer(curr.left);
        }
        if (curr.right != null) {
          q.offer(curr.right);
        }
      }
    }

    return res;
  }

  // Time: O(N)
  // Space: O(W) where W is the maximum width of the tree
}