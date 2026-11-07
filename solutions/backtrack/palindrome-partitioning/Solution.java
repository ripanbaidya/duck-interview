class Solution {

  public List<List<String>> partition(String s) {
    int n = s.length();

    // pal[i][j] is true if s[i..j] is a palindrome
    boolean[][] pal = new boolean[n][n];
    for (int i = n - 1; i >= 0; i--) {
      for (int j = i; j < n; j++) {
        pal[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || pal[i + 1][j - 1]);
      }
    }

    List<List<String>> res = new ArrayList<>();
    backtrack(s, 0, pal, new ArrayList<>(), res);
    return res;
  }

  private void backtrack(String s, int start, boolean[][] pal, List<String> path,
      List<List<String>> res) {
    if (start == s.length()) {
      res.add(new ArrayList<>(path));
      return;
    }

    for (int end = start; end < s.length(); end++) {
      if (!pal[start][end]) continue;

      path.add(s.substring(start, end + 1));
      backtrack(s, end + 1, pal, path, res);
      path.remove(path.size() - 1);
    }
  }

  // Time: O(n * 2^n)
  // Space: O(n^2)
}