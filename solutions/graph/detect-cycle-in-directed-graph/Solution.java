class Solution {
  // DFS
  public boolean isCyclic(int V, int[][] edges) {
    List<List<Integer>> adj = new ArrayList<>();
    for (int i = 0; i < V; i++) {
      adj.add(new ArrayList<>());
    }
    for (int[] edge : edges) {
      adj.get(edge[0]).add(edge[1]);
    }

    boolean[] vis = new boolean[V];
    // Nodes currently on the DFS recursion path
    boolean[] pathVis = new boolean[V];

    for (int i = 0; i < V; i++) {
      if (!vis[i] && dfs(i, vis, pathVis, adj)) {
        return true;
      }
    }
    return false;
  }

  private boolean dfs(int node, boolean[] vis, boolean[] pathVis,
      List<List<Integer>> adj) {
    vis[node] = true;
    pathVis[node] = true;

    for (int next : adj.get(node)) {
      if (!vis[next]) {
        if (dfs(next, vis, pathVis, adj)) {
          return true;
        }
      } else if (pathVis[next]) {
        // A back edge to a node on the current path forms a cycle
        return true;
      }
    }

    pathVis[node] = false;
    return false;
  }

  // Time: O(V + E)
  // Space: O(V + E) for adjacency list, visited/path arrays, and recursion stack

  // BFS/ Khan's Algorithm

  /*
  public boolean isCyclic(int V, int[][] edges) {
    List<List<Integer>> adj = new ArrayList<>();
    for (int i = 0; i < V; i++) {
      adj.add(new ArrayList<>());
    }

    int[] inDegree = new int[V];
    for (int[] edge : edges) {
      adj.get(edge[0]).add(edge[1]);
      inDegree[edge[1]]++;
    }

    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < V; i++) {
      if (inDegree[i] == 0) {
        queue.add(i);
      }
    }

    int processed = 0;
    while (!queue.isEmpty()) {
      int node = queue.poll();
      processed++;

      for (int next : adj.get(node)) {
        inDegree[next]--;
        if (inDegree[next] == 0) {
          queue.add(next);
        }
      }
    }

    // A cycle prevents some nodes from ever reaching in-degree 0
    return processed != V;
  }
  */

  // Time: O(V + E)
  // Space: O(V + E) for adjacency list, in-degree array, and queue

}