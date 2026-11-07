// DFS
class Solution {
  public Node cloneGraph(Node node) {
    Map<Node, Node> oldToNew = new HashMap<>();
    return dfs(node, oldToNew);
  }

  private Node dfs(Node node, Map<Node, Node> oldToNew) {
    if (node == null) {
      return null;
    }

    // Return the existing clone to handle cycles.
    if (oldToNew.containsKey(node)) {
      return oldToNew.get(node);
    }

    // Create and store the clone before visiting neighbors.
    Node copy = new Node(node.val);
    oldToNew.put(node, copy);

    // Clone all neighbors and connect them.
    for (Node neighbor : node.neighbors) {
      copy.neighbors.add(dfs(neighbor, oldToNew));
    }

    return copy;
  }
}

// BFS
/*
class Solution {
  public Node cloneGraph(Node node) {
    if (node == null) {
      return null;
    }

    Map<Node, Node> oldToNew = new HashMap<>();
    Queue<Node> queue = new ArrayDeque<>();

    Node copy = new Node(node.val);
    oldToNew.put(node, copy);
    queue.offer(node);

    while (!queue.isEmpty()) {
      Node current = queue.poll();

      // Clone neighbors and connect them to the current clone.
      for (Node neighbor : current.neighbors) {
        if (!oldToNew.containsKey(neighbor)) {
          oldToNew.put(neighbor, new Node(neighbor.val));
          queue.offer(neighbor);
        }

        oldToNew.get(current).neighbors.add(oldToNew.get(neighbor));
      }
    }

    return copy;
  }
}
*/

// Time: O(V + E)
// Space: O(V)