class Solution {
  
  public int characterReplacement(String s, int k) {
    int[] freq = new int[26];
    int maxFreq = 0;
    int res = 0;

    for (int i = 0, j = 0; j < s.length(); j++) {
      maxFreq = Math.max(maxFreq, ++freq[s.charAt(j) - 'A']);

      // If invalid window, shrink from left while maintaining max window size
      if (j - i + 1 - maxFreq > k) {
        freq[s.charAt(i) - 'A']--;
        i++;
      }

      res = Math.max(res, j - i + 1);
    }

    return res;
  }

  // Time: O(n)
  // Space: O(1)
}