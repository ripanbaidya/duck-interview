class Solution {
  
  public int findTheCity(int n, int[][] edges, int distanceThreshold) {
    int[][] dist = new int[n][n];
    int inf = (int) 1e9;

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        dist[i][j] = (i == j) ? 0 : inf;
      }
    }

    for (int[] edge : edges) {
      int u = edge[0], v = edge[1], wt = edge[2];
      dist[u][v] = wt;
      dist[v][u] = wt;
    }

    // Floyd-Warshall algorithm for all-pairs shortest paths
    for (int k = 0; k < n; k++) {
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
        }
      }
    }

    int minReachable = Integer.MAX_VALUE;
    int resultCity = -1;

    for (int i = 0; i < n; i++) {
      int reachableCount = 0;
      for (int j = 0; j < n; j++) {
        if (i != j && dist[i][j] <= distanceThreshold) {
          reachableCount++;
        }
      }

      // Use <= to ensure the city with the greatest index is chosen on ties
      if (reachableCount <= minReachable) {
        minReachable = reachableCount;
        resultCity = i;
      }
    }

    return resultCity;
  }

  // Time: O(N^3)
  // Space: O(N^2)
}