class Solution {
  
  // Use the Floyd-Warshall algorithm to find all-pairs shortest paths 
  // in-place by iteratively updating distances using each vertex as an intermediate node.
  public void floydWarshall(int[][] dist) {
    int n = dist.length;
    final int INF = 100_000_000;

    for (int k = 0; k < n; k++) {
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          if (dist[i][k] != INF && dist[k][j] != INF) {
            dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
          }
        }
      }
    }

    // Follow-up: Detection of Negative Weight Cycles
    /*
    for (int i = 0; i < n; i++) {
      if (dist[i][i] < 0) {
        // Graph contains a negative weight cycle involving node: 'i'
      }
    }
    */
  }

  // Time: O(V^3) where V is the number of vertices
  // Space: O(1) as distances are updated in-place
}