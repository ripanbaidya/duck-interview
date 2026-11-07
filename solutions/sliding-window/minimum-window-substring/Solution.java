class Solution {
  
  public String minWindow(String s, String t) {
    if (t.length() > s.length()) {
      return "";
    }

    Map<Character, Integer> need = new HashMap<>();
    for (char c : t.toCharArray()) {
      need.put(c, need.getOrDefault(c, 0) + 1);
    }

    Map<Character, Integer> window = new HashMap<>();
    int required = need.size();
    int formed = 0;

    int left = 0;
    int minLen = Integer.MAX_VALUE;
    int start = 0;

    for (int right = 0; right < s.length(); right++) {
      char curr = s.charAt(right);
      window.put(curr, window.getOrDefault(curr, 0) + 1);

      if (need.containsKey(curr) &&
          window.get(curr).intValue() == need.get(curr).intValue()) {
        formed++;
      }

      while (formed == required) {
        if (right - left + 1 < minLen) {
          minLen = right - left + 1;
          start = left;
        }

        char leftChar = s.charAt(left);
        window.put(leftChar, window.get(leftChar) - 1);

        if (need.containsKey(leftChar) &&
            window.get(leftChar) < need.get(leftChar)) {
          formed--;
        }

        left++;
      }
    }

    return minLen == Integer.MAX_VALUE
        ? ""
        : s.substring(start, start + minLen);
  }

  // Time: O(|s| + |t|)
  // Space: O(|s| + |t|) in the general character-set model
}