// Optimal solution - using two stacks
class MinStack {
  private Deque<Integer> stack;
  private Deque<Integer> minStack;

  public MinStack() {
    stack = new ArrayDeque<>();
    minStack = new ArrayDeque<>();
  }

  public void push(int val) {
    stack.push(val);

    // Track the current minimum, including duplicates.
    if (minStack.isEmpty() || val <= minStack.peek()) {
      minStack.push(val);
    }
  }

  public void pop() {
    if (stack.isEmpty()) {
      return;
    }

    int removedVal = stack.pop();

    // Remove the minimum only if the popped value matches it.
    if (removedVal == minStack.peek()) {
      minStack.pop();
    }
  }

  public int top() {
    if (stack.isEmpty()) {
      throw new IllegalStateException("Stack is empty");
    }
    return stack.peek();
  }

  public int getMin() {
    if (minStack.isEmpty()) {
      throw new IllegalStateException("Stack is empty");
    }
    return minStack.peek();
  }
}

// Time: O(1) per operation.
// Space: O(n), where 'n' is the number of elements in the stack.