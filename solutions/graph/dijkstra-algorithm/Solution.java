class Solution {
  public int[] dijkstra(int V, int[][] edges, int src) {
    List<List<int[]>> adj = new ArrayList<>();

    for (int i = 0; i < V; i++) {
      adj.add(new ArrayList<>());
    }

    for (int[] edge : edges) {
      int u = edge[0];
      int v = edge[1];
      int weight = edge[2];

      adj.get(u).add(new int[]{v, weight});
      adj.get(v).add(new int[]{u, weight});
    }

    int[] dist = new int[V];
    Arrays.fill(dist, (int) 1e9);

    PriorityQueue<int[]> minHeap =
        new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

    dist[src] = 0;
    minHeap.offer(new int[]{0, src});

    while (!minHeap.isEmpty()) {
      int[] current = minHeap.poll();
      int currDist = current[0];
      int node = current[1];

      if (currDist > dist[node]) {
        continue;
      }

      for (int[] neighbor : adj.get(node)) {
        int nextNode = neighbor[0];
        int weight = neighbor[1];

        int newDist = currDist + weight;

        if (newDist < dist[nextNode]) {
          dist[nextNode] = newDist;
          minHeap.offer(new int[]{newDist, nextNode});
        }
      }
    }

    return dist;
  }

  // Time: O((V + E) log V)
  // Space: O(V + E)
}