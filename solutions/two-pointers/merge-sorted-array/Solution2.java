// Better Solution
class Solution {
  public void merge(int[] nums1, int m, int[] nums2, int n) {
    int[] temp = new int[m + n];
    int p1 = 0; // Pointer for nums1
    int p2 = 0; // Pointer for nums2
    int idx = 0; // Pointer for the temp merged array

    // Merge elements in ascending order
    while (p1 < m && p2 < n) {
      if (nums1[p1] <= nums2[p2]) {
        temp[idx++] = nums1[p1++];
      } else {
        temp[idx++] = nums2[p2++];
      }
    }

    // Copy remaining elements from nums1, if any
    while (p1 < m) {
      temp[idx++] = nums1[p1++];
    }

    // Copy remaining elements from nums2, if any
    while (p2 < n) {
      temp[idx++] = nums2[p2++];
    }

    // Copy the final sorted result back into nums1
    System.arraycopy(temp, 0, nums1, 0, m + n);
  }
}

// Time: O(m+n),  We iterate through both arrays to fill tmp, and then iterate again to copy back to nums1.
// Space: O(m+n), We allocate a completely new array tmp of size 