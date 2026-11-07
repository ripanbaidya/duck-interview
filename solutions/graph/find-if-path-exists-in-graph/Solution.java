class Solution {
  public boolean validPath(int n, int[][] edges, int source, int destination) {
    List<List<Integer>> adj = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      adj.add(new ArrayList<>());
    }

    for (int[] edge : edges) {
      adj.get(edge[0]).add(edge[1]);
      adj.get(edge[1]).add(edge[0]);
    }

    boolean[] visited = new boolean[n];
    boolean[] found = new boolean[2];

    dfs(source, adj, visited, source, destination, found);

    return found[1];
  }

  private void dfs(int node, List<List<Integer>> adj, boolean[] visited, 
    int source, int destination, boolean[] found) {
    
    visited[node] = true;

    if (node == source) 
      found[0] = true;
    if (node == destination) 
      found[1] = true;

    for (int neighbor : adj.get(node)) {
      if (!visited[neighbor]) {
        dfs(neighbor, adj, visited, source, destination, found);
      }
    }
  }
}

// Time: O(V + E)
// Space: O(V + E)