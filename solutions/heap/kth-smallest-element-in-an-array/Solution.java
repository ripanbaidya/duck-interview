class Solution {

  public int findKthSmallest(int[] nums, int k) {
    PriorityQueue<Integer> maxHeap =
      new PriorityQueue<>(Collections.reverseOrder());

    for (int val : nums) {
      maxHeap.offer(val);

      if (maxHeap.size() > k) {
        maxHeap.poll();
      }
    }

    return maxHeap.peek();
  }

  // Time: O(n log k)
  // Space: O(k)
}