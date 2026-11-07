class Tuple {
  int row;
  int col;
  TreeNode node;

  Tuple(int row, int col, TreeNode node) {
    this.row = row;
    this.col = col;
    this.node = node;
  }
}

class Solution {
  
  public List<List<Integer>> verticalTraversal(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) 
      return result;

    // col -> (row -> min-heap of values for same position tie-breaking)
    TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
    Queue<Tuple> queue = new LinkedList<>();
    queue.offer(new Tuple(0, 0, root));

    while (!queue.isEmpty()) {
      int size = queue.size();

      for (int i = 0; i < size; i++) {
        Tuple curr = queue.poll();
        
        if (!map.containsKey(curr.col)) {
          map.put(curr.col, new TreeMap<>());
        }
        if (!map.get(curr.col).containsKey(curr.row)) {
          map.get(curr.col).put(curr.row, new PriorityQueue<>());
        }
        map.get(curr.col).get(curr.row).offer(curr.node.val);

        if (curr.node.left != null) {
          queue.offer(new Tuple(curr.row + 1, curr.col - 1, curr.node.left));
        }
        if (curr.node.right != null) {
          queue.offer(new Tuple(curr.row + 1, curr.col + 1, curr.node.right));
        }
      }
    }

    // Extract values ordered by column, then row, then value
    for (TreeMap<Integer, PriorityQueue<Integer>> rowMap : map.values()) {
      List<Integer> colList = new ArrayList<>();
      for (PriorityQueue<Integer> heap : rowMap.values()) {
        while (!heap.isEmpty()) {
          colList.add(heap.poll());
        }
      }
      result.add(colList);
    }

    return result;
  }

  // Time: O(N log N)
  // Space: O(N)
}
