class Pair {
  Node node;
  int col;

  public Pair(Node node, int col) {
    this.node = node;
    this.col = col;
  }
}

class Solution {
  public List<Integer> bottomView(Node root) {
    List<Integer> ans = new ArrayList<>();
    if (root == null) 
      return ans;

    Queue<Pair> q = new LinkedList<>();
    TreeMap<Integer, Integer> map = new TreeMap<>();

    q.offer(new Pair(root, 0));

    while (!q.isEmpty()) {
      Pair curr = q.poll();
      Node node = curr.node;
      int col = curr.col;

      // Overwrite to keep the bottommost node at each column.
      map.put(col, node.data);

      if (node.left != null) {
        q.offer(new Pair(node.left, col - 1));
      }

      if (node.right != null) {
        q.offer(new Pair(node.right, col + 1));
      }
    }

    for (int val : map.values()) {
      ans.add(val);
    }

    return ans;
  }

  // Time: O(N log N)
  // Space: O(N)
}