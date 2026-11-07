class Solution {
  public boolean isValid(String s) {
    Deque<Character> stack = new ArrayDeque<>();

    for (char currChar : s.toCharArray()) {
      // Push opening brackets onto the stack.
      if (currChar == '(' || currChar == '{' || currChar == '[') {
        stack.push(currChar);
      } else {
        // Every closing bracket must match the most recent opening bracket.
        if (!stack.isEmpty() && isMatchingPair(stack.peek(), currChar)) {
          stack.pop();
        } else {
          return false;
        }
      }
    }

    // Unmatched opening brackets make the string invalid.
    return stack.isEmpty();
  }

  private boolean isMatchingPair(char open, char close) {
    return (open == '(' && close == ')') ||
        (open == '{' && close == '}') ||
        (open == '[' && close == ']');
  }
}

// Time: O(n)
// Space: O(n)