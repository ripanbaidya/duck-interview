class Solution {

  public int countSubstrings(String s) {
    int n = s.length();
    int count = 0;

    // 2n - 1 centers: n single chars and n - 1 gaps between chars
    for (int center = 0; center < 2 * n - 1; center++) {
      int left = center / 2;
      int right = left + center % 2;

      while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
        count++;
        left--;
        right++;
      }
    }
    return count;
  }

  // Time: O(n^2)
  // Space: O(1)
}