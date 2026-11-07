class Solution {
  public ArrayList<ArrayList<Integer>> getComponents(int n, int[][] edges) {
    ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      adj.add(new ArrayList<>());
    }

    for (int[] edge : edges) {
      adj.get(edge[0]).add(edge[1]);
      adj.get(edge[1]).add(edge[0]);
    }

    ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
    boolean[] visited = new boolean[n];

    for (int i = 0; i < n; i++) {
      if (!visited[i]) {
        ArrayList<Integer> component = new ArrayList<>();
        dfs(i, adj, visited, component);
        ans.add(component);
      }
    }

    return ans;
  }

  private void dfs(int node, ArrayList<ArrayList<Integer>> adj, 
    boolean[] visited, ArrayList<Integer> component) {
    
    visited[node] = true;
    component.add(node);

    for (int neighbor : adj.get(node)) {
      if (!visited[neighbor]) {
        dfs(neighbor, adj, visited, component);
      }
    }
  }
}

// Time: O(V + E)
// Space: O(V + E)