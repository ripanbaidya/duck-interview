class Solution {
  
  public boolean isIsomorphic(String s, String t) {
    if (s.length() != t.length()) return false;

    int[] mapS = new int[256];
    int[] mapT = new int[256];

    for (int i = 0; i < s.length(); i++) {
      char c1 = s.charAt(i);
      char c2 = t.charAt(i);

      if (mapS[c1] != mapT[c2]) return false;

      // Store 1-based index to avoid default 0 collision
      mapS[c1] = i + 1;
      mapT[c2] = i + 1;
    }

    return true;
  }
  // Time: O(n)
  // Space: O(1)
}