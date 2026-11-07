class Solution {
  
  // Use a modified Dijkstra's algorithm to find the path that minimizes 
  // the maximum absolute difference between adjacent cells (bottleneck path).
  public int minimumEffortPath(int[][] heights) {
    int rows = heights.length;
    int cols = heights[0].length;
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    int[][] dist = new int[rows][cols];
    for (int[] row : dist) {
      Arrays.fill(row, Integer.MAX_VALUE);
    }
    dist[0][0] = 0;

    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
    pq.offer(new int[] {0, 0, 0});

    while (!pq.isEmpty()) {
      int[] curr = pq.poll();
      int effort = curr[0];
      int r = curr[1];
      int c = curr[2];

      if (r == rows - 1 && c == cols - 1) {
        return effort;
      }

      if (effort > dist[r][c]) {
        continue;
      }

      for (int[] dir : dirs) {
        int nr = r + dir[0];
        int nc = c + dir[1];

        if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
          int nextEffort = Math.max(effort, Math.abs(heights[r][c] - heights[nr][nc]));

          if (nextEffort < dist[nr][nc]) {
            dist[nr][nc] = nextEffort;
            pq.offer(new int[] {nextEffort, nr, nc});
          }
        }
      }
    }

    return 0;
  }

  // Time: O(R * C * log(R * C)) where R is rows and C is columns
  // Space: O(R * C) for the distance matrix and priority queue
}