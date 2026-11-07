class Solution {
  public int[] findOrder(int numCourses, int[][] prerequisites) {
    List<List<Integer>> adj = new ArrayList<>();
    for (int i = 0; i < numCourses; i++) {
      adj.add(new ArrayList<>());
    }

    int[] inDegree = new int[numCourses];
    for (int[] p : prerequisites) {
      adj.get(p[1]).add(p[0]);
      inDegree[p[0]]++;
    }

    Queue<Integer> q = new LinkedList<>();
    for (int i = 0; i < numCourses; i++) {
      if (inDegree[i] == 0) {
        q.add(i);
      }
    }

    int[] order = new int[numCourses];
    int idx = 0;

    while (!q.isEmpty()) {
      int curr = q.poll();
      order[idx++] = curr;
      
      for (int next : adj.get(curr)) {
        inDegree[next]--;
        if (inDegree[next] == 0) {
          q.add(next);
        }
      }
    }

    return idx == numCourses ? order : new int[0];
  }

  // Time: O(V + E) where V is numCourses and E is prerequisites.length
  // Space: O(V + E) for the adjacency list and queue
}