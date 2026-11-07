class Solution {
  
  // Use Prim's algorithm with a min-heap to find the Minimum Spanning Tree (MST) 
  // by greedily expanding the tree through the minimum weight edge to unvisited nodes.
  public int spanningTree(int V, int[][] edges) {
    List<List<int[]>> adj = new ArrayList<>();
    for (int i = 0; i < V; i++) {
      adj.add(new ArrayList<>());
    }
    for (int[] e : edges) {
      adj.get(e[0]).add(new int[] {e[1], e[2]});
      adj.get(e[1]).add(new int[] {e[0], e[2]});
    }

    PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
    boolean[] visited = new boolean[V];

    minHeap.offer(new int[] {0, 0});
    int totalWeight = 0;

    while (!minHeap.isEmpty()) {
      int[] curr = minHeap.poll();
      int wt = curr[0];
      int u = curr[1];

      if (visited[u]) {
        continue;
      }

      visited[u] = true;
      totalWeight += wt;

      for (int[] next : adj.get(u)) {
        int v = next[0];
        int weight = next[1];

        if (!visited[v]) {
          minHeap.offer(new int[] {weight, v});
        }
      }
    }

    return totalWeight;
  }

  // Time: O(E log V) where E is the number of edges and V is the number of vertices
  // Space: O(V + E) for the adjacency list and min-heap
}