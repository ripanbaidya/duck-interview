class Solution {
  public boolean isSubsequence(String s, String t) {
    int i = 0, j = 0;

    // Match characters in order while scanning both strings.
    while (i < s.length() && j < t.length()) {
      if (s.charAt(i) == t.charAt(j)) {
        i++;
      }
      j++;
    }

    // All characters of s must be matched to form a subsequence.
    return i == s.length();
  }
}

// Time: O(n)
// Space: O(1)