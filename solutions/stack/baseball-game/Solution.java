class Solution {
  public int calPoints(String[] operations) {
    Deque<Integer> stack = new ArrayDeque<>();

    for (String op : operations) {
      switch (op) {
        case "+" -> {
          // Add the sum of the previous two scores as a new round.
          int top = stack.pop();
          int secondTop = stack.peek();
          stack.push(top);
          stack.push(top + secondTop);
        }
        case "D" -> {
          // Double the previous round's score.
          stack.push(stack.peek() * 2);
        }
        case "C" -> {
          // Remove the previous round's score.
          stack.pop();
        }
        default -> stack.push(Integer.parseInt(op));
      }
    }

    int totalScore = 0;
    for (int score : stack) {
      totalScore += score;
    }

    return totalScore;
  }
}

// Time: O(n), where 'n' is the number of operations.
// Space: O(n) for storing the scores in the stack. 