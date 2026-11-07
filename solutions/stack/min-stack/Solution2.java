// Better solution

class Pair {
  int value;
  int minVal;

  public Pair(int value, int minVal) {
    this.value = value;
    this.minVal = minVal;
  }
}

class MinStack {
  private List<Pair> stack;

  public MinStack() {
    stack = new ArrayList<>();
  }

  public void push(int val) {
    int currentMin = val;

    if (!stack.isEmpty()) {
      Pair lastPair = stack.get(stack.size() - 1);
      currentMin = Math.min(val, lastPair.minVal);
    }

    // Store the value along with the minimum at this stack position.
    stack.add(new Pair(val, currentMin));
  }

  public void pop() {
    if (!stack.isEmpty()) {
      stack.remove(stack.size() - 1);
    }
  }

  public int top() {
    if (stack.isEmpty()) {
      throw new IllegalStateException("Stack is empty");
    }
    return stack.get(stack.size() - 1).value;
  }

  public int getMin() {
    if (stack.isEmpty()) {
      throw new IllegalStateException("Stack is empty");
    }
    return stack.get(stack.size() - 1).minVal;
  }
}

// Time: O(1) for push(), pop(), top(), and getMin() (amortized for push()).
// Space: O(n), where 'n' is the number of elements in the stack.