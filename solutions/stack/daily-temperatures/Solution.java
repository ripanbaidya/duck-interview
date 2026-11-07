class Solution {
  
  public int[] dailyTemperatures(int[] t) {
    int n = t.length;
    int[] ans = new int[n];

    Deque<Integer> stack = new ArrayDeque<>();
    for (int i = 0; i < n; i++) {
      // Resolve earlier days waiting for a warmer temperature
      while (!stack.isEmpty() && t[i] > t[stack.peek()]) {
        int j = stack.pop();
        ans[j] = i - j;
      }
      stack.push(i);
    }

    return ans;
  }

  // Time: O(n)
  // Space: O(n)
}