class Solution {
  
  public boolean isBipartite(int[][] graph) {
    int v = graph.length;
    int[] color = new int[v];
    Arrays.fill(color, -1);

    // Handle disconnected components
    for (int i = 0; i < v; i++) {
      if (color[i] == -1 && !bfs(i, graph, color)) {
        return false;
      }
    }
    return true;
  }

  private boolean bfs(int start, int[][] graph, int[] color) {
    Queue<Integer> queue = new ArrayDeque<>();
    queue.offer(start);
    color[start] = 0;

    while (!queue.isEmpty()) {
      int node = queue.poll();
      int nextColor = 1 - color[node];

      for (int next : graph[node]) {
        if (color[next] == -1) {
          color[next] = nextColor;
          queue.offer(next);
        } else if (color[next] == color[node]) {
          // Same-colored adjacent nodes violate bipartiteness
          return false;
        }
      }
    }
    return true;
  }

  // Time: O(V + E)
  // Space: O(V + E) for color array, queue, and graph storage
}