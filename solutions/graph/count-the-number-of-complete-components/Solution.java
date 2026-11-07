class Solution {
  public int countCompleteComponents(int n, int[][] edges) {
    // Build the adjacency list.
    List<List<Integer>> adj = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      adj.add(new ArrayList<>());
    }

    for (int[] edge : edges) {
      adj.get(edge[0]).add(edge[1]);
      adj.get(edge[1]).add(edge[0]);
    }

    boolean[] visited = new boolean[n];
    int count = 0;

    for (int i = 0; i < n; i++) {
      if (!visited[i]) {
        int[] totalNodes = new int[1];
        int[] totalEdges = new int[1];

        dfs(i, adj, visited, totalNodes, totalEdges);

        // Each node must connect to every other node.
        int expectedEdges = totalNodes[0] * (totalNodes[0] - 1);

        if (totalEdges[0] == expectedEdges) {
          count++;
        }
      }
    }

    return count;
  }

  private void dfs(int node, List<List<Integer>> adj, boolean[] visited,
      int[] totalNodes, int[] totalEdges) {
    visited[node] = true;
    totalNodes[0]++;
    totalEdges[0] += adj.get(node).size();

    // Explore all unvisited neighbors.
    for (int neighbor : adj.get(node)) {
      if (!visited[neighbor]) {
        dfs(neighbor, adj, visited, totalNodes, totalEdges);
      }
    }
  }
}

// Time: O(V + E)
// Space: O(V + E)