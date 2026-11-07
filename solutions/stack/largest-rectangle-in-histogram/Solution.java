class Solution {
  
  public int largestRectangleArea(int[] heights) {
    int n = heights.length;
    int[] prevSmaller = new int[n];
    int[] nextSmaller = new int[n];
    Arrays.fill(nextSmaller, n);

    Deque<Integer> stack = new ArrayDeque<>();

    for (int i = 0; i < n; i++) {
      // Keep indices with strictly smaller heights
      while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
        stack.pop();
      }
      prevSmaller[i] = stack.isEmpty() ? -1 : stack.peek();
      stack.push(i);
    }

    stack.clear();

    for (int i = 0; i < n; i++) {
      // Resolve bars whose next smaller height is heights[i]
      while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
        nextSmaller[stack.pop()] = i;
      }
      stack.push(i);
    }

    int maxArea = 0;
    for (int i = 0; i < n; i++) {
      int width = nextSmaller[i] - prevSmaller[i] - 1;
      maxArea = Math.max(maxArea, heights[i] * width);
    }

    return maxArea;
  }

  // Time: O(n)
  // Space: O(n)
}