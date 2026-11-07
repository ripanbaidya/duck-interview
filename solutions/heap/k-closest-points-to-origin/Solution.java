class Pair {
  long dist;
  int[] point;

  Pair(long dist, int[] point) {
    this.dist = dist;
    this.point = point;
  }
}

class Solution {

  public int[][] kClosest(int[][] points, int k) {
    PriorityQueue<Pair> maxHeap = new PriorityQueue<>(
      (a, b) -> Long.compare(b.dist, a.dist)
    );

    for (int[] point : points) {
      long dist = calcDistance(point[0], point[1]);
      maxHeap.offer(new Pair(dist, point));

      if (maxHeap.size() > k) {
        maxHeap.poll();
      }
    }

    int[][] ans = new int[k][2];

    for (int i = 0; i < k; i++) {
      ans[i] = maxHeap.poll().point;
    }

    return ans;
  }

  private long calcDistance(int x, int y) {
    return (long) x * x + (long) y * y;
  }

  // Time: O(n log k)
  // Space: O(k)
}