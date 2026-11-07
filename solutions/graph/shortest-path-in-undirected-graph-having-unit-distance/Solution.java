class Solution {
  // Use BFS to find the shortest path in an unweighted graph, 
  // returning the distance the first time the destination is reached.
  public int shortestPath(int V, int[][] edges, int src, int dest) {
    List<List<Integer>> adj = new ArrayList<>();
    for (int i = 0; i < V; i++) {
      adj.add(new ArrayList<>());
    }
    for (int[] e : edges) {
      adj.get(e[0]).add(e[1]);
      adj.get(e[1]).add(e[0]);
    }

    boolean[] visited = new boolean[V];
    Queue<int[]> q = new LinkedList<>();

    q.offer(new int[] {src, 0});
    visited[src] = true;

    while (!q.isEmpty()) {
      int[] curr = q.poll();
      int node = curr[0];
      int dist = curr[1];

      if (node == dest) {
        return dist;
      }

      for (int next : adj.get(node)) {
        if (!visited[next]) {
          visited[next] = true;
          q.offer(new int[] {next, dist + 1});
        }
      }
    }

    return -1;
  }

  // Time: O(V + E)
  // Space: O(V + E)
}