class Solution {
  public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    dfs(0, graph, ans, path);
    return ans;
  }

  private void dfs(int node, int[][] graph, List<List<Integer>> ans, List<Integer> path) {
    path.add(node);

    // Store the path when the destination is reached.
    if (node == graph.length - 1) {
      ans.add(new ArrayList<>(path));
    } else {
      for (int neighbor : graph[node]) {
        dfs(neighbor, graph, ans, path);
      }
    }

    // Backtrack to explore other possible paths.
    path.remove(path.size() - 1);
  }
}

// Time: O(2^V * V) 
// Space: O(V) auxiliary, excluding output