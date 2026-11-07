class Solution {
  public int isTree(int n, int m, int[][] edges) {
    // A tree with n vertices must have exactly n - 1 edges.
    if (m != n - 1) {
      return 0;
    }

    List<List<Integer>> adj = new ArrayList<>();
    
    for (int i = 0; i < n; i++) {
      adj.add(new ArrayList<>());
    }

    for (int[] edge : edges) {
      adj.get(edge[0]).add(edge[1]);
      adj.get(edge[1]).add(edge[0]);
    }

    boolean[] visited = new boolean[n];

    // A tree must be cycle-free.
    if (hasCycleDFS(0, -1, visited, adj)) {
      return 0;
    }

    // A tree must also be connected.
    for (boolean isVisited : visited) {
      if (!isVisited) {
        return 0;
      }
    }

    return 1;
  }

  private boolean hasCycleDFS(int node, int parent, boolean[] visited, List<List<Integer>> adj) {
    visited[node] = true;

    for (int neighbor : adj.get(node)) {
      if (!visited[neighbor]) {
        if (hasCycleDFS(neighbor, node, visited, adj)) {
          return true;
        }
      } else if (neighbor != parent) {
        // A visited neighbor other than the parent indicates a cycle.
        return true;
      }
    }

    return false;
  }
}

// Time: O(V + E)
// Space: O(V + E)