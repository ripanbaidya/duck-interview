class Solution {
  public ArrayList<Integer> dfs(ArrayList<ArrayList<Integer>> adj) {
    int vertices = adj.size();
    ArrayList<Integer> ans = new ArrayList<>();
    boolean[] visited = new boolean[vertices];

    helper(0, adj, ans, visited);
    return ans;
  }

  private void helper(int node, ArrayList<ArrayList<Integer>> adj, ArrayList<Integer> ans, boolean[] visited) {
    visited[node] = true;
    ans.add(node);

    for (int neighbor : adj.get(node)) {
      if (!visited[neighbor]) {
        helper(neighbor, adj, ans, visited);
      }
    }
  }
}

// Time: O(V + E)
// Space: O(V)