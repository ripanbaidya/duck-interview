class Solution {
  
  public int lengthOfLongestSubstring(String s) {
    int maxLen = 0;
    int left = 0, right = 0;
    HashSet<Character> seen = new HashSet<>();

    while (right < s.length()) {
      char curr = s.charAt(right);

      if (seen.contains(curr)) {
        while (s.charAt(left) != curr) {
          seen.remove(s.charAt(left));
          left++;
        }

        seen.remove(s.charAt(left));
        left++;
      }

      seen.add(curr);
      maxLen = Math.max(maxLen, right - left + 1);
      right++;
    }

    return maxLen;
  }

  // Time: O(n)
  // Space: O(min(n, charset size))
}