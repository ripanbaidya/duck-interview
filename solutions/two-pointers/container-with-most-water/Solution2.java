// Brute force

class Solution {
  public int maxArea(int[] heights) {
    int n = heights.length;
    int maxArea = Integer.MIN_VALUE;

    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        int area = (j - i) * min(heights[i], heights[j]);
        maxArea = max(maxArea, area);
      }
    }

    return maxArea;
  }
}

// Time: O(n²)
// Space: O(1)