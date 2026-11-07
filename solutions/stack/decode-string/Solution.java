class Solution {
  
  public String decodeString(String s) {
    Deque<String> stack = new ArrayDeque<>();

    for (char c : s.toCharArray()) {
      if (c != ']') {
        stack.push(String.valueOf(c));
      } else {
        StringBuilder sb = new StringBuilder();
        while (!stack.peek().equals("[")) {
          sb.insert(0, stack.pop());
        }
        stack.pop(); // Remove '['

        StringBuilder num = new StringBuilder();
        while (!stack.isEmpty() && Character.isDigit(stack.peek().charAt(0))) {
          num.insert(0, stack.pop());
        }

        int k = Integer.parseInt(num.toString());
        stack.push(sb.toString().repeat(k));
      }
    }

    StringBuilder res = new StringBuilder();
    while (!stack.isEmpty()) {
      res.insert(0, stack.pop());
    }

    return res.toString();
  }

  // Time: O(n * maxK)
  // Space: O(n)
}