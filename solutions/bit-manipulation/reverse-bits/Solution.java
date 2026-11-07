public class Solution {
  
  public int reverseBits(int n) {
    int ans = 0;

    for (int i = 0; i < 32; i++) {
      ans = (ans << 1) | (n & 1);
      n >>>= 1;
    }

    return ans;
  }

  // Time: O(1) - exactly 32 iterations
  // Space: O(1)
}