// Optimal solution

class Solution {
  public int trap(int[] height) {
    int left = 0, right = height.length - 1;
    int leftMax = 0, rightMax = 0;
    int total = 0;

    while (left < right) {
      // Process the side with the shorter boundary, since it limits the trapped water.
      if (height[left] <= height[right]) {
        if (height[left] >= leftMax) {
          leftMax = height[left];
        } else {
          // The difference between the left maximum and current height is trapped water.
          total += leftMax - height[left];
        }
        left++;
      } else {
        if (height[right] >= rightMax) {
          rightMax = height[right];
        } else {
          // The difference between the right maximum and current height is trapped water.
          total += rightMax - height[right];
        }
        right--;
      }
    }

    return total;
  }
}

// Time: O(n)
// Space: O(1)