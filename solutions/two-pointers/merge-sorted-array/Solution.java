// Optimal Solution - Backward Three-Pointer Technique
class Solution {
  public void merge(int[] nums1, int m, int[] nums2, int n) {
    int p1 = m - 1; // Pointer for the last valid element in nums1
    int p2 = n - 1; // Pointer for the last element in nums2
    int tail = m + n - 1; // Pointer for the end of nums1 where elements are placed

    // Merge backwards to place the largest elements at the end of nums1 first
    while (p1 >= 0 && p2 >= 0) {
      if (nums1[p1] > nums2[p2]) {
        nums1[tail--] = nums1[p1--];
      } else {
        nums1[tail--] = nums2[p2--];
      }
    }

    // If nums2 still has elements left, copy them over
    while (p2 >= 0) {
      nums1[tail--] = nums2[p2--];
    }
  }
}

// Time: O(m+n)
// Space: O(1)
