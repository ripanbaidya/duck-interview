iclass Solution {

  public List<String> restoreIpAddresses(String s) {
    List<String> res = new ArrayList<>();
    backtrack(s, 0, new ArrayList<>(), res);
    return res;
  }

  private void backtrack(String s, int start, List<String> parts, List<String> res) {
    int left = 4 - parts.size();
    int remain = s.length() - start;

    // Each remaining part needs 1 to 3 digits, so prune impossible lengths
    if (remain < left || remain > left * 3) return;

    if (left == 0) {
      res.add(String.join(".", parts));
      return;
    }

    for (int len = 1; len <= 3 && start + len <= s.length(); len++) {
      // Longer segments from here would keep the leading zero or exceed 255 as well
      if (len > 1 && s.charAt(start) == '0') break;

      String seg = s.substring(start, start + len);
      if (Integer.parseInt(seg) > 255) break;

      parts.add(seg);
      backtrack(s, start + len, parts, res);
      parts.remove(parts.size() - 1);
    }
  }

  // Time: O(1), at most 3^4 splits
  // Space: O(1)
}