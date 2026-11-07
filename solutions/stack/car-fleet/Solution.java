class Solution {
  
  public int carFleet(int target, int[] position, int[] speed) {
    int n = position.length;
    int[][] cars = new int[n][2];

    for (int i = 0; i < n; i++) {
      cars[i][0] = position[i];
      cars[i][1] = speed[i];
    }

    // Closest car to the target first
    Arrays.sort(cars, (a, b) -> Integer.compare(b[0], a[0]));

    Deque<Double> stack = new ArrayDeque<>();
    for (int[] car : cars) {
      double time = (double) (target - car[0]) / car[1];

      // Equal time means the car joins the fleet ahead
      if (stack.isEmpty() || time > stack.peek()) {
        stack.push(time);
      }
    }

    return stack.size();
  }

  // Time: O(n log n)
  // Space: O(n)
}