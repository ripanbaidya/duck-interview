class Solution {
  public boolean canVisitAllRooms(List<List<Integer>> rooms) {
    int n = rooms.size();
    boolean[] visited = new boolean[n];
    dfs(0, rooms, visited);

    for (boolean room : visited) {
      if (!room) return false;
    }
    return true;
  }

  private void dfs(int room, List<List<Integer>> rooms, boolean[] visited) {
    visited[room] = true;

    for (int key : rooms.get(room)) {
      if (!visited[key]) {
        dfs(key, rooms, visited);
      }
    }
  }

  // Time: O(n + k), where k is the total number of keys
  // Space: O(n + k) for visited, recursion stack, and input storage
}