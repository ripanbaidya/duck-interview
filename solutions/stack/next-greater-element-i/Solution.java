class Solution {
  
  public int[] nextGreaterElement(int[] nums1, int[] nums2) {
    int n = nums1.length, m = nums2.length;
    Deque<Integer> stack = new ArrayDeque<>();
    Map<Integer, Integer> nextGreater = new HashMap<>();

    for (int i = 0; i < m; i++) {
      // Resolve indices whose next greater element is nums2[i]
      while (!stack.isEmpty() && nums2[i] > nums2[stack.peek()]) {
        int idx = stack.pop();
        nextGreater.put(nums2[idx], nums2[i]);
      }
      stack.push(i);
    }

    int[] ans = new int[n];
    for (int i = 0; i < n; i++) {
      ans[i] = nextGreater.getOrDefault(nums1[i], -1);
    }
    return ans;
  }

  // Time: O(n + m)
  // Space: O(m)
}