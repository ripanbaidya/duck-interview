class Solution {
  
  public int[] maxSlidingWindow(int[] nums, int k) {
    // Descending order based on value
    PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);

    int[] res = new int[nums.length - k + 1];
    int idx = 0;

    for (int i = 0; i < nums.length; i++) {
      maxHeap.offer(new int[] { nums[i], i });

      // Remove elements outside the current window
      while (maxHeap.peek()[1] <= i - k) {
        maxHeap.poll();
      }

      if (i >= k - 1) {
        res[idx++] = maxHeap.peek()[0];
      }
    }

    return res;
  }

  // Time: O(n log n)
  // Space: O(n)
}
``