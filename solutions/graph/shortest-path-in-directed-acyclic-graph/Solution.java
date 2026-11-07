class Solution {
  
  // Use Dijkstra's algorithm with a min-heap to find the shortest path 
  // from source vertex 0 to all other vertices, replacing unreachable nodes with -1.
  public int[] shortestPath(int V, int E, int[][] edges) {
    List<List<int[]>> adj = new ArrayList<>();
    for (int i = 0; i < V; i++) {
      adj.add(new ArrayList<>());
    }
    for (int[] e : edges) {
      adj.get(e[0]).add(new int[] {e[1], e[2]});
    }

    int[] dist = new int[V];
    Arrays.fill(dist, (int) 1e9);
    dist[0] = 0;

    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
    pq.offer(new int[] {0, 0});

    while (!pq.isEmpty()) {
      int[] curr = pq.poll();
      int d = curr[0];
      int u = curr[1];

      if (d > dist[u]) {
        continue;
      }

      for (int[] next : adj.get(u)) {
        int v = next[0];
        int w = next[1];

        if (dist[u] + w < dist[v]) {
          dist[v] = dist[u] + w;
          pq.offer(new int[] {dist[v], v});
        }
      }
    }

    for (int i = 0; i < V; i++) {
      if (dist[i] == (int) 1e9) {
        dist[i] = -1;
      }
    }

    return dist;
  }

  // Time: O(E log V) where E is the number of edges and V is the number of vertices
  // Space: O(V + E) for the adjacency list and priority queue
}