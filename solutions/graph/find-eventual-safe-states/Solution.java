class Solution {
  
  public List<Integer> eventualSafeNodes(int[][] graph) {
    int n = graph.length;
    List<List<Integer>> rev = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      rev.add(new ArrayList<>());
    }

    int[] outDegree = new int[n];
    for (int i = 0; i < n; i++) {
      for (int next : graph[i]) {
        rev.get(next).add(i);
      }
      outDegree[i] = graph[i].length;
    }

    Queue<Integer> q = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      if (outDegree[i] == 0) {
        q.add(i);
      }
    }

    boolean[] safe = new boolean[n];
    while (!q.isEmpty()) {
      int curr = q.poll();
      safe[curr] = true;
      for (int prev : rev.get(curr)) {
        outDegree[prev]--;
        if (outDegree[prev] == 0) {
          q.add(prev);
        }
      }
    }

    List<Integer> res = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      if (safe[i]) {
        res.add(i);
      }
    }
    return res;
  }

  // Time: O(V + E)
  // Space: O(V + E)
}