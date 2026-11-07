class Solution {
  
  public String removeDuplicates(String s) {
    StringBuilder res = new StringBuilder();

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      int len = res.length();

      if (len > 0 && res.charAt(len - 1) == c) {
        res.deleteCharAt(len - 1);
      } else {
        res.append(c);
      }
    }

    return res.toString();
  }

  // Time: O(n)
  // Space: O(n)
}