class Solution {

  // DFS
  public ArrayList<Integer> topoSort(int V, int[][] edges) {
    List<List<Integer>> adj = new ArrayList<>();
    for (int i = 0; i < V; i++) {
      adj.add(new ArrayList<>());
    }
    for (int[] edge : edges) {
      adj.get(edge[0]).add(edge[1]);
    }

    boolean[] vis = new boolean[V];
    Stack<Integer> stack = new Stack<>();

    // Handle disconnected components
    for (int i = 0; i < V; i++) {
      if (!vis[i]) {
        dfs(i, adj, stack, vis);
      }
    }

    ArrayList<Integer> topo = new ArrayList<>();
    while (!stack.isEmpty()) {
      topo.add(stack.pop());
    }
    return topo;
  }

  private void dfs(int node, List<List<Integer>> adj, Stack<Integer> stack,
      boolean[] vis) {
    vis[node] = true;

    for (int next : adj.get(node)) {
      if (!vis[next]) {
        dfs(next, adj, stack, vis);
      }
    }

    // Push after all dependencies are processed
    stack.push(node);
  }

  // Time: O(V + E)
  // Space: O(V + E) for adjacency list, visited array, stack, and recursion stack

  // BFS/ Khan's Algorithm
  /*
  public ArrayList<Integer> topoSort(int V, int[][] edges) {
    List<List<Integer>> adj = new ArrayList<>();
    int[] inDegree = new int[V];

    for (int i = 0; i < V; i++) {
      adj.add(new ArrayList<>());
    }
    for (int[] edge : edges) {
      adj.get(edge[0]).add(edge[1]);
      inDegree[edge[1]]++;
    }

    Queue<Integer> queue = new ArrayDeque<>();
    for (int i = 0; i < V; i++) {
      if (inDegree[i] == 0) {
        queue.offer(i);
      }
    }

    ArrayList<Integer> topo = new ArrayList<>();
    while (!queue.isEmpty()) {
      int node = queue.poll();
      topo.add(node);

      for (int next : adj.get(node)) {
        inDegree[next]--;
        if (inDegree[next] == 0) {
          queue.offer(next);
        }
      }
    }

    return topo;
  }
  */
 
  // Time: O(V + E)
  // Space: O(V + E) for adjacency list, in-degree array, queue, and result
}