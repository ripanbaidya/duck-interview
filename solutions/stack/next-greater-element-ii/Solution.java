class Solution {
  
  public int[] nextGreaterElements(int[] nums) {
    int n = nums.length;
    int[] nge = new int[n];
    Arrays.fill(nge, -1);

    Deque<Integer> stack = new ArrayDeque<>();
    // Traverse twice to account for the circular array
    for (int i = 0; i < 2 * n; i++) {
      int idx = i % n;

      while (!stack.isEmpty() && nums[idx] > nums[stack.peek()]) {
        nge[stack.pop()] = nums[idx];
      }

      if (i < n) stack.push(idx);
    }

    return nge;
  }

  // Time: O(n)
  // Space: O(n)
}