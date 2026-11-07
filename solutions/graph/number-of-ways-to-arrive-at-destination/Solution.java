class Solution {
  
  // Use Dijkstra's algorithm with a min-heap to find the shortest path while
  //  tracking the number of ways to reach each node, updating counts with modulo arithmetic.
  public int countPaths(int n, int[][] roads) {
    final int MOD = 1_000_000_007;

    List<List<int[]>> adj = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      adj.add(new ArrayList<>());
    }
    for (int[] r : roads) {
      adj.get(r[0]).add(new int[] {r[1], r[2]});
      adj.get(r[1]).add(new int[] {r[0], r[2]});
    }

    long[] dist = new long[n];
    Arrays.fill(dist, Long.MAX_VALUE);
    int[] ways = new int[n];

    dist[0] = 0;
    ways[0] = 1;

    PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
    pq.offer(new long[] {0, 0});

    while (!pq.isEmpty()) {
      long[] curr = pq.poll();
      long time = curr[0];
      int u = (int) curr[1];

      if (time > dist[u]) {
        continue;
      }

      for (int[] next : adj.get(u)) {
        int v = next[0];
        long weight = next[1];

        if (time + weight < dist[v]) {
          dist[v] = time + weight;
          ways[v] = ways[u];
          pq.offer(new long[] {dist[v], v});
        } else if (time + weight == dist[v]) {
          // Another shortest path found; accumulate path counts modulo MOD
          ways[v] = (ways[v] + ways[u]) % MOD;
        }
      }
    }

    return ways[n - 1];
  }

  // Time: O(E log V) where E is the number of roads and V is the number of nodes
  // Space: O(V + E) for the adjacency list, distance array, ways array, and priority queue
}