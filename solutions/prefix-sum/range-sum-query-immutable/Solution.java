class NumArray {
  
  private int[] nums;

  public NumArray(int[] nums) {
    this.nums = nums;
    // Compute prefix sum in-place
    for (int i = 1; i < nums.length; i++) {
      this.nums[i] += this.nums[i - 1];
    }
  }

  public int sumRange(int left, int right) {
    return nums[right] - (left > 0 ? nums[left - 1] : 0);
  }

  // Time: O(n) for constructor, O(1) for sumRange
  // Space: O(1)
}