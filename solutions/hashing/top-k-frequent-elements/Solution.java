class Solution {
  public int[] topKFrequent(int[] nums, int k) {
    Map<Integer, Integer> count = new HashMap<>();

    // Count the frequency of each number
    for (int n : nums) {
      count.put(n, count.getOrDefault(n, 0) + 1);
    }

    // Min-heap ordered by frequency
    PriorityQueue<Integer> heap = new PriorityQueue<>(
        (a, b) -> count.get(a) - count.get(b));

    // Keep only the k most frequent elements
    for (int n : count.keySet()) {
      heap.offer(n);

      if (heap.size() > k) {
        heap.poll();
      }
    }

    // Build the result array
    int[] top = new int[k];
    for (int i = 0; i < k; i++) {
      top[i] = heap.poll();
    }

    return top;
  }
}

// Time: O(n+nlogk) if k<n and O(1) in the particular case of n=k.
// Space: O(n+k) to store the hash map with not more n elements and a heap with k elements.