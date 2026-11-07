// DFS
class Solution {
  public int findCircleNum(int[][] isConnected) {
    int n = isConnected.length;
    boolean[] visited = new boolean[n];
    int count = 0;

    for (int i = 0; i < n; i++) {
      if (!visited[i]) {
        dfs(i, isConnected, visited);
        count++;
      }
    }

    return count;
  }

  private void dfs(int node, int[][] isConnected, boolean[] visited) {
    visited[node] = true;

    for (int neighbor = 0; neighbor < isConnected.length; neighbor++) {
      if (isConnected[node][neighbor] == 1 && !visited[neighbor]) {
        dfs(neighbor, isConnected, visited);
      }
    }
  }
}

// BFS
/*
class Solution {
  public int findCircleNum(int[][] isConnected) {
    int n = isConnected.length;
    boolean[] visited = new boolean[n];
    int count = 0;

    for (int i = 0; i < n; i++) {
      if (!visited[i]) {
        bfs(i, isConnected, visited);
        count++;
      }
    }

    return count;
  }

  private void bfs(int start, int[][] isConnected, boolean[] visited) {
    Queue<Integer> queue = new ArrayDeque<>();
    queue.offer(start);
    visited[start] = true;

    while (!queue.isEmpty()) {
      int node = queue.poll();

      for (int neighbor = 0; neighbor < isConnected.length; neighbor++) {
        if (isConnected[node][neighbor] == 1 && !visited[neighbor]) {
          queue.offer(neighbor);
          visited[neighbor] = true;
        }
      }
    }
  }
}
*/

// Time: O(V²)
// Space: O(V)