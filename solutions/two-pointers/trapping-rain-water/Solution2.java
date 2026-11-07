// Brute force

class Solution {
  public int trap(int[] height) {
    int n = height.length;
    if (n == 0) {
      return 0;
    }

    int total = 0;

    // For each position, find the tallest bars on its left and right.
    for (int i = 0; i < n; i++) {
      int leftMax = 0, rightMax = 0;

      // Find the maximum height from the left up to the current position.
      for (int j = 0; j <= i; j++) {
        leftMax = Math.max(leftMax, height[j]);
      }

      // Find the maximum height from the current position to the right.
      for (int j = i; j < n; j++) {
        rightMax = Math.max(rightMax, height[j]);
      }

      // Water trapped depends on the shorter boundary minus the current height.
      total += Math.min(leftMax, rightMax) - height[i];
    }

    return total;
  }
}

// Time: O(n²)
// Space: O(1)