class Solution {
  
  public int hammingWeight(int n) {
    int count = 0;

    while (n != 0) {
      n = n & (n - 1);
      count++;
    }

    return count;
  }

  // Time: O(K), where K is the number of set bits
  // Space: O(1)
}