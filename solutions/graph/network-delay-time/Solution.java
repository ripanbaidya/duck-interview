class Solution {
  
  // Use Dijkstra's algorithm with a min-heap to find the 
  // shortest path from the source node to all other nodes.
  public int networkDelayTime(int[][] times, int n, int k) {
    List<List<int[]>> adj = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      adj.add(new ArrayList<>());
    }
    for (int[] t : times) {
      adj.get(t[0]).add(new int[] {t[1], t[2]});
    }

    int[] dist = new int[n + 1];
    Arrays.fill(dist, (int) 1e9);
    dist[k] = 0;

    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
    pq.offer(new int[] {0, k});

    while (!pq.isEmpty()) {
      int[] curr = pq.poll();
      int time = curr[0];
      int u = curr[1];

      if (time > dist[u]) {
        continue;
      }

      for (int[] next : adj.get(u)) {
        int v = next[0];
        int w = next[1];

        if (time + w < dist[v]) {
          dist[v] = time + w;
          pq.offer(new int[] {dist[v], v});
        }
      }
    }

    int max = 0;
    for (int i = 1; i <= n; i++) {
      if (dist[i] == (int) 1e9) {
        return -1;
      }
      max = Math.max(max, dist[i]);
    }

    return max;
  }

  // Time: O(E log V) where E is the number of edges and V is the number of nodes
  // Space: O(V + E) for the adjacency list and priority queue
}