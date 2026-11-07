class Solution {

  private static final String[] MAP = {
    "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
  };

  public List<String> letterCombinations(String digits) {
    List<String> res = new ArrayList<>();
    if (digits.isEmpty()) return res;

    backtrack(digits, 0, new StringBuilder(), res);
    return res;
  }

  private void backtrack(String digits, int idx, StringBuilder sb, List<String> res) {
    if (idx == digits.length()) {
      res.add(sb.toString());
      return;
    }

    for (char ch : MAP[digits.charAt(idx) - '0'].toCharArray()) {
      sb.append(ch);
      backtrack(digits, idx + 1, sb, res);
      sb.deleteCharAt(sb.length() - 1);
    }
  }

  // Time: O(4^n * n)
  // Space: O(n)
}