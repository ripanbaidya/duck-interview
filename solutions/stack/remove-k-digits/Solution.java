class Solution {
  
  public String removeKdigits(String num, int k) {
    if (k == num.length()) return "0";

    Deque<Character> stack = new ArrayDeque<>();

    for (char c : num.toCharArray()) {
      // Remove a larger previous digit to make the result smaller
      while (k > 0 && !stack.isEmpty() && stack.peek() > c) {
        stack.pop();
        k--;
      }
      stack.push(c);
    }

    // Digits are non-decreasing, so remove from the end
    while (k > 0) {
      stack.pop();
      k--;
    }

    StringBuilder sb = new StringBuilder();
    while (!stack.isEmpty()) {
      sb.append(stack.pop());
    }
    sb.reverse();

    // Remove leading zeros, but keep one digit
    while (sb.length() > 1 && sb.charAt(0) == '0') {
      sb.deleteCharAt(0);
    }

    return sb.toString();
  }

  // Time: O(n)
  // Space: O(n)
}