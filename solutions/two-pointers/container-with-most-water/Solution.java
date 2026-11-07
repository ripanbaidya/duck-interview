// Optimal

class Solution {
  public int maxArea(int[] heights) {
    int left = 0, right = heights.length - 1;
    int maxArea = Integer.MIN_VALUE;

    while (left < right) {
      int area = (right - left) * min(heights[left], heights[right]);
      maxArea = max(maxArea, area);

      if (heights[left] < heights[right]) {
        left++;
      } else {
        right--;
      }
    }

    return maxArea;
  }
}

// Time: O(n)
// Space: O(1)