class Solution {
  // BFS
  public boolean isCycle(int V, List<List<Integer>> adj) {
    boolean[] vis = new boolean[V];

    // Handle disconnected components
    for (int i = 0; i < V; i++) {
      if (!vis[i] && hasCycle(i, adj, vis)) {
        return true;
      }
    }
    return false;
  }

  private boolean hasCycle(int src, List<List<Integer>> adj, boolean[] vis) {
    // Queue stores {node, parent}
    Queue<int[]> queue = new LinkedList<>();
    queue.add(new int[]{src, -1});
    vis[src] = true;

    while (!queue.isEmpty()) {
      int[] cur = queue.poll();
      int node = cur[0], parent = cur[1];

      for (int next : adj.get(node)) {
        if (!vis[next]) {
          vis[next] = true;
          queue.add(new int[]{next, node});
        } else if (next != parent) {
          // Visited neighbor that is not the parent closes a cycle
          return true;
        }
      }
    }
    return false;
  }

  // Time: O(V + E)
  // Space: O(V + E) for visited array, queue, and adjacency list storage

  // DFS
  /*
  public boolean isCycle(int V, List<List<Integer>> adj) {
    boolean[] vis = new boolean[V];

    // Handle disconnected components
    for (int i = 0; i < V; i++) {
      if (!vis[i] && hasCycle(i, -1, adj, vis)) {
        return true;
      }
    }
    return false;
  }

  private boolean hasCycle(int node, int parent, List<List<Integer>> adj, boolean[] vis) {
    vis[node] = true;

    for (int next : adj.get(node)) {
      if (!vis[next]) {
        if (hasCycle(next, node, adj, vis)) {
          return true;
        }
      } else if (next != parent) {
        // A visited neighbor other than the parent closes a cycle
        return true;
      }
    }
    return false;
  }
  */

  // Time: O(V + E)
  // Space: O(V + E) for visited array, recursion stack, and adjacency list storage

}