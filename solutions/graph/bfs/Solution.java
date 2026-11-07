class Solution {
  public ArrayList<Integer> bfs(ArrayList<ArrayList<Integer>> adj) {
    int vertices = adj.size();
    ArrayList<Integer> ans = new ArrayList<>();
    boolean[] visited = new boolean[vertices];
    Queue<Integer> queue = new ArrayDeque<>();

    queue.offer(0);
    visited[0] = true;

    while (!queue.isEmpty()) {
      int node = queue.poll();
      ans.add(node);

      for (int neighbor : adj.get(node)) {
        if (!visited[neighbor]) {
          queue.offer(neighbor);
          visited[neighbor] = true;
        }
      }
    }

    return ans;
  }
}

// Time: O(V + E)
// Space: O(V)