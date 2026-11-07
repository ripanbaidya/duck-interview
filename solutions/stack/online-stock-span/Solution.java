class StockSpanner {
  
  private int idx;
  private Deque<int[]> stack; // {price, index}

  public StockSpanner() {
    idx = 0;
    stack = new ArrayDeque<>();
  }

  public int next(int price) {
    // Remove prices that cannot be the previous greater price
    while (!stack.isEmpty() && stack.peek()[0] <= price) {
      stack.pop();
    }

    int prevGreater = stack.isEmpty() ? -1 : stack.peek()[1];
    stack.push(new int[]{price, idx});

    return idx++ - prevGreater;
  }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */