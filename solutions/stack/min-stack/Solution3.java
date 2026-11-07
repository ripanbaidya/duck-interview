// Brute solution
class MinStack {
  private Deque<Integer> stack;

  public MinStack() {
    stack = new ArrayDeque<>();
  }

  public void push(int val) {
    stack.push(val);
  }

  public void pop() {
    if (!stack.isEmpty()) {
      stack.pop();
    }
  }

  public int top() {
    if (stack.isEmpty()) {
      throw new IllegalStateException("Stack is empty");
    }
    return stack.peek();
  }

  public int getMin() {
    if (stack.isEmpty()) {
      throw new IllegalStateException("Stack is empty");
    }

    int minVal = Integer.MAX_VALUE;

    // Scan the stack to find the minimum element.
    for (int val : stack) {
      minVal = Math.min(minVal, val);
    }

    return minVal;
  }
}

// Time: 
//   push, pop, top - O(1) per operation.
//   getMin - O(n)
// Space: O(n), where 'n' is the number of elements in the stack.