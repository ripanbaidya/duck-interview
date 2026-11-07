class Solution {
  public int removeDuplicates(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }

    // Pointer 'i' keeps track of the insert position for the next unique element.
    // It starts at 0 because the first element is always unique.
    int i = 0;

    // Pointer 'j' iterates through the array to find new unique elements.
    for (int j = 1; j < nums.length; j++) {
      // If we find an element that is different from the last unique element,
      // it means we've found a new unique number.
      if (nums[j] != nums[i]) {
        i++; // Move the unique elements pointer forward
        nums[i] = nums[j]; // Place the unique value at the new position
      }
    }

    // Since 'i' is the index of the last unique element, 
    // the total number of unique elements is index + 1.
    return i + 1;
  }
}

// Time: O(n)
// Space: O(1)