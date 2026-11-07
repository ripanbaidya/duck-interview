class Solution {
  
  public int[] asteroidCollision(int[] asteroids) {
    Deque<Integer> stack = new ArrayDeque<>();

    for (int a : asteroids) {
      boolean survived = true;

      // Only a left-moving asteroid can collide with a right-moving one
      while (!stack.isEmpty() && stack.peek() > 0 && a < 0) {
        if (stack.peek() < -a) {
          stack.pop();
        } else if (stack.peek() == -a) {
          stack.pop();
          survived = false;
          break;
        } else {
          survived = false;
          break;
        }
      }

      if (survived) stack.push(a);
    }

    int n = stack.size();
    int[] res = new int[n];
    for (int i = n - 1; i >= 0; i--) {
      res[i] = stack.pop();
    }

    return res;
  }

  // Time: O(n)
  // Space: O(n)
}