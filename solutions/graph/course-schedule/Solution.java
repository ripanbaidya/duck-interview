class Solution {
  
  public boolean canFinish(int numCourses, int[][] prerequisites) {
    List<List<Integer>> adj = new ArrayList<>();
    for (int i = 0; i < numCourses; i++) {
      adj.add(new ArrayList<>());
    }

    int[] inDegree = new int[numCourses];
    for (int[] prereq : prerequisites) {
      int course = prereq[0];
      int required = prereq[1];
      adj.get(required).add(course);
      inDegree[course]++;
    }

    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < numCourses; i++) {
      if (inDegree[i] == 0) {
        queue.add(i);
      }
    }

    int processed = 0;
    while (!queue.isEmpty()) {
      int course = queue.poll();
      processed++;

      for (int next : adj.get(course)) {
        inDegree[next]--;
        if (inDegree[next] == 0) {
          queue.add(next);
        }
      }
    }

    // A prerequisite cycle leaves some courses unprocessed
    return processed == numCourses;
  }

  // Time: O(V + E)
  // Space: O(V + E) for adjacency list, in-degree array, and queue
}