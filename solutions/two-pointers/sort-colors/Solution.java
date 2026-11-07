// Optimal solution - using Dutch National Flag (DNF) Algorithm

class Solution {

  public void sortColors(int[] nums) {
    int low = 0, mid = 0;
    int high = nums.length - 1;

    // Three pointers divide the array into sorted and unsorted regions.
    while (mid <= high) {
      if (nums[mid] == 0) {
        // Move 0 to the beginning and expand the left sorted region.
        swap(nums, low, mid);
        low++;
        mid++;
      } else if (nums[mid] == 1) {
        // 1 belongs in the middle, so move to the next element.
        mid++;
      } else {
        // Move 2 to the end; check the swapped element before moving mid.
        swap(nums, mid, high);
        high--;
      }
    }
  }

  private void swap(int[] nums, int a, int b) {
    int temp = nums[a];
    nums[a] = nums[b];
    nums[b] = temp;
  }
}

// Time: O(n)
// Space: O(1)