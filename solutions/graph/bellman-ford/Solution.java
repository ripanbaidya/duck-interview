class Solution {
  
  // Use the Bellman-Ford algorithm to find shortest paths from a source 
  // and check for negative weight cycles, with an early-stopping optimization.
  public int[] bellmanFord(int V, int[][] edges, int src) {
    final int INF = 100_000_000;
    int[] dist = new int[V];
    Arrays.fill(dist, INF);
    dist[src] = 0;

    for (int i = 0; i < V - 1; i++) {
      boolean updated = false;

      for (int[] e : edges) {
        int u = e[0];
        int v = e[1];
        int wt = e[2];

        if (dist[u] != INF && dist[v] > dist[u] + wt) {
          dist[v] = dist[u] + wt;
          updated = true;
        }
      }

      if (!updated) {
        break;
      }
    }

    for (int[] e : edges) {
      int u = e[0];
      int v = e[1];
      int wt = e[2];

      if (dist[u] != INF && dist[v] > dist[u] + wt) {
        return new int[] {-1};
      }
    }

    return dist;
  }

  // Time: O(V * E) where V is the number of vertices and E is the number of edges
  // Space: O(V) for the distance array
}