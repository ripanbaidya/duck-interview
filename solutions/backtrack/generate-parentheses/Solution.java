class Solution {

  public List<String> generateParenthesis(int n) {
    List<String> res = new ArrayList<>();
    backtrack(n, 0, 0, new StringBuilder(), res);
    return res;
  }

  private void backtrack(int n, int open, int close, StringBuilder sb, List<String> res) {
    if (sb.length() == 2 * n) {
      res.add(sb.toString());
      return;
    }

    if (open < n) {
      sb.append('(');
      backtrack(n, open + 1, close, sb, res);
      sb.deleteCharAt(sb.length() - 1);
    }

    // A ')' is valid only if there is an unmatched '('
    if (close < open) {
      sb.append(')');
      backtrack(n, open, close + 1, sb, res);
      sb.deleteCharAt(sb.length() - 1);
    }
  }

  // Time: O(4^n / sqrt(n))
  // Space: O(n)
}